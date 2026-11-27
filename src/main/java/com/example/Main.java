package com.example;


public class Main {


    
    public static void main(String[] args){
        Manager  manager = new Manager();

        

        boolean run = true;

        // main menu loop
        while (run== true){
            
            try { // alows for the code to handle non integer inputs
            System.out.println("=================================");
            System.out.println(" STUDENT ASSIGNMENT MANAGER");
            System.out.println("=================================");
            System.out.println("Please select an option:");
            System.out.println("1. Add a new module");
            System.out.println("2. View your modules");            
            System.out.println("3. Add a new assignment");
            System.out.println("4. View all assignments in a module");
            System.out.println("5. View all assignments");
            System.out.println("6. Update an assignment status");
            System.out.println("7. View current assignments");
            System.out.println("8. Exit");

            int input = manager.getMenuInput();
            
            // handles the user choosing an option 
            switch(input)
            {
                case 1: // allows the user to add a new module 
                    // creates an instance of the module class and adds it to the moduleList
                    manager.addNewModule();
                    break;

                case 2: // displays all modules
                    manager.displayAllModules();
                    break;
                
                case 3: // adds a new assignment to a module
                    manager.addNewAssignment();
                    break;

                case 4: // displays all the assignments in a chosen module
                    Module chosenModule = manager.selectModule();
                    if (chosenModule != null){
                        chosenModule.displayAssignments();
                    }
                    break;

                case 5: // displays all of the assignments in all modules
                    manager.displayEverySingleAssignment();
                    break;

                case 6: // updates the status of a chosen assignment 
                    manager.updateStatus( );
                    break;  
                
                case 7: // view all assignments that have not yet been completed/ are before their deadline
                    manager.viewUncompletedAssigments();
                    break;
                
                case 8: // close the program
                    System.out.println("Shutting down the program, goodbye");
                    run = false;
                    break;

            }
            } catch (Exception e){
                System.out.println("there has been an error");
                break;
            }
        }
    }
}
