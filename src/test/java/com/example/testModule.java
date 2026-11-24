package com.example;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testModule {

    // tests that the modules are set up correctly with the correct attributes
    @Test 
    public void testModuleConstructor(){
        Module theModule = new Module("Test Module", "123");
        Assertions.assertEquals("Test Module", theModule.getModuleName());

        Assertions.assertEquals("123", theModule.getModuleCode());

    }
    // tests that you can add an assignment to a module
    @Test 
    public void testAddAssignmnet() {
        //  creates test module and assignment
        Module theModule = new Module("Test Module", "123");
        Assignment theAssignment = new Assignment("Test Assignment");
        // assigns the assignment to the module
        theModule.addAssignment(theAssignment);
        Assertions.assertEquals(1, theModule.getModuleAssignments().size()); // checks that the assignment has been added to that module
        Assertions.assertEquals(theAssignment, theModule.getModuleAssignments().get(0)); // checks that the assignment is correctly added with the right attributes

    }
    
    @Test  // tests that the displayAssignment method handles when the user has no assignnmets in a module
    public void testDisplayAssignmentsNoAssignments(){
        Module theModule = new Module("Test Module", "123");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); // sets whatever is outputted to be stored by the variable output 


        try {
            theModule.displayAssignments();
            String result = output.toString();
            Assertions.assertTrue(result.contains("No assignments have been added"));
        } finally{
            // sets the system output back to the terminal 
            System.setOut(origionalOutput);
        }
    }

     @Test  // tests that the displayAssignment method handles when the user has no assignmnets in a module
    public void testDisplayNotCompletedAssignmentsNoAssignments(){
        Module theModule = new Module("Test Module", "123");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        java.io.PrintStream origionalOutput = System.out; // stores the origional output section
        System.setOut(new java.io.PrintStream(output)); // sets whatever is outputted to be stored by the variable output 


        try {
            theModule.displayNotCompletedAssignments();
            String result = output.toString();
            Assertions.assertTrue(result.contains("No assignments have been added"));
        } finally{
            // sets the system output back to the terminal 
            System.setOut(origionalOutput);
        }
    }




}
