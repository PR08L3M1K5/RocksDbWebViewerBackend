package com.example.rocksdbwebviewer.databaseview;

import com.google.gson.Gson;

import java.util.Map;

public class DatabaseObjectFormGson extends DatabaseObject{
    public DatabaseObjectFormGson(byte[] objectFromDb,String key) {
        super(key,new Gson().fromJson(new String(objectFromDb), Map.class).values().stream().map(value->value.toString()).toList(),new String(objectFromDb));
    }
}
