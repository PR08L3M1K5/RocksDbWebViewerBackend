package com.example.rocksdbwebviewer.databaseconfiguration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class DatabaseConfigurationFileManager {
    private final String DIRECTORY_PATH = "DatabaseConfigFiles";

    public DatabaseConfigurationFileManager() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    public boolean checkIfDatabaseConfigurationExist(String databaseName){
        return new File(DIRECTORY_PATH + "/" + databaseName).exists();
    }


    public DatabaseConfiguration getDatabaseConfigurationFromFile(String databaseName) {
        File configFile = new File(DIRECTORY_PATH + "/" + databaseName);
        if (!configFile.exists())
            return null;
        try {
            FileReader fileReader = new FileReader(configFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String result = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();
            return new Gson().fromJson(result, DatabaseConfiguration.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean saveConfigurationToFile(DatabaseConfiguration databaseConfiguration) {
        try {
            File configFile = new File(DIRECTORY_PATH + "/" + databaseConfiguration.databaseName);
            if (configFile.exists())
                return editExistingFile(databaseConfiguration, configFile);
            FileWriter fileWriter = new FileWriter(configFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(new Gson().toJson(databaseConfiguration));
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editExistingFile(DatabaseConfiguration databaseConfiguration, File configFile) {
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(new Gson().toJson(databaseConfiguration));
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getAllSavedDatabaseConfigurationFilesNames() {
        List<String> result = new ArrayList<>();
        File folder = new File(DIRECTORY_PATH);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null)
            return result;
        for (File file : listOfFiles)
            if (file.isFile()) {
                result.add(file.getName());
            }
        return result;
    }

    public boolean deleteDatabaseConfigurationFile(String databaseName) {

        return new File(DIRECTORY_PATH + "/" + databaseName).delete();

    }
}
