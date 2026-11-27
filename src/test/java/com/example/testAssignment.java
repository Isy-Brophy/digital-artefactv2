package com.example;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testAssignment {
    

    // tests that the assignments are set up correctly with the correct attributes
    @Test 
    public void testAssignmentConstructor(){
        Assignment theAssignment = new Assignment("Test assignment");
        Assertions.assertEquals("Test assignment", theAssignment.getTitle());
        Assertions.assertNull(theAssignment.getDeadline());
        Assertions.assertNull(theAssignment.getPriority());
        Assertions.assertEquals("not started", theAssignment.getStatus());
        Assertions.assertFalse(theAssignment.getCompleted());

    }

    // tests that the isDateValid method allows the user to enter a date in the correct format and sets the deadline attriute to it, if the date is today
    @Test //TC-013
    public void testIsDateValidWithValidDateNow(){


        Assignment theAssignment = new Assignment("Test assignment");

        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        Boolean result = theAssignment.isDateValid(todayDate);
        //tests todays date is valid
        Assertions.assertTrue(result);
        // checks that the deadline attribute is correctly updated
        Assertions.assertEquals(todayDate, theAssignment.getDeadline());
    

    }

    // tests that the isDateValid method allows the user to enter a date in the correct format and sets the deadline attriute to it, if the date is in the future
    @Test //TC-013.5
    public void testIsDateValidWithValidDateFuture(){ 
        Assignment theAssignment = new Assignment("Test assignment");

        //tests a furure date is valid
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        Boolean result = theAssignment.isDateValid(futureDate);


        //tests the future date is valid
        Assertions.assertTrue(result);
        // checks that the deadline attribute is correctly updated
        Assertions.assertEquals(futureDate, theAssignment.getDeadline());

    }

    // tests that it doesn't allow dates in the past
    @Test //TC-016
    public void testIsDateValidWithInvalidDatePast(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 
        try {
            Assignment theAssignment = new Assignment("Test assignment");

            // tests that the function doesn't allow the user to enter a date in the past
            String pastDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
            Boolean result = theAssignment.isDateValid(pastDate);
            String outputText = output.toString();
            // tests the past date is invalid
            Assertions.assertFalse(result);
            Assertions.assertNull(theAssignment.getDeadline());
            Assertions.assertTrue(outputText.contains("The date cannot be in the past, enter the assignment deadline in the format dd/mm/yyyy"));
        
        } finally {
            System.setOut(origionalOutput);
        }

    }

    // tests that it doesn't allow dates in the wrong format
    @Test //TC=015
    public void testIsDateValidWithInvalidDateFormat(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 

        try{
            Assignment theAssignment = new Assignment("Test assignment");
            String invalidFormateDateText = "1st january";
            Boolean result = theAssignment.isDateValid(invalidFormateDateText);
            Assertions.assertFalse(result);
            String outputText = output.toString();
            Assertions.assertNull(theAssignment.getDeadline());
            Assertions.assertTrue(outputText.contains("Invalid date format, enter the assignment deadline in the format dd/mm/yyyy"));

        } finally {
            System.setOut(origionalOutput);
        }


    }

    // tests that it doesn't allow dates in the wrong format
    @Test //TC-014 
    public void testIsDateValidWithInvalidDateFormatText(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 

        try{
            Assignment theAssignment = new Assignment("Test assignment");
            String invalidFormateDateText = "1st january";
            Boolean result = (theAssignment.isDateValid(invalidFormateDateText));
            String outputText = output.toString();
            Assertions.assertFalse(result);
            Assertions.assertNull(theAssignment.getDeadline());
            Assertions.assertTrue(outputText.contains("Invalid date format, enter the assignment deadline in the format dd/mm/yyyy"));
        } finally {
            System.setOut(origionalOutput);
        }
    }

    // tests that it doesn't allow dates that don't exist in the calendar
    @Test //TC-017
    public void testIsDateValidWithInvalidDateNonexistant(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); 

        try {
            Assignment theAssignment = new Assignment("Test assignment");

            // tests that dates that don't exist are invalid
            String invalidDate = "31/02/2027";
            Boolean result = theAssignment.isDateValid(invalidDate);
            String outputText = output.toString();
            Assertions.assertFalse(result);
            Assertions.assertNull(theAssignment.getDeadline());
            Assertions.assertTrue(outputText.contains("Invalid date format, enter the assignment deadline in the format dd/mm/yyyy"));
        } finally{
            System.setOut(origionalOutput);
        }

    }

    //tests isValidPriority with valid inputs
    @Test //TC-018
    public void testIsPriorityValid(){
        Assignment theAssignment = new Assignment("Test assignment");

        String validInput = "high";
        //tests if a valid input is accepted is valid
        Assertions.assertTrue(theAssignment.isPriorityValid(validInput));
        // checks that the priority attribute is correctly updated
        Assertions.assertEquals(validInput, theAssignment.getPriority());

        //test with all caps valid input
        String validInput2 = "MEDIUM";
        Assertions.assertTrue(theAssignment.isPriorityValid(validInput2));
        // checks that the priority attribute is correctly updated
        Assertions.assertEquals(validInput2, theAssignment.getPriority());
    }

    // tests an invalid input is rejected by isPriorityValid
    @Test //TC-019
    public void testIsPriorityValidInvalidInput(){
        Assignment theAssignment = new Assignment("Test assignment");

        String invalidInput = "mid";

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); // sets whatever is outputted to be stored by the variable output 

        try {
            //tests if a valid input is accepted is valid
            Assertions.assertFalse(theAssignment.isPriorityValid(invalidInput));
            // checks that the priority attribute isn't
            Assertions.assertNull(theAssignment.getPriority());
            String result = output.toString();
            Assertions.assertTrue(result.contains("Invalid priority, please enter one of the following : high, medium, low"));
        } finally{
            // sets the system output back to the terminal 
            System.setOut(origionalOutput);
        }
    }
    
    //checks that setStatus returns true and updates the attribute if a valid inputs given
    @Test //TC-028
     public void testSetStatusValidInput(){
        Assignment theAssignment = new Assignment("Test assignment");
        String validInput = "in progress";
        Assertions.assertTrue( theAssignment.setStatus(validInput)); // checks that it returns true as its a valid input
        Assertions.assertEquals(validInput, theAssignment.getStatus()); // checks that the status attribute is correctly updated
    }

    //checks that setStatus returns true and updates the attribute if a valid inputs given
    @Test //TC-030
    public void testSetStatusInvalidInput(){
        Assignment theAssignment = new Assignment("Test assignment");
        String invalidInput = "finished";
        Assertions.assertFalse( theAssignment.setStatus(invalidInput)); // checks that it returns false as its an invalid input
        Assertions.assertEquals("not started",theAssignment.getStatus()); // checks that the status attribute isn't updated, and kept to the origional value
    }

    @Test // tests that the completed attribute is correctly updated if the status is set to completed
    public void testSetCompleted(){ 
        Assignment theAssignment = new Assignment("Test Assignment");
        theAssignment.setStatus("completed");
        theAssignment.setDeadline("01/01/2028");
        theAssignment.setCompleted();
        Assertions.assertTrue(theAssignment.getCompleted());
    }

    @Test // tests that the completed attribute is updated to true if the deadlines gone and the user hasn't set the status to completed
    public void testSetCompletedMissedDeadline(){
        Assignment theAssignment = new Assignment("Test Assignment");
        theAssignment.setStatus("in progress");
        theAssignment.setDeadline("01/01/2024"); // deadline in the past
        theAssignment.setCompleted();
        Assertions.assertTrue(theAssignment.getCompleted());
    }

    @Test // test that the completed attribute is set to false if the deadline attribute is in the future and the user hasn't updated the status to completed
    public void testSetCompletedNotCompleted() {
        Assignment theAssignment = new Assignment("Test Assignment");
        theAssignment.setStatus("in progress");
        theAssignment.setDeadline("01/01/2028"); // deadline in the future
        theAssignment.setCompleted();
        Assertions.assertFalse(theAssignment.getCompleted());
    }

}





