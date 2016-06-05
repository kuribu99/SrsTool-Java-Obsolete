/*
 */
package com.kongmy.srs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    
    private Map<String, Object> data;
    
    public DataContext() {
        data = new HashMap<>();
    }
    
    public void putData(String key, Object value) {
        data.put(key, value);
    }
    
    public Object getData(String key) {
        return data.get(key);
    }
    
    public void Save(String filePath) {
        try (FileOutputStream fileStream = new FileOutputStream(filePath)){
            
            try (ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)){
            
                objectStream.writeObject(data);
                
            } catch (IOException ex) {
                Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataContext Load(String filePath) {
        DataContext dataContext = new DataContext();
        
        try (FileInputStream fileStream = new FileInputStream(filePath)){
            
            try (ObjectInputStream objectStream = new ObjectInputStream(fileStream)){
            
                dataContext.data = (Map<String, Object>) objectStream.readObject();
                
            } catch (IOException ex) {
                Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DataContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataContext;
    }
    
}
