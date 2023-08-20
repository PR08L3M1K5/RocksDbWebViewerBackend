package com.example.rocksdbwebviewer.databaseview;

import com.example.rocksdbwebviewer.databaseconfiguration.DatabaseConfiguration;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseObjectFactory {
    public static DatabaseObject convertDatabaseObject(DatabaseConfiguration databaseConfiguration, byte[] databaseObject, String key) {
        return switch (databaseConfiguration.serializationType) {
            case "Gson" -> createDatabaseObjectFromGson(databaseObject, key);
            case "Collection" -> createDatabaseObjectFromCollection(databaseObject,key);
            case "Class" -> createDatabaseObjectFromClass(databaseObject, key,databaseConfiguration.serializationClass);
            default -> null;
        };
    }

    private static DatabaseObject createDatabaseObjectFromGson(byte[] databaseObject, String key) {
        return new DatabaseObject(key,new Gson().fromJson(new String(databaseObject), Map.class).values().stream().map(value->value.toString()).toList(),new String(databaseObject));
    }

    private static DatabaseObject createDatabaseObjectFromCollection(byte[] databaseObject, String key) {
        return null;
    }

    private static DatabaseObject createDatabaseObjectFromClass(byte[] databaseObject, String key,String className) {
        String convertedObject = getObjectFromClass(className,databaseObject);
        List<String> values = new ArrayList<>();
        values.add(convertedObject);
        return new DatabaseObject(key,values,convertedObject);
    }

    private static String getObjectFromClass(String className, byte[] value) {

        switch (className) {
            case "String" -> {
                return new String(value);
            }
            default -> {
                return null;
            }
        }

    }


}
