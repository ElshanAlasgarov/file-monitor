package com.az.edu.turing.filemonitor.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileMonitor {
    private final String directoryPath;
    private final int interval;
    private final ScheduledExecutorService scheduler;
    private final FileProcessor fileProcessor;
    private final Set<String> processedFiles;

    public FileMonitor(String directoryPath, int interval, FileProcessor fileProcessor) {
        this.directoryPath = directoryPath;
        this.interval = interval;
        this.fileProcessor = fileProcessor;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.processedFiles = new HashSet<>();
    }

    public void start() {
        System.out.println("File Monitor started, watching directory: " + directoryPath);
        scheduler.scheduleAtFixedRate(this::scanDirectory, 0, interval, TimeUnit.MILLISECONDS);
    }

    private void scanDirectory() {
        try {
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory created: " + directoryPath);
            }

            Files.list(path)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        String fileName = file.getFileName().toString();
                        if (!processedFiles.contains(fileName)) {
                            System.out.println("New file detected: " + fileName);
                            processedFiles.add(fileName);
                            fileProcessor.processFile(file);
                        }
                    });

        } catch (IOException e) {
            System.err.println("Failed to scan directory: " + e.getMessage());
        }
    }

    public void stop() {
        System.out.println("Stopping File Monitor...");
        scheduler.shutdownNow();
    }
}
