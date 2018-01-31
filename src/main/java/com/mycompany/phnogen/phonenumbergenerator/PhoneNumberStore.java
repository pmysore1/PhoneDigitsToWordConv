/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phnogen.phonenumbergenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author prade
 */
public class PhoneNumberStore {
    private char char1 ;
    private char char2 ;
    private char char3 ;
    private HashMap<Integer, String> digitToWordMap ;
    
    public PhoneNumberStore()
    {
        digitToWordMap = new HashMap<Integer, String>();
        digitToWordMap.put(2, "abc");
        digitToWordMap.put(3, "def");
        digitToWordMap.put(4, "ghi");
        digitToWordMap.put(5, "jkl");
        digitToWordMap.put(6, "mno");
        digitToWordMap.put(7, "pqrs");
        digitToWordMap.put(8, "tuv");
        digitToWordMap.put(9, "wxyz");
        digitToWordMap.put(0, "0");
        digitToWordMap.put(1, "1");
    }
    public List<String> getAlphaNumericPhoneNoAll(String phoneDigits)
    {
        int phoneDigitSize = phoneDigits.length() ;
        String before ;
        String after ;
        String newString ;
        ArrayList<String> result ;
        Set<String> hs = new HashSet<>();
        
        result = (ArrayList<String>)getAlphaNumericPhoneNo(phoneDigits) ;
        
        for(int i=0; i < phoneDigitSize; i++)
        {
            newString = phoneDigits.substring(phoneDigitSize-i-1) ;
            before = phoneDigits.substring(0, phoneDigitSize-i-1) ;
            after = phoneDigits.substring(phoneDigitSize-i) ;
            //System.out.println("before:" +before+ ",newString: " + newString + ", After:"+after);
            if(!after.equals(""))
                result = (ArrayList<String>)getAlphaNumericPhoneNo(before, newString, result) ;
        }
        //Remove duplicates 
        hs.addAll(result);
        result.clear();
        result.addAll(hs);
        return result ;
    }
    public List<String> getAlphaNumericPhoneNo(String phoneDigits)
    {
         ArrayList<String> alphaNumericList = new ArrayList<String>();
         ArrayList<String> alphaNumericListNew = new ArrayList<String>();
         int processingCount=0 ;
         
         if(phoneDigits == null || phoneDigits.length() == 0)
            return alphaNumericList;
         ArrayList<Character> temp = new ArrayList<Character>();
         int phoneDigitSize = phoneDigits.length() ;
         //System.out.println("phoneDigitSize: " + phoneDigitSize);
         char charDigit[] = new char[1];
         String before;
         String after;
         int numberofChar =1 ;
        
           
        for(int i=0; i < phoneDigitSize; i++)
        {
             charDigit[0] = phoneDigits.charAt(phoneDigitSize-i-1) ;
           
             //System.out.println("Processing character : " + new String(charDigit));
             before = phoneDigits.substring(0, phoneDigitSize-i-1) ;
             //System.out.println("Before String : " + before);
             after = phoneDigits.substring(phoneDigitSize-i) ;
             //System.out.println("After String : " + after);     
             populatePhoneWords(new String(charDigit), temp, alphaNumericList, digitToWordMap, before,after );

         } 
         
      
         return alphaNumericList;
        
    }
    public List<String> getAlphaNumericPhoneNo(String prefix, String phoneDigits, ArrayList<String> alphaNumericList)
    {
         //ArrayList<String> alphaNumericList = new ArrayList<String>();
         ArrayList<String> alphaNumericListNew = new ArrayList<>();
         
         
         if(phoneDigits == null || phoneDigits.length() == 0)
            return alphaNumericList;
         ArrayList<Character> temp = new ArrayList<>();
         int phoneDigitSize = phoneDigits.length() ;
         //System.out.println("phoneDigitSize: " + phoneDigitSize);
        
         populatePhoneWords(phoneDigits, temp, alphaNumericList, digitToWordMap, prefix );
         return alphaNumericList;
        
    }
     
    public void populatePhoneWords(String phoneDigits, ArrayList<Character> temp, ArrayList<String> result,  HashMap<Integer, String> map, String prefix){
        
        int tempLength=temp.size() ;
        
        if(phoneDigits.length() == 0){
            char[] arr = new char[tempLength];
            for(int i=0; i<tempLength; i++){
                arr[i] = temp.get(i);
            }
            result.add(prefix+String.valueOf(arr));
            return;
        }

        Integer curr = Integer.valueOf(phoneDigits.substring(0,1));
        String letters = map.get(curr);
        for(int i=0; i<letters.length(); i++){
            temp.add(letters.charAt(i));
            populatePhoneWords(phoneDigits.substring(1), temp, result, map, prefix);
            temp.remove(temp.size()-1);
        }
    }
     
    public void populatePhoneWords(String phoneDigits, ArrayList<Character> temp, ArrayList<String> result,  HashMap<Integer, String> map){
        
        int tempLength=temp.size() ;
        
        if(phoneDigits.length() == 0){
            char[] arr = new char[tempLength];
            for(int i=0; i<tempLength; i++){
                arr[i] = temp.get(i);
            }
            result.add(String.valueOf(arr));
            return;
        }

        Integer curr = Integer.valueOf(phoneDigits.substring(0,1));
        String letters = map.get(curr);
        for(int i=0; i<letters.length(); i++){
            temp.add(letters.charAt(i));
            populatePhoneWords(phoneDigits.substring(1), temp, result, map);
            temp.remove(temp.size()-1);
        }
    }
    
     public void populatePhoneWords(String phoneDigits, 
             ArrayList<Character> temp, 
             ArrayList<String> result,  
             HashMap<Integer, String> map, 
             String before, 
             String after){
        
        int tempLength=temp.size() ;
        
        if(phoneDigits.length() == 0){
            char[] arr = new char[tempLength];
            for(int i=0; i<tempLength; i++){
                arr[i] = temp.get(i);
            }
            result.add(before+String.valueOf(arr)+after);
            return;
        }

        Integer curr = Integer.valueOf(phoneDigits.substring(0,1));
        String letters = map.get(curr);
        for(int i=0; i<letters.length(); i++){
            temp.add(letters.charAt(i));
            populatePhoneWords(phoneDigits.substring(1), temp, result, map, before, after);
            temp.remove(temp.size()-1);
        }
    }

    
}
