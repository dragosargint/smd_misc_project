package com.example.foodfinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;



public class FileRW<T> {

    Class<T> tClass;

    public FileRW(Class<T> tClass) {
        this.tClass = tClass;
    }

    /* returns true if write succeded, false othrewise */
   public boolean write(String filename, T object) {
       ObjectOutputStream objectOutputStream = null;
       FileOutputStream fileOutputStream = null;
       try {
           fileOutputStream = new FileOutputStream(filename);
           objectOutputStream = new ObjectOutputStream(fileOutputStream);
       } catch (Exception e) {
           return false;
       }
       try {
           objectOutputStream.writeObject(object);
           objectOutputStream.flush();
           objectOutputStream.close();
       } catch (Exception e) {
           return false;
       }

       return true;
   }

   public boolean write(String filename, List<T> lisOfObjects) {
       ObjectOutputStream objectOutputStream = null;
       FileOutputStream fileOutputStream = null;
       try {
           fileOutputStream = new FileOutputStream(filename);
           objectOutputStream = new ObjectOutputStream(fileOutputStream);
       } catch (Exception e) {
           return false;
       }

       try {
           objectOutputStream.writeObject(lisOfObjects);
           objectOutputStream.flush();
           objectOutputStream.close();
       } catch (Exception e) {
           return false;
       }

       return true;
   }

    public void writeRaw(String filename, T object) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream(filename);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

   public T read(String filename) {
       FileInputStream fileInputStream = null;
       ObjectInputStream objectInputStream = null;
       T object = null;
       Object generalObject = null;

       try {
           fileInputStream = new FileInputStream(filename);
           objectInputStream = new ObjectInputStream(fileInputStream);
       } catch (Exception e) {
           return null;
       }

       try {
           generalObject = objectInputStream.readObject();
           if (tClass.isInstance(generalObject))
               object = (T)  generalObject;
       } catch (Exception e) {
           return null;
       }

       return object;
   }

    public List<T> readList(String filename) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ArrayList<T> listOfObjects = null;
        Object generalObject;

        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (Exception e) {
            return null;
        }

        try {
            generalObject = objectInputStream.readObject();
            if (generalObject instanceof List<?>)
                listOfObjects = (ArrayList<T>)  generalObject;
        } catch (Exception e) {
            return null;
        }

        return listOfObjects;
    }

    public T readRaw(String filename) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);;
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);;
        T object = (T)  objectInputStream.readObject();

        return object;
    }

    public List<T> readListRaw(String filename) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);;
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);;
        List<T> listOfObjects = (ArrayList<T>)  objectInputStream.readObject();

        return listOfObjects;
    }
}
