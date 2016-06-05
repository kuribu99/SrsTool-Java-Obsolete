/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongmy.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kong My
 */
public abstract class Configuration {

    // Constants

    protected static final String FILE_NAME = "config.txt";
    public static final String MODULES_CLASS_NAMES = "modules_class_name";

    protected final Map<String, String> settings;

    protected Configuration() {
        settings = new HashMap<>();
    }

    public String getSetting(String key) {
        return this.settings.get(key);
    }

    public void AddConfiguration(Configurable conf) {
        conf.getConfiguration().entrySet().forEach((entry) -> {
            if (!settings.containsKey(entry.getKey())) {
                settings.put(entry.getKey(), entry.getValue());
            }
        });
    }

    public abstract void LoadDefaultConfiguration();

    public void ReadConfigurationFile() throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(FILE_NAME))) {
            String[] lineSplit = null;
            while (in.hasNextLine()) {
                lineSplit = in.nextLine().split("=");
                settings.put(lineSplit[0].trim(), lineSplit[1].trim());
            }
        }
    }

    public void WriteConfigurationToFile() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            settings.entrySet().forEach((entry) -> {
                try {
                    writer.write(entry.getKey().trim());
                    writer.write("=");
                    writer.write(entry.getValue().trim());
                    writer.write("\n");
                } catch (IOException ex) {
                    Logger.getLogger(Configuration.class.getName()).log(Level.WARNING, "Configuration file not found", ex);
                }
            });

            writer.close();
        }
    }
}
