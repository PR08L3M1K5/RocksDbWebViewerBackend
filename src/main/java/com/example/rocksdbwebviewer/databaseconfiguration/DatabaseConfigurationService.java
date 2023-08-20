package com.example.rocksdbwebviewer.databaseconfiguration;
import com.example.rocksdbwebviewer.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/database/configuration")
@RequiredArgsConstructor
public class DatabaseConfigurationService {
    public final DatabaseConfigurationFacade databaseConfigurationFacade;

    @PostMapping
    public GenericResponse upsert(@RequestBody DatabaseConfiguration databaseConfiguration)
    {
        return databaseConfigurationFacade.saveDatabaseConfiguration(databaseConfiguration);
    }

    @GetMapping
    ResponseEntity<List<String>> getAllDatabasesConfigurations(){
        return databaseConfigurationFacade.getAllDatabaseConfigurationFilesNames();
    }

    @DeleteMapping("/{databaseName}")
    ResponseEntity<Boolean> deleteDatabaseConfiguration(@PathVariable String databaseName){
        return databaseConfigurationFacade.deleteDatabaseConfiguration(databaseName);
    }

}
