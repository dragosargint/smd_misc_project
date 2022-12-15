package com.example.foodfinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRW<T> {

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

   public T read(String filename) {
       FileInputStream fileInputStream = null;
       ObjectInputStream objectInputStream = null;
       T object = null;

       try {
           fileInputStream = new FileInputStream(filename);
           objectInputStream = new ObjectInputStream(fileInputStream);
       } catch (Exception e) {
           return null;
       }

       try {
           object = (T)  objectInputStream.readObject();
       } catch (Exception e) {
           return null;
       }

       return object;
   }
}
