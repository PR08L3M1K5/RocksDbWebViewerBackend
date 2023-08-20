package com.example.rocksdbwebviewer;

import com.example.rocksdbwebviewer.databaseview.DatabaseObject;

public class GenericResponse {
    public String message;
    public DatabaseObject result;
    public int statusCode;
    public GenericResponse(String message, DatabaseObject result, int statusCode) {
        this.message = message;
        this.result = result;
        this.statusCode = statusCode;
    }


}
