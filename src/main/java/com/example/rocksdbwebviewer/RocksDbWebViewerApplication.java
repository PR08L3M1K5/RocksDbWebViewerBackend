package com.example.rocksdbwebviewer;

import org.rocksdb.RocksDBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RocksDbWebViewerApplication {

    public static void main(String[] args) throws RocksDBException {
        SpringApplication.run(RocksDbWebViewerApplication.class, args);
    }

}
