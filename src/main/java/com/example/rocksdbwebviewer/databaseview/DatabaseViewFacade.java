package com.example.rocksdbwebviewer.databaseview;

import com.example.rocksdbwebviewer.DatabaseConnector;
import com.example.rocksdbwebviewer.GenericResponse;
import com.example.rocksdbwebviewer.databaseconfiguration.DatabaseConfiguration;
import com.example.rocksdbwebviewer.databaseconfiguration.DatabaseConfigurationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
@RequiredArgsConstructor
public class DatabaseViewFacade {
    private final DatabaseConfigurationFacade databaseConfigurationFacade;

    public ResponseEntity<DatabaseView> getDataFromDatabase(String databaseName) {
        DatabaseConfiguration databaseConfiguration = databaseConfigurationFacade.getDatabaseConfiguration(databaseName);
        if (databaseConfiguration == null)
            return null;
        DatabaseConnector databaseConnector = new DatabaseConnector(databaseConfiguration);
        DatabaseView databaseView = new DatabaseView(databaseConfiguration.databaseName);
        databaseView.addDatabaseObjects(databaseConnector.getAllData(), databaseConfiguration);
        return ResponseEntity.ok(databaseView);
    }

    public GenericResponse getDatabaseObjectFromKey(String searchKey, String databaseName) {
        if (searchKey.isEmpty())
            return new GenericResponse("Invalid search key", null, 400);
        DatabaseConfiguration databaseConfiguration = databaseConfigurationFacade.getDatabaseConfiguration(databaseName);
        if (databaseConfiguration == null)
            return null;
        DatabaseConnector databaseConnector = new DatabaseConnector(databaseConfiguration);
        byte[] result = databaseConnector.getDataFromKey(searchKey);
        if (result.length == 0)
            return new GenericResponse("Object not found", null, 400);
        return new GenericResponse("",DatabaseObjectFactory.convertDatabaseObject(databaseConfiguration,result,searchKey),200);

    }
}
