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
    Interpreter myApp;
    
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

///*    
    private Method getValidateSubStringsMethod() throws NoSuchMethodException {
      Method method = Interpreter.class.getDeclaredMethod("validateSubStrings", Vector.class);
      method.setAccessible(true);
      return method;
    }
//*/    
    
    @Test
    public void shouldAnswerWithTrue()
    {
        myApp = new Interpreter();
        String[] out = null;
        try{
          out = (String[])getSliceInputMethod().invoke(myApp, "321 021" );
        } catch(Exception e){
          System.out.println("No such method");
        }
        assertTrue(out != null);
        assertTrue(out[0].equals("321"));
        assertTrue(out[1].equals("021"));
    
        String[] inStrings = {"210","23","10","857"};
        String   validOut  = "2102310857";
        Vector<String> outString = null;
        
        try{
          outString = (Vector<String>)getCreateValidSubStringsMethod().invoke(myApp,inStrings);
        } catch(Exception e){
          System.out.println("No such method");
        }
        
        //assertTrue(outString != null);
        //assertTrue(outString.toArray()[0].equals(validOut));
    }
    
}
