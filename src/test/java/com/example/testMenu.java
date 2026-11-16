package com.example;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testMenu {
    @Test 
    public void testValidMenuInput() {
        String testInput = "1\n"; // this is a valid input that will be tested
        //this creates a scanner that reads from the testInput rather than taking an input from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(1, result);
        
    }

    @Test 
    public void testInvalidMenuInput() {
        String testInput = "100\n3\n"; // this is a invalid input that won't be accepted, so the user is prompted to enter a valid input which is accepted
        //this creates a scanner that reads from the testInput rather than taking an input from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(3, result);
    }

    @Test 
    public void testInvalidBoundaryMenuInput() {
        String testInput = "0\n9\n4\n"; // testing the boundary invalid inputs, which shouldn't e accepted until the user enters a valid input
        //this creates a scanner that reads from the testInput rather than taking an input from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(4, result);
    }

    @Test 
    public void testInvalidNonIntegerMenuInput(){
        String testInput = "abc\n3\n"; // testing the invalid non-integer input
        //this creates a scanner that reads from the testInput rather than taking an input from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(3, result);
    }



    @Test 
    public void testWhichMenuOptionSelected() { // this test that the switch case is working correctly and if the user enters 2, case 2 is ran
        String testInput = "2\n8\n"; // tests menu option 2 is chosen then option 8 to close the program
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        java.io.ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable
        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("You have no modules, please add a modue first"));

        } finally {
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }


    //test menu option 1
    @Test 
    public void testAddModule() {
        String testInput = "1\ncoding\n101\n8\n"; // tests menu option 1 is chosen then they create a module and then option 8 is chosen to close the program
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        java.io.ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable
        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("Module coding(101) has been added"));

        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }

    //Test menu option 2 
    //Test adding an assignmnet to a module using all valid inputs
    @Test 
    public void testAddAssignment() {
        String testInput = "1\ncoding\n101\ncoding project\n03\03\2027\n8\n"; // tests menu option 1 is chosen then they create a module and then option 8 is chosen to close the program
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        java.io.ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable
        try {
            Main.main(new String[] {});
            String result = output.toString();
            Assertions.assertTrue(result.contains("Module coding(101) has been added"));
        } finally { 
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }

    
}

