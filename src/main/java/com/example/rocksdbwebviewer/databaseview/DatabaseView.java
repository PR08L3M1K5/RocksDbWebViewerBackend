package com.example.rocksdbwebviewer.databaseview;

import com.example.rocksdbwebviewer.databaseconfiguration.DatabaseConfiguration;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseView {
    public String databaseName;
    public List<DatabaseObject> databaseObjects = new ArrayList<>();
    public List<String> objectValuesNames = new ArrayList<>();
    public DatabaseView(String databaseName){
        this.databaseName = databaseName;
    }
    public void addDatabaseObjects(Map<String,byte[]> objectsFromDb,DatabaseConfiguration databaseConfiguration){
        if(objectsFromDb.isEmpty())
            return;
       this.getObjectValuesNames(objectsFromDb,databaseConfiguration.serializationType);
        for(String key : objectsFromDb.keySet()){
             databaseObjects.add(DatabaseObjectFactory.convertDatabaseObject(databaseConfiguration,objectsFromDb.get(key),key));
        }
    }
    private void getObjectValuesNames(Map<String,byte[]> objectsFromDb,String serializationType){
        switch (serializationType){
            case "Gson":
                Map map = new Gson().fromJson(new String(objectsFromDb.entrySet().iterator().next().getValue()),Map.class);
                objectValuesNames.addAll(map.keySet());
                break;
            case "Class":
                objectValuesNames.add("value");
                break;
        }

    }

}
