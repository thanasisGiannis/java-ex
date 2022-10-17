package com.omilia.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Interpreter
{


  private String[] sliceInput(String inputS){
    // chop input string into valid length substrings 
    // using space as seperation rule
    if(inputS == null) return null;
    String[] numbersStringArray = null;
    numbersStringArray = inputS.split(" ");
    
    for(String number : numbersStringArray){
      // ToDo: Check for non number related characters
      // and discard them
      if(number.length() > 3 || number.length()<1) return null;
      for(int i=0; i < number.length(); i++){
        if( (number.charAt(i) < 48) || (number.charAt(i) > 57) ) return null;
      }
    }
    
    return numbersStringArray;
  }
  

  private Vector<String> createValidSubStrings(String[] arrayOfNumbersAsStrings){
    // create all substrings based on the space seperated substrings
    
    if(arrayOfNumbersAsStrings == null) return null;
    
    Vector<String> outputVec = new Vector<String>();
    Vector<String> phonesAll = new Vector<String>();
    Vector<String> phonestmp = new Vector<String>();
    
    for(String numAsString : arrayOfNumbersAsStrings){
      int c0;
      int c1;
      int c2;
      
      phonestmp.clear();
      switch(numAsString.length()){
        // create substrings according to the input spaced seperated substring number
        case 1:
          phonestmp.add(numAsString);
          break;
        case 2:
          phonestmp.add(numAsString);
          c1 = Character.getNumericValue(numAsString.charAt(0));
          c0 = Character.getNumericValue(numAsString.charAt(1));
          if(c0 != 0){
            phonestmp.add(Integer.toString(c1*10)+Integer.toString(c0));
          }  
          break;
        case 3:
          phonestmp.add(numAsString);
          c2 = Character.getNumericValue(numAsString.charAt(0));
          c1 = Character.getNumericValue(numAsString.charAt(1));
          c0 = Character.getNumericValue(numAsString.charAt(2));
          if(c1!=0 && c0!=0){
            phonestmp.add(Integer.toString(c2*100)+Integer.toString(c1*10)+Integer.toString(c0));
          }
          if(c2!=0 && c0!=0 ){
            phonestmp.add(Integer.toString(c2*100+c1*10)+Integer.toString(c0));
          }  
          
         if(c1!=0 || c0!=0){
            phonestmp.add(Integer.toString(c2*100)+Integer.toString(c1*10+c0));
          }  
          break;
        default:
          return null;
      }
      
        
      
      if(phonesAll.size() > 0){
        // At this point create all combinations 
        // previous and new substrings
        // phonesAll x phonestmp;
        outputVec.clear();
        for(String tmp : phonestmp){
          for(String phone : phonesAll){
            outputVec.add(phone + tmp);
          }
        }
        phonesAll.clear();
        phonesAll = new Vector(outputVec);
      }else{
        phonesAll = new Vector(phonestmp);
      }
      phonesAll = new Vector(new HashSet(phonesAll));
    }    
    return phonesAll;
  }

  private Vector<String> validateSubStrings(Vector<String> phonesAll){
  
    Vector<String> validatedPhonesAll = new Vector<String>();
    
    for(String number : phonesAll){
      
      // greek numbers should be of length 10 or 14
      if(number.length() == 10){
        // should start with '2' or '69'
        if(number.charAt(0) == '2' || (number.charAt(0) == '6' && number.charAt(1) == '9'))
        {
          validatedPhonesAll.add(number);
         
        } 
      }else if(number.length() == 14){
        // should start with 00302’ or ‘003069’
        if(number.substring(0,5).equals("00302") || number.substring(0,6).equals("003069") ){
          validatedPhonesAll.add(number);
        } 
      }else{
        // ignore the number
      }
    
    }
    
    //validatedPhonesAll = new Vector<String>(phonesAll);
    return validatedPhonesAll;
  }

  public Vector<String> interepretApp() 
  throws IOException 
  {
    BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    
    String numbersString;
    
    System.out.println("Insert valid number");
    
    // Reading data using readLine
    numbersString = reader.readLine();

    String[] outputIntsAsStrings = sliceInput(numbersString);
    
    if(outputIntsAsStrings == null) return null;    

    Vector<String> phonesAll = createValidSubStrings(outputIntsAsStrings);
    phonesAll = validateSubStrings(phonesAll);
    
    return phonesAll;
    /*
    for(int i = 0; phonesAll!=null && i<phonesAll.size(); i++){
      System.out.println(phonesAll.toArray()[i]);
    }
    */

  }

} 
