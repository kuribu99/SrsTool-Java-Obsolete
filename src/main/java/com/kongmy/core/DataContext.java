/*
 */
package com.kongmy.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kong My
 */
public class DataContext {

    private static final String FORMAT = ".srs";

    private String projectName;
    private String filePath;
    private Map<String, Object> data;

    public DataContext() {
        data = new HashMap<>();
        projectName = "untitled";
        filePath = projectName + FORMAT;
    }

    private static String FormatFilePath(String filePath) {
        if (filePath.endsWith(FORMAT)) {
            return filePath;
        } else {
            return filePath + FORMAT;
        }
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void Save() {
        try (FileOutputStream fileStream = new FileOutputStream(filePath)) {

            try (ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {

                objectStream.writeObject(data);

            } catch (IOException ex) {
                Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataContext newInstance() {
        return new DataContext();
    }

    public static DataContext Load(String filePath) {
        DataContext dataContext = new DataContext();
        filePath = FormatFilePath(filePath);
        try (FileInputStream fileStream = new FileInputStream(filePath)) {

            try (ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

                dataContext.data = (Map<String, Object>) objectStream.readObject();

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataContext.filePath = filePath;
        return dataContext;
    }
    
    public boolean FileExists() {
        return new File(filePath).exists();
    }

}
