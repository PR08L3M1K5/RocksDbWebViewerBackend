package com.example.rocksdbwebviewer.databaseview;

import com.example.rocksdbwebviewer.GenericResponse;
import com.example.rocksdbwebviewer.databaseview.DatabaseViewFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/database/view")
@RequiredArgsConstructor
public class DatabaseViewService {
    public final DatabaseViewFacade databaseViewFacade;
    @GetMapping("/{databaseName}")
    ResponseEntity<DatabaseView> getDatabaseView(@PathVariable String databaseName){
        return databaseViewFacade.getDataFromDatabase(databaseName);
    }
    @GetMapping("/{databaseName}/object/{searchKey}")
    GenericResponse getDatabaseObjectFromKey(@PathVariable String searchKey,@PathVariable String databaseName){
        return databaseViewFacade.getDatabaseObjectFromKey(searchKey,databaseName);
    }
}
