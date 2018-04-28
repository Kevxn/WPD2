package servlet;


import db.H2Milestone;
import app.model.*;
import java.io.*;
import java.util.*;

public class test implements Serializable {


HashMap data = new HashMap();

    test(){

    }


    public void tests(List<Milestone> ma)
    {
        System.out.print("testclass");

         try
         {

             FileOutputStream fos =
                     new FileOutputStream("hashmap.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);

                 System.out.print("t");
                 oos.writeObject(ma);

             oos.close();
             fos.close();
             System.out.printf("Serialized HashMap data is saved in hashmap.ser");
         }catch(IOException ioe)
         {
             ioe.printStackTrace();
         }
     }
 }


