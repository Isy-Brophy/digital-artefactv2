package com.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private Scanner scanner;

    public Manager(){
        this.scanner = new Scanner(System.in);
    }

    public void displayAllModules(ArrayList<Module> modulesList){ // prints out all the modules and their module code
        if (modulesList.isEmpty()){
            System.out.println("You have no modules, please add a modue first");
        } else{
            
            for (int i = 0; i < modulesList.size(); i++){
                Module theModule = modulesList.get(i);
                System.out.println((i+1) + ". " + theModule.getModuleName() + " (" + theModule.getModuleCode() + ")");
            }
        }
        
    }

    public  Module selectModule(ArrayList<Module> modulesList){ // allows the user to select a module
        if (modulesList.isEmpty()){
            System.out.println("There are no modules to select, please add one first");
            return null;
        } else {
            Module chosenModule = null;
            while (chosenModule == null){
                try{ // allows for the code to handle the user entering a non integer 
                    System.out.println("Select a module");
                    displayAllModules(modulesList);
                    int choose = this.scanner.nextInt();
                    this.scanner.nextLine();
                    // ensures the user chooses a valid number
                    while (choose <= 0 || choose > modulesList.size()){
                        System.out.println("Please choose a valid module number");
                        choose = this.scanner.nextInt();
                        this.scanner.nextLine();
                    }

                    chosenModule = modulesList.get(choose-1);
                } catch (Exception e){
                    System.out.println("Please enter a valid number");
                    this.scanner.nextLine();
                }
            }
            return chosenModule;

        }
    }
   
    public Assignment selectAssignment(Module chosenModule){ // allows the user to select an assignment in a module
        if (chosenModule.getModuleAssignments().isEmpty()){
            System.out.println("There are no assignments in this module");
            return null;
        } else{

            chosenModule.displayAssignments();
            Assignment chosenAssignment = null;
            while (chosenAssignment == null){
                try { // allows for the code to handle the user entering a upon integer
                    System.out.println("select an assignment");
                    int num = this.scanner.nextInt();
                    this.scanner.nextLine();

                    // ensures the user chooses a valid number
                    while (num <= 0 || num > (chosenModule.getModuleAssignments().size())){
                        System.out.println("Please choose a valid module number");
                        num = this.scanner.nextInt();
                        this.scanner.nextLine();
                    }
                    chosenAssignment = chosenModule.getModuleAssignments().get(num-1);
                } catch (Exception e) {
                    System.out.println("Please enter a valid number");
                    this.scanner.nextLine();
                }
            }
            return chosenAssignment;
        }
    }

    public void addNewModule(ArrayList<Module> modulesList){
        // creates an instance of the module class and adds it to the moduleList
        System.out.println("Please enter the module name: ");
        String moduleName = this.scanner.nextLine();
        System.out.println("Please enter the module code: ");
        String moduleCode = this.scanner.nextLine();
        Module newModule = new Module(moduleName, moduleCode);
        modulesList.add(newModule);
        System.out.println("=================================");
        System.out.println("Module " + moduleName + "(" +moduleCode + ") has been added");
        System.out.println("=================================");
    }
    
    public  void addNewAssignment( ArrayList<Module> modulesList){
        // creates an instance of the assignment class and adds it to the attribute moduleAssignments which is a list that contains all of the assignments for that module
        Module chosenModule = selectModule(modulesList);
        if (chosenModule != null){
            System.out.println("Enter your assignment title: ");
            String theTitle = this.scanner.nextLine();
            Assignment newAssignment = new Assignment(theTitle);

            // adds the assignment to the chosen module's assignment list
            chosenModule.addAssignment(newAssignment);

            System.out.println("Enter the assignment deadline in the format dd/mm/yyyy : ");

            String theDeadline;
            
            // ensures that the user can only enter a valid date
            do{ 
                theDeadline = this.scanner.nextLine();
            } 
            while (newAssignment.isDateValid(theDeadline)!= true);

            
            System.out.println("Enter the priority: high/medium/low");
            String thePriority;

            // ensures that the user can only enter a valid priority
            do{
                thePriority = this.scanner.nextLine();
            }
            while (newAssignment.isPriorityValid(thePriority)!= true);


        }
    } 

    public void displayEverySingleAssignment( ArrayList<Module> modulesList){
        if (modulesList.isEmpty() ){
            System.out.println("You don't have any modules or assignments, please add a module first and add your assignments");
        } else {
            for (int i = 0; i< modulesList.size(); i++){
                Module theModule = modulesList.get(i);
                System.out.println("Module: " + theModule.getModuleName() + " (" + theModule.getModuleCode() + ")");
                theModule.displayAssignments();
            }
        }
    }

    public  void updateStatus( ArrayList<Module> modulesList ){
        System.out.println("Choose the module that contains the assignment you want to update");
        // allows the user to choose a module
        Module chosenModule = selectModule(modulesList);
        
        if (chosenModule != null){
            // allows the user to choose an assignment in the chosen module
            Assignment chosenAssignment = selectAssignment(chosenModule);
            if (chosenAssignment != null){
                System.out.println("Select an assignment that you want to oupdate the status of");

                System.out.println("What would you like to change the status to? in progress/needs review/need assistance/completed");
                String newStatus = this.scanner.nextLine();
                
                // ensures that the user chooses a valid status
                while (chosenAssignment.setStatus(newStatus)!= true){
                    System.out.println("Please choose a valid status? in progress/needs review/need assistance/completed");
                    newStatus = this.scanner.nextLine();
                }
            }
            
        }
    }
    
    public boolean isAssignments(Module theModule){
        return !theModule.getModuleAssignments().isEmpty(); 
    }
    
    public  void viewUncompletedAssigments(ArrayList<Module> modulesList){
        if (modulesList.isEmpty() ){
            System.out.println("You don't have any modules and assignments, please add a module first and add your assignments");
        } else {
            for (int i = 0; i< modulesList.size(); i++){
                Module theModule = modulesList.get(i);
                if (isAssignments(theModule)){
                    System.out.println("Module: " + theModule.getModuleName() + " (" + theModule.getModuleCode() + ")");
                    theModule.displayNotCompletedAssignments();
                } else {
                    System.out.println("You have no assignments");
                }

            }
        }
    }

    public  int getMenuInput(){
        // this loops until a valid input is given
        while (true){
            // this handles the user entering a non integer
            try {
                //takles in the users input
                int input = this.scanner.nextInt();
                this.scanner.nextLine();

                //checks if the users input is valid - it must be between 1 and 8
                while (input<=0 || input >= 9){
                    System.out.println("Please enter a number between 1-8");
                    input = this.scanner.nextInt();
                    this.scanner.nextLine();
                }
                return input;
                
            } catch (Exception e) {
                System.out.println("Please enter a valid number between 1-8");
                this.scanner.nextLine();
            }

        }
    }

}
