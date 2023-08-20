package com.example.rocksdbwebviewer;

import com.example.rocksdbwebviewer.databaseconfiguration.DatabaseConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DatabaseConnector {
    String databasePath;
    RocksDB rocksDB;

    public DatabaseConnector(DatabaseConfiguration databaseConfiguration) {
        if(databaseConfiguration.sshHost.isEmpty())
            createConnectionFromRemote(databaseConfiguration);
        createConnectionFromSsh(databaseConfiguration);
    }
    private void createConnectionFromRemote(DatabaseConfiguration databaseConfiguration){
        try {
            RocksDB.loadLibrary();
            Options options = new Options();
            rocksDB = RocksDB.openReadOnly(options, databaseConfiguration.databasePath);
        } catch (Exception e) {

        }
    }
    private void createConnectionFromSsh(DatabaseConfiguration databaseConfiguration){
        String sshHost = "ssh-server";
        int sshPort = 1; // SSH port

        int localPort = 12345; // Local port for tunnel
        String remoteHost = "rocksdb-hostname";
        int remotePort = 1234; // RocksDB port on the remote server

    }

    public void closeDatabaseConnection(){
        if(this.rocksDB == null)
            return;
        rocksDB.close();
    }

    public Map<String, byte[]> getAllData() {
        Map<String, byte[]> result = new HashMap<>();
        try {
            RocksIterator iterator = rocksDB.newIterator();
            iterator.seekToFirst();
            while (iterator.isValid()) {
                result.put(new String(iterator.key()), iterator.value());
                iterator.next();
            }
            this.closeDatabaseConnection();
            return result;
        }catch (Exception e){
            return result;
        }
    }


    public byte[] getDataFromKey(String searchKey) {
        byte[] result = new byte[0];
        try {
            RocksIterator iterator = rocksDB.newIterator();
            iterator.seekToFirst();
            while (iterator.isValid()) {
                if(new String(iterator.key()).equals(searchKey)) {
                    result = iterator.value();
                    break;
                }
                iterator.next();
            }
            this.closeDatabaseConnection();
            return result;
        }catch (Exception e){
            return result;
        }
    }
}
