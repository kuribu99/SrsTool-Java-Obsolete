/*
 */
package com.kongmy.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kong My
 */
public class DataContext {

    public static final String PROJECT_NAME = "projectName";
    public static final String UNTITLED_PROJECT_NAME = "untitled";
    private static final String PROPERTY_THIS = "this";

    private PropertyChangeSupport pcs;
    private String filePath;
    private Map<String, Object> data;
    private boolean saved;

    public DataContext() {
        data = new HashMap<>();
        saved = false;
        pcs = new PropertyChangeSupport(this);

        data.put(PROJECT_NAME, UNTITLED_PROJECT_NAME);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(PROPERTY_THIS, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(PROPERTY_THIS, listener);
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        if (this.saved != saved) {
            boolean oldVal = this.saved;
            this.saved = saved;
            pcs.firePropertyChange(PROPERTY_THIS, oldVal, saved);
        }
    }

    public boolean hasFile() {
        return filePath != null && new File(filePath).exists();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getProjectName() {
        return data.get(PROJECT_NAME).toString();
    }

    public void setProjectName(String projectName) {
        String oldProjectName = data.get(PROJECT_NAME).toString();
        if (oldProjectName != projectName) {
            data.put(PROJECT_NAME, projectName);
            setSaved(false);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void Save() throws IOException {
        try (FileOutputStream fileStream = new FileOutputStream(filePath)) {
            try (ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
                objectStream.writeObject(data);
            }
        }
    }

    public static DataContext newInstance() {
        return new DataContext();
    }

    public static DataContext Load(File file) throws ClassNotFoundException, IOException {
        DataContext dataContext = new DataContext();
        try (FileInputStream fileStream = new FileInputStream(file)) {
            try (ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
                dataContext.data = (Map<String, Object>) objectStream.readObject();
            }
        }
        dataContext.filePath = file.getAbsolutePath();
        dataContext.setSaved(true);
        return dataContext;
    }

}
