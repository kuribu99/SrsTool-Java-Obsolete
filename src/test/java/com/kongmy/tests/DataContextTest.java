/*
 */
package com.kongmy.tests;

import com.kongmy.core.DataContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kong My
 */
public class DataContextTest {

    private DataContext dataContext;
    private static final String FILE_PATH = "data";

    @Before
    public void setUp() {
        dataContext = new DataContext();
    }

    @After
    public void tearDown() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void TestSave() throws IOException, ClassNotFoundException {
        dataContext.getData().put("key", "value");
        dataContext.setFilePath(FILE_PATH);
        dataContext.Save();

        File file = new File(FILE_PATH);
        assertTrue(file.exists());

        Map<String, Object> map;
        try (FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            map = (Map<String, Object>) objectStream.readObject();
        }

        assertEquals(map.size(), 2);
        assertEquals(map.get(DataContext.PROJECT_NAME), "untitled");
        assertEquals(map.get("key"), "value");
    }

    @Test
    public void TestLoad() throws IOException, ClassNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");

        File file = new File(FILE_PATH);
        try (FileOutputStream fileStream = new FileOutputStream(file);
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(map);
        }

        dataContext = DataContext.Load(new File(FILE_PATH));

        assertEquals("value", dataContext.getData().get("key"));
    }

}
