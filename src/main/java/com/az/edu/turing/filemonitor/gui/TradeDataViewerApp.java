package com.az.edu.turing.filemonitor.gui;

import com.az.edu.turing.filemonitor.config.ConfigManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.az.edu.turing.filemonitor.model.TradeData;
import com.az.edu.turing.filemonitor.service.FileMonitor;
import com.az.edu.turing.filemonitor.service.FileProcessor;

public class TradeDataViewerApp extends Application {
    private static FileDataTable fileDataTable;
    private static FileMonitor fileMonitor;
    private static FileProcessor fileProcessor;

    @Override
    public void start(Stage primaryStage) {
        fileDataTable = new FileDataTable();

        VBox vbox = new VBox(fileDataTable.getTableView());
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setTitle("Trade Data Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.runLater(() -> {
            System.out.println("GUI has fully started!");
            fileProcessor = new FileProcessor();
            fileMonitor = new FileMonitor(ConfigManager.getInputDirectory(),
                    ConfigManager.getMonitoringInterval(), fileProcessor);
            fileMonitor.start();
        });

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Window is closing. Stopping FileMonitor and FileProcessor...");
            if (fileMonitor != null) {
                fileMonitor.stop();
            }
            if (fileProcessor != null) {
                fileProcessor.shutdown();
            }
            Platform.exit();
            System.exit(0);
        });
    }

    public static void addTradeData(TradeData tradeData) {
        Platform.runLater(() -> {
            if (fileDataTable != null) {
                fileDataTable.addData(tradeData);
                System.out.println("Data added to GUI: " + tradeData);
            } else {
                System.err.println("Error: FileDataTable is not initialized!");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
