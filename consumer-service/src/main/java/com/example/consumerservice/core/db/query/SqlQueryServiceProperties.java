package com.example.consumerservice.core.db.query;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sql-query")
public class SqlQueryServiceProperties {
    private boolean hotReload;
    private String[] mapperLocations;

    public boolean isHotReload() {
        return hotReload;
    }

    public void setHotReload(boolean hotReload) {
        this.hotReload = hotReload;
    }

    public String[] getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String[] mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

}
