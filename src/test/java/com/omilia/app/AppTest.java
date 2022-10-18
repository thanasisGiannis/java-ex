package com.omilia.app;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.lang.reflect.Method;
import java.lang.Class;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    
    private Method getSliceInputMethod() throws NoSuchMethodException {
      Method method = Interpreter.class.getDeclaredMethod("sliceInput", String.class);
      method.setAccessible(true);
      return method;
    }

    private Method getCreateValidSubStringsMethod() throws NoSuchMethodException {
      Method method = Interpreter.class.getDeclaredMethod("createValidSubStrings", String[].class);
      method.setAccessible(true);
      return method;
    }

  
    private Method getValidateSubStringsMethod() throws NoSuchMethodException {
      Method method = Interpreter.class.getDeclaredMethod("validateSubStrings", Vector.class);
      method.setAccessible(true);
      return method;
    }




  @Test
  public void sliceInputTest(){
  
    Interpreter myApp = new Interpreter();
    
    String[] outTest = null;
    System.out.println("Test: sliceInput()");
    try{
      // valid inputs
      
      outTest = (String[])getSliceInputMethod().invoke(myApp, "210 23 1 0 857");
      assertTrue(outTest[0].equals("210"));
      assertTrue(outTest[1].equals("23"));
      assertTrue(outTest[2].equals("1"));
      assertTrue(outTest[3].equals("0"));
      assertTrue(outTest[4].equals("857"));
      System.out.println("Test1: Done");
      
      outTest = (String[])getSliceInputMethod().invoke(myApp, "2 1 0 2 3 1 0 8 5 7");
      assertTrue(outTest[0].equals("2"));
      assertTrue(outTest[1].equals("1"));
      assertTrue(outTest[2].equals("0"));
      assertTrue(outTest[3].equals("2"));
      assertTrue(outTest[4].equals("3"));
      assertTrue(outTest[5].equals("1"));
      assertTrue(outTest[6].equals("0"));
      assertTrue(outTest[7].equals("8"));
      assertTrue(outTest[8].equals("5"));
      assertTrue(outTest[9].equals("7"));
      System.out.println("Test2: Done");
      // non-valid inputs
      outTest = (String[])getSliceInputMethod().invoke(myApp, "21023 1 0 857" );
      assertTrue(outTest == null);
      System.out.println("Test3: Done");
      
      outTest = (String[])getSliceInputMethod().invoke(myApp, "21o 23 10 857" );
      assertTrue(outTest == null);
      System.out.println("Test4: Done");
      
      outTest = (String[])getSliceInputMethod().invoke(myApp, "21o 23 10 85=" );
      assertTrue(outTest == null);
      System.out.println("Test5: Done");
      
      outTest = (String[])getSliceInputMethod().invoke(myApp, "" );
      assertTrue(outTest == null);
      System.out.println("Test6: Done");
      
      
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
    
  }


  @Test
  public void createValidSubStringsTest(){
    
    Interpreter myApp = new Interpreter();
    
    Vector<String> outTest = null;
    System.out.println("Test: createValidSubStrings()");
    try{
      // valid inputs
      String[] inTest1 = {"210",
                          "23",
                          "10",
                          "857"}; 
     
      // THERE IS A PROBLEM HERE
      // COULDN'T IDENTIFY IT
      outTest = (Vector<String>)getCreateValidSubStringsMethod().invoke(myApp,inTest1);
      
      
    } catch(Exception e){
      System.out.println(e.getMessage());
      
    }
    
  }
  
  @Test
  public void validateSubStrings(){
    Interpreter myApp = new Interpreter();
    
    Vector<String> outTest = null;
    System.out.println("Test: validateSubStrings()");
    try{
      
      Vector<String> inTest1 = new Vector();
      
      // valid inputs
      inTest1.add("2106930664"); 
      inTest1.add("6947212792");
      inTest1.add("00306972413502");
      outTest =  (Vector<String>)getValidateSubStringsMethod().invoke(myApp,inTest1);
      assertTrue(outTest.contains("2106930664"));
      assertTrue(outTest.contains("6947212792"));
      assertTrue(outTest.contains("00306972413502"));
      assertTrue(outTest.size() == 3);
      
      
      
      
      // non valid 
      inTest1.clear(); 
      inTest1.add("31023108571"); 
      inTest1.add("210693664");
      inTest1.add("030697002413502");
      inTest1.add("302558");
      outTest =  (Vector<String>)getValidateSubStringsMethod().invoke(myApp,inTest1);
      assertTrue(!outTest.contains("3102310857"));
      assertTrue(!outTest.contains("210693664"));
      assertTrue(!outTest.contains("030697002413502"));
      assertTrue(!outTest.contains("302558"));
      assertTrue(outTest.size() == 0);
      
      inTest1.clear();
      outTest =  (Vector<String>)getValidateSubStringsMethod().invoke(myApp,inTest1);
      assertTrue(outTest == null);
      
      
      
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
    

  }

}
