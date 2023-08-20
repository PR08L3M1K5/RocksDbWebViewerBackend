package com.example.rocksdbwebviewer.databaseconfiguration;

import com.example.rocksdbwebviewer.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DatabaseConfigurationFacade {
    DatabaseConfigurationFileManager databaseConfigurationFileManager = new DatabaseConfigurationFileManager();
    public ResponseEntity<List<String>> getAllDatabaseConfigurationFilesNames(){
        return ResponseEntity.ok(databaseConfigurationFileManager.getAllSavedDatabaseConfigurationFilesNames());
    }
    public GenericResponse saveDatabaseConfiguration(DatabaseConfiguration databaseConfiguration)  {
        if(!databaseConfiguration.isValid())
            return new GenericResponse("Invalid object", null,400);
        if(databaseConfigurationFileManager.checkIfDatabaseConfigurationExist(databaseConfiguration.databaseName))
            return new GenericResponse(MessageFormat.format("Database configuration with name: {0} already exist",databaseConfiguration.databaseName), null,400);
        if(!databaseConfigurationFileManager.saveConfigurationToFile(databaseConfiguration))
            return new GenericResponse(MessageFormat.format("Error while saving database configuration",databaseConfiguration.databaseName), null,400);
        return new GenericResponse("Database configuration saved successfully", null,200);
    }

    public ResponseEntity<Boolean> deleteDatabaseConfiguration(String databaseName) {
        return ResponseEntity.ok(databaseConfigurationFileManager.deleteDatabaseConfigurationFile(databaseName));
    }

    public DatabaseConfiguration getDatabaseConfiguration(String databaseName){
        return this.databaseConfigurationFileManager.getDatabaseConfigurationFromFile(databaseName);
    }
}
