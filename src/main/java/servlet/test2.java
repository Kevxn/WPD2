package servlet;
import app.model.*;
    import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class test2
    {
        public static void main(String [] args)
        {
            ArrayList<Milestone> arraylist;
            try
            {
                FileInputStream fis = new FileInputStream("hashmap.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                System.out.println(ois.readObject());
                ois.close();
                fis.close();
            }catch(IOException ioe)
            {
                ioe.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Class not found");
                c.printStackTrace();
                return;
            }
            System.out.println("Deserialized HashMap..");
            // Display content using Iterator
       //     for(Milestone temp : arraylist){
              //  System.out.println();
         ////   }
        }
    }

