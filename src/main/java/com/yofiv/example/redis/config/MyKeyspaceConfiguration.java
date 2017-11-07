package com.yofiv.example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

public class MyKeyspaceConfiguration
        extends KeyspaceConfiguration
{
    @Autowired
    private Environment env;

    @Override
    public boolean hasSettingsFor(Class<?> type)
    {
        return true;
    }

    @Override
    public KeyspaceSettings getKeyspaceSettings(Class<?> type)
    {
        KeyspaceSettings keyspaceSettings = new KeyspaceSettings(type, "my-keyspace");
        keyspaceSettings.setTimeToLive(Long.parseLong(env.getProperty("spring.redis.timeToLive")));

        return keyspaceSettings;
    }
}
