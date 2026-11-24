package com.example;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testManager {
    @Test 
    public void testValidMenuInput() {

        String testInput = "1\n"; // this is a valid input that will be tested
        java.io.InputStream origionalInput = System.in;

        try {
             System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
             Manager manager = new Manager();
             int result = manager.getMenuInput();
             // this checks that the input is equal to the expected output
             Assertions.assertEquals(1, result);
        
        } finally {
            System.setIn(origionalInput);
        }
        
    }

    @Test 
    public void testInvalidMenuInput() {

        
        String testInput = "100\n3\n"; // this is a invalid input that won't be accepted, so the user is prompted to enter a valid input which is accepted
        java.io.InputStream origionalInput = System.in;

        try {
             System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
             Manager manager = new Manager();
             int result = manager.getMenuInput();
             // this checks that the input is equal to the expected output
             Assertions.assertEquals(3, result);
        
        } finally {
            System.setIn(origionalInput);
        }

    }

    @Test 
    public void testInvalidBoundaryMenuInput() {

        String testInput = "0\n9\n4\n"; // testing the boundary invalid inputs, which shouldn't e accepted until the user enters a valid input
        java.io.InputStream origionalInput = System.in;

        try {
             System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
             Manager manager = new Manager();
             int result = manager.getMenuInput();
             // this checks that the input is equal to the expected output
             Assertions.assertEquals(4, result);
        
        } finally {
            System.setIn(origionalInput);
        }

    }

    @Test 
    public void testInvalidNonIntegerMenuInput(){

        String testInput = "abc\n3\n"; // testing the invalid non-integer input
        java.io.InputStream origionalInput = System.in;

        try {
             System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
             Manager manager = new Manager();
             int result = manager.getMenuInput();
             // this checks that the input is equal to the expected output
             Assertions.assertEquals(3, result);
        
        } finally {
            System.setIn(origionalInput);
        }
    }



    @Test 
    public void testWhichMenuOptionSelected() { // this test that the switch case is working correctly and if the user enters 2, case 2 is ran

        String testInput = "2\n8\n"; // tests menu option 2 is chosen then option 8 to close the program
        java.io.InputStream origionalInput = System.in;
        Manager manager = new Manager();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
       

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
             System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("You have no modules, please add a modue first"));

        } finally {
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }
    }



    @Test 
    public void testAddModule() {
        
        String testInput = "1\ncoding\n101\n8\n"; // tests menu option 1 is chosen then they create a module and then option 8 is chosen to close the program

        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out;

        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable

        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("Module coding(101) has been added"));

        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(origionalOutput);
        }
    }

    //Test menu option 2 
    //Test adding an assignmnet to a module using all valid inputs
    @Test 
    public void testAddAssignment() {

        String testInput = "1\ncoding\n101\ncoding project\n03\03\2027\n8\n"; // tests menu option 1 is chosen then they create a module and then option 8 is chosen to close the program
        
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable

        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("Module coding(101) has been added"));
        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(origionalOutput);
        }
    }

    // test that menu 4 deals with if there is no modules added
    @Test 
    public void testMenu4NoModules() {
         String testInput = "4\n8\n"; // choses menu option 4 and then 8 to close the program

        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out;

        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable

        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("There are no modules to select, please add one first"));

        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(origionalOutput);
        }
    }
}


