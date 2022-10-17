package com.omilia.app;

import java.io.IOException;
import java.util.*;

 
public class App 
{
    public static void main( String[] args )
    throws IOException
    {
        Interpreter myApp = new Interpreter();

        Vector<String> phonesAll = myApp.interepretApp();
        
        
        for(int i = 0; phonesAll!=null && i<phonesAll.size(); i++){
          System.out.println(phonesAll.toArray()[i]);
        }
       
    }
}
