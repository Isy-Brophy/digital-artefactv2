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
        //this creates a scanner that reads from the testInput rather than taking an inuot from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(1, result);
        
    }

    @Test 
    public void testInvalidMenuInput() {
        String testInput = "100\n3\n"; // this is a invalid input that won't be accepted, so the user is prompted to enter a valid input which is accepted
        //this creates a scanner that reads from the testInput rather than taking an inuot from the user
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
    public void testCorrectMenuOptionSelected() {
        String testInput = "2\n8\n"; // tests menu option 2 is chosen then option 8 to close the program
        System.setIn(new ByteArrayInputStream(testInput.getBytes())); // sets the system input to the testInput 
        java.io.ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output)); // sets the system output to the output variable
        try {
            studentAssignmentManager.main(new String[] {});
            String result = output.toString();
            assertTrue(result.contains("you have no modules, please add a moduke first"));

        } finally {
            // resets the inputs and outputs to the keyboard and terminal
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }
    
}

