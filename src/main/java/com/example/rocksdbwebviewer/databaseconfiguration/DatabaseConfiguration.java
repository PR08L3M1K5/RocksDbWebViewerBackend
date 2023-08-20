package com.example.rocksdbwebviewer.databaseconfiguration;

public class DatabaseConfiguration {
    public String databasePath;
    public String serializationType;
    public String serializationClass;
    public String pathToCustomClass;
    public String databaseName;
    public String sshHost;
    public boolean isValid() {
        return databaseName != null && databasePath != null;
    }
}
