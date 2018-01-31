/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phnogen.phonenumbergenerator;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prade
 */
public class PhoneWordGenTest {
    
    public PhoneWordGenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
     @Test
    public void testForAllOneNumbers() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        PhoneNumberStore phoneNumberStore = new PhoneNumberStore() ;
        ArrayList<String> result = (ArrayList<String>)phoneNumberStore.getAlphaNumericPhoneNoAll("1111111") ;
        
        assertEquals("All phone digits with 1", result.size(), 1);
    }
     @Test
    public void testForTenDigitsNo7() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        PhoneNumberStore phoneNumberStore = new PhoneNumberStore() ;
        ArrayList<String> result = (ArrayList<String>)phoneNumberStore.getAlphaNumericPhoneNoAll("2234234444") ;
        
        assertEquals("All phone digits with 1", result.size(), 88599);
    }
     @Test
    public void testForTenDigitsWith7() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        PhoneNumberStore phoneNumberStore = new PhoneNumberStore() ;
        ArrayList<String> result = (ArrayList<String>)phoneNumberStore.getAlphaNumericPhoneNoAll("7344445666") ;
        
        assertEquals("All phone digits with 1", result.size(), 108283);
    }
     @Test
    public void testFor7DigitsWith7() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        PhoneNumberStore phoneNumberStore = new PhoneNumberStore() ;
        ArrayList<String> result = (ArrayList<String>)phoneNumberStore.getAlphaNumericPhoneNoAll("7344445") ;
        
        assertEquals("All phone digits with 1", result.size(), 4027);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
