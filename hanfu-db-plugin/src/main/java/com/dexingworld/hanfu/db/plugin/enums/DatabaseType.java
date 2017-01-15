package com.dexingworld.hanfu.db.plugin.enums;

/**
 * Created by wangpeng on 2016/12/15.
 */
public enum DatabaseType {
    MYSQL("mysql", "com.mysql.jdbc.Driver"),
    POSTGRESQL("postgresql", "org.postgresql.Driver");
    private String type;
    private String driverClassName;

    private DatabaseType(String type, String driverClassName) {
        this.type = type;
        this.driverClassName = driverClassName;
    }

    public String getType() {
        return this.type;
    }

    public String getDriverClassName() {
        return this.driverClassName;
    }
}
