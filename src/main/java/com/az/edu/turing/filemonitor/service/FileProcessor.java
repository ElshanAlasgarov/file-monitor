package com.az.edu.turing.filemonitor.service;

import com.az.edu.turing.filemonitor.loader.CSVLoader;
import com.az.edu.turing.filemonitor.loader.FileLoader;
import com.az.edu.turing.filemonitor.loader.TXTLoader;
import com.az.edu.turing.filemonitor.loader.XMLLoader;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProcessor {

    private final ExecutorService executor;
    private final FileLoader csvLoader;
    private final FileLoader txtLoader;
    private final FileLoader xmlLoader;

    public FileProcessor(){
        this.executor = Executors.newFixedThreadPool(3);
        this.csvLoader = new CSVLoader();
        this.txtLoader = new TXTLoader();
        this.xmlLoader = new XMLLoader();
    }

    public void processFile(Path filePath){
        executor.submit(() -> {
            String fileName = filePath.getFileName().toString();
            try {
                if(fileName.endsWith(".csv")){
                    csvLoader.load(filePath);
                } else if (fileName.endsWith(".txt")) {
                    txtLoader.load(filePath);
                } else if (fileName.endsWith(".xml")) {
                    xmlLoader.load(filePath);
                } else {
                    System.err.println("Unknown file type: " + filePath);
                }
            } catch (Exception e) {
                System.err.println("Error processing file: " + filePath);
                e.printStackTrace();
            }
        });
    }

    public void shutdown(){
        executor.shutdown();
    }
}
