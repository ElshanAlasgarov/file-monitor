package com.az.edu.turing.filemonitor.loader;

import com.az.edu.turing.filemonitor.model.TradeData;
import com.az.edu.turing.filemonitor.gui.TradeDataViewerApp;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XMLLoader implements FileLoader {
    @Override
    public void load(Path filePath) throws Exception {
        List<String> lines = Files.readAllLines(filePath);
        System.out.println("Loaded XML file: " + filePath.getFileName());

        for (String line : lines) {
            if (!line.contains("<value ")) continue;

            try {
                String date = extractAttribute(line, "date");
                double open = Double.parseDouble(extractAttribute(line, "open"));
                double high = Double.parseDouble(extractAttribute(line, "high"));
                double low = Double.parseDouble(extractAttribute(line, "low"));
                double close = Double.parseDouble(extractAttribute(line, "close"));
                int volume = Integer.parseInt(extractAttribute(line, "volume"));

                TradeData tradeData = new TradeData(date, open, high, low, close, volume);
                System.out.println("Parsed XML data: " + tradeData);
                TradeDataViewerApp.addTradeData(tradeData);
            } catch (Exception e) {
                System.err.println("Error parsing XML line: " + line);
                e.printStackTrace();
            }
        }
    }

    private String extractAttribute(String line, String attribute) {
        String[] parts = line.split(attribute + "=\"");
        if (parts.length < 2) {
            return "0";
        }
        return parts[1].split("\"")[0];
    }
}
