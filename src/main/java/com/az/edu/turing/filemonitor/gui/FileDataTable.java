package com.az.edu.turing.filemonitor.gui;

import com.az.edu.turing.filemonitor.model.TradeData;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FileDataTable {
    private TableView<TradeData> tableView;

    public FileDataTable() {
        tableView = new TableView<>();

        TableColumn<TradeData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<TradeData, Double> openColumn = new TableColumn<>("Open");
        openColumn.setCellValueFactory(new PropertyValueFactory<>("open"));

        TableColumn<TradeData, Double> highColumn = new TableColumn<>("High");
        highColumn.setCellValueFactory(new PropertyValueFactory<>("high"));

        TableColumn<TradeData, Double> lowColumn = new TableColumn<>("Low");
        lowColumn.setCellValueFactory(new PropertyValueFactory<>("low"));

        TableColumn<TradeData, Double> closeColumn = new TableColumn<>("Close");
        closeColumn.setCellValueFactory(new PropertyValueFactory<>("close"));

        TableColumn<TradeData, Integer> volumeColumn = new TableColumn<>("Volume");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

        tableView.getColumns().addAll(dateColumn, openColumn, highColumn, lowColumn, closeColumn, volumeColumn);
    }

    public TableView<TradeData> getTableView() {
        return tableView;
    }

    public void addData(TradeData tradeData) {
        Platform.runLater(() -> tableView.getItems().add(tradeData));
    }
}
