package com.az.edu.turing.filemonitor;

import com.az.edu.turing.filemonitor.config.ConfigManager;
import com.az.edu.turing.filemonitor.gui.TradeDataViewerApp;

public class Main {
    public static void main(String[] args) {

        ConfigManager.loadConfig();

        TradeDataViewerApp.main(args);
    }
}
