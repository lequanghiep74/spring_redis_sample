package com.yofiv.example.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

public class MyKeyspaceConfiguration
        extends KeyspaceConfiguration
{
    private @Value("${spring.redis.timeToLive}")
    long timeToLive;

    @Override
    public boolean hasSettingsFor(Class<?> type)
    {
        return true;
    }

    @Override
    public KeyspaceSettings getKeyspaceSettings(Class<?> type)
    {
        KeyspaceSettings keyspaceSettings = new KeyspaceSettings(type, "my-keyspace");
        keyspaceSettings.setTimeToLive(timeToLive);

        return keyspaceSettings;
    }
}
