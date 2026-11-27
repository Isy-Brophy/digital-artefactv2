package com.example;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testManager {
    @Test  // TC-001
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

    @Test // TC-002
    public void testInvalidMenuInput() {

        
        String testInput = "10\n3\n"; // this is a invalid input that won't be accepted, so the user is prompted to enter a valid input which is accepted
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
            System.setOut(new java.io.PrintStream(output)); 
            Manager manager = new Manager();
            int result = manager.getMenuInput();
            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertEquals(3, result);
            Assertions.assertTrue(outputText.contains("Please enter a number between 1-8"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }

    }

    @Test //TC-002
    public void testInvalidBoundaryMenuInput() {

        String testInput = "0\n9\n4\n"; // testing the boundary invalid inputs, which shouldn't e accepted until the user enters a valid input
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
            System.setOut(new java.io.PrintStream(output)); 
            Manager manager = new Manager();
            int result = manager.getMenuInput();
            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertEquals(4, result);
            Assertions.assertTrue(outputText.contains("Please enter a number between 1-8"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }

    }

    @Test //TC-003
    public void testInvalidNonIntegerMenuInput(){

        String testInput = "abc\n3\n"; // testing the invalid non-integer input
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
            System.setOut(new java.io.PrintStream(output)); 
            Manager manager = new Manager();
            int result = manager.getMenuInput();
            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertEquals(3, result);
            Assertions.assertTrue(outputText.contains("Please enter a valid number between 1-8"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }
    }

    @Test //TC-004
    public void testAddNewModule(){
        String testInput = "coding\n123\n"; // testing the invalid non-integer input
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
            System.setOut(new java.io.PrintStream(output)); 
            Manager manager = new Manager();
            manager.addNewModule();
            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertEquals(1, manager.getModulesList().size());
            Assertions.assertTrue(outputText.contains("Module coding(123) has been added"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }

    }

    // tests that the module prints out the modules
    @Test // TC-005
    public void testDisplayAllModules(){
        String testInput = "coding\n123\n";
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

         try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            System.setOut(new java.io.PrintStream(output)); 

            Manager manager = new Manager();
            manager.addNewModule();
            manager.displayAllModules();

            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertTrue(outputText.contains("1. coding (123)"));
        
        } finally {

            System.setOut(origionalOutput);
            System.setIn(origionalInput);
        }
    }

    // tests that the module prints out the modules
    @Test // TC-006
    public void testDisplayAllModulesMultipleModules(){
        String testInput = "coding\n123\ndatabases\n124\nnetworks\n125\n";
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            System.setOut(new java.io.PrintStream(output)); 

            Manager manager = new Manager();
            manager.addNewModule();
            manager.addNewModule();
            manager.addNewModule();
            manager.displayAllModules();

            String outputText = output.toString();
            // this checks that the input is equal to the expected output
            Assertions.assertTrue(outputText.contains("1. coding (123)"));
            Assertions.assertTrue(outputText.contains("2. databases (124)"));
            Assertions.assertTrue(outputText.contains("3. networks (125)"));
        
        } finally {

            System.setOut(origionalOutput);
            System.setIn(origionalInput);
        }
    }
    
    // test that the function is able to handle when a user calls this and there are no modules tp print out 
    @Test //TC-007
    public void testDisplayAllModulesNoModules(){
         ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section

         try {
             System.setOut(new java.io.PrintStream(output)); 

             Manager manager = new Manager();
             manager.displayAllModules();

             String outputText = output.toString();
             // this checks that the input is equal to the expected output
             Assertions.assertTrue(outputText.contains("You have no modules, please add a modue first"));
        
        } finally {

            System.setOut(origionalOutput);
        }
    }

//test selectmodule using a valid input
    @Test //TC-008
    public void testSelectModule(){
        String testInput = "coding\n123\n2\n1\n";
        java.io.InputStream origionalInput = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
             Manager manager = new Manager();
             manager.addNewModule();
             Module result = manager.selectModule();
             // this checks that the input is equal to the expected output
             Assertions.assertEquals("coding", result.getModuleName());
        
        } finally {
            System.setIn(origionalInput);
        }
    }
    
    //test selectmodule using an invalid input
    @Test //TC-009
    public void testSelectModuleInvalidInput(){
        String testInput = "coding\n123\n2\n1\n";
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
             Manager manager = new Manager();
             manager.addNewModule();
             manager.selectModule();
             String outputText = output.toString();
             // this checks that the input is equal to the expected output
             Assertions.assertTrue(outputText.contains("Please choose a valid module number"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }
    }

    //test selectmodule using an invalid input
    @Test //TC-010
    public void testSelectModuleInvalidTypeInput(){
        String testInput = "coding\n123\nabc\n1\n";
        java.io.InputStream origionalInput = System.in;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
             Manager manager = new Manager();
             manager.addNewModule();
             manager.selectModule();
             String outputText = output.toString();
             // this checks that the input is equal to the expected output
             Assertions.assertTrue(outputText.contains("Please enter a valid number in integer format"));
        
        } finally {
            System.setIn(origionalInput);
            System.setOut(origionalOutput);
        }
    }

    // tests that the sleectModule() function can handle being called when the user has no modules, and warn the user of this
    @Test //TC-011
    public void testSelectModuleNoModules(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 
        try {
            Manager manager = new Manager();
            manager.selectModule();
            String outputText = output.toString();
            Assertions.assertTrue(outputText.contains("There are no modules to select, please add one first"));

        } finally {
            System.setOut(origionalOutput);
        }
    }

    @Test 
    public void testWhichMenuOptionSelected() { // this test that the switch case is working correctly and if the user enters 2, case 2 is ran

        String testInput = "2\n8\n"; // tests menu option 2 is chosen then option 8 to close the program
        java.io.InputStream origionalInput = System.in;

        
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



    //Test menu option 2 
    //Test adding an assignmnet to a module using all valid inputs
    @Test // TC-012
    public void testAddNewAssignment() {

        String testInput = "1\ncoding\n123\n3\n1\njava project\n01/01/2028\nmedium\n8\n"; // tests menu option 1 is chosen then they create a module and then option 8 is chosen to close the program
        
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable

        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("Assignment java project has been added to module coding(123)"));
        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(origionalOutput);
        }
    }

    // test that menu 4 deals with if there is no modules added
    @Test //TC-022
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

    @Test //TC-021
    public void testMenu4() {
         String testInput = "1\ncoding\n123\n3\n1\njava project\n01/01/2028\nhigh\n4\n1\n8\n"; // the user had created a module and assignmnet, and then chooses menu option 4 and the module to see its assignmnets

        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out;

        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable

        try {
            Main.main(new String[] {});
            String result = output.toString();
            //checks that it prints out all the correct attributes
            Assertions.assertTrue(result.contains("java project"));
            Assertions.assertTrue(result.contains("Deadline: 01/01/2028"));
            Assertions.assertTrue(result.contains("Priority: high"));
            Assertions.assertTrue(result.contains("Status: not started"));


        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(origionalOutput);
        }
    }

}


