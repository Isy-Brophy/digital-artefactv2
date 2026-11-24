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
    @Test
    public void testIsDateValidWithValidDateNow(){
        Assignment theAssignment = new Assignment("Test assignment");

        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/mm/uuuu").withResolverStyle(ResolverStyle.STRICT));
        //tests todays date is valid
        Assertions.assertTrue(theAssignment.isDateValid(todayDate));
        // checks that the deadline attribute is correctly updated
        Assertions.assertEquals(todayDate, theAssignment.getDeadline());

    }

    @Test// tests that the isDateValid method allows the user to enter a date in the correct format and sets the deadline attriute to it, if the date is in the future
    public void testIsDateValidWithValidDateFuture(){ 
        Assignment theAssignment = new Assignment("Test assignment");

        //tests a furure date is valid
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/mm/uuuu").withResolverStyle(ResolverStyle.STRICT));
        //tests the future date is valid
        Assertions.assertTrue(theAssignment.isDateValid(futureDate));
        // checks that the deadline attribute is correctly updated
        Assertions.assertEquals(futureDate, theAssignment.getDeadline());

    }




    // tests that it doesn't allow dates in the past
    @Test 
    public void testIsDateValidWithInvalidDatePast(){

        Assignment theAssignment = new Assignment("Test assignment");

        // tests that the function doesn't allow the user to enter a date in the past
        String pastDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        // tests the past date is invalid
        Assertions.assertFalse(theAssignment.isDateValid(pastDate));
        Assertions.assertNull(theAssignment.getDeadline());

    }

    // tests that it doesn't allow dates in the wrong format
    @Test 
    public void testIsDateValidWithInvalidDateFormat(){

        Assignment theAssignment = new Assignment("Test assignment");


        String invalidFormatDate = "2027/03/03";

        // tests that the wrong format is invalid
        Assertions.assertFalse(theAssignment.isDateValid(invalidFormatDate));
        Assertions.assertNull(theAssignment.getDeadline());

        // tests that dates that don't exist are invalid
        String invalidDate = "31/02/2027";
        Assertions.assertFalse(theAssignment.isDateValid(invalidDate));
        Assertions.assertNull(theAssignment.getDeadline());

    }

        // tests that it doesn't allow dates that don't exist in the calendar
    @Test 
    public void testIsDateValidWithInvalidDateNonexistant(){

        Assignment theAssignment = new Assignment("Test assignment");


        // tests that dates that don't exist are invalid
        String invalidDate = "31/02/2027";
        Assertions.assertFalse(theAssignment.isDateValid(invalidDate));
        Assertions.assertNull(theAssignment.getDeadline());

    }

    //tests isValidPriority with valid inputs
    @Test
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
    @Test
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

    @Test //checks that setStatus returns true and updates the attribute if a valid inputs given
    public void testSetStatusValidInput(){
        Assignment theAssignment = new Assignment("Test assignment");
        String validInput = "in progress";
        Assertions.assertTrue( theAssignment.setStatus(validInput)); // checks that it returns true as its a valid input
        Assertions.assertEquals(validInput, theAssignment.getStatus()); // checks that the status attribute is correctly updated
    }

    @Test //checks that setStatus returns true and updates the attribute if a valid inputs given
    public void testSetStatusInvalidInput(){
        Assignment theAssignment = new Assignment("Test assignment");
        String invalidInput = "editing";
        Assertions.assertFalse( theAssignment.setStatus(invalidInput)); // checks that it returns false as its an invalid input
        Assertions.assertNull(theAssignment.getStatus()); // checks that the status attribute isn't updated
    }

    @Test // tests that the completed attribute is correctly updated if the status is set to completed
    public void testSetCompleted(){ 
        Assignment theAssignment = new Assignment("Test Assignment");
        theAssignment.setStatus("completed");
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
        theAssignment.setDeadline("01/01/2025"); // deadline in the future
        theAssignment.setCompleted();
        Assertions.assertFalse(theAssignment.getCompleted());
    }

}





