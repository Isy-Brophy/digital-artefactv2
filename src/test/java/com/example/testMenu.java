package com.example;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testMenu {
    @Test 
    public void testValidMenuInput() {
        String testInput = "1";
        //this creates a scanner that reads from the testInput rather than taking an inuot from the user
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.getMenuInput(scanner);
        // this checks that the input is equal to the expected output
        Assertions.assertEquals(1, result);
        
    }
}

