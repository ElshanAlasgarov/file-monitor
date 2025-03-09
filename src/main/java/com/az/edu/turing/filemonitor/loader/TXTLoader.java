package com.az.edu.turing.filemonitor.loader;

import com.az.edu.turing.filemonitor.gui.TradeDataViewerApp;
import com.az.edu.turing.filemonitor.model.TradeData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TXTLoader implements FileLoader {

    @Override
    public void load(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        System.out.println("Loaded txt file: " + path.getFileName());
        for (String line : lines) {
            if (!line.startsWith("Date")) {
                String[] data = line.split(";");

                if (data.length < 6) {
                    System.err.println("Invalid CSV line: " + line);
                    continue;
                }
                TradeData tradeData = new TradeData(
                        data[0],
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),
                        Integer.parseInt(data[5])
                );
                System.out.println("Parsed TXT data: " + tradeData);
                TradeDataViewerApp.addTradeData(tradeData);
            }
        }
    }
}
