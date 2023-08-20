package com.example.rocksdbwebviewer.databaseview;
import java.util.List;



public  class DatabaseObject {
    public String key;
    public List<String> values;
    public String jsonObject;
    public DatabaseObject(String key,List<String> values, String jsonObject){
        this.key = key;
        this.values = values;
        this.jsonObject = jsonObject;
    }
}
