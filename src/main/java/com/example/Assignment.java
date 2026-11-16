package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Assignment{
    String title;
    String deadline;
    String priority;
    String status;
    Boolean completed;

    public Assignment (String title){
        this.title = title;
        this.status = "not started";
        this.completed = false;
    }
    
    public String getTitle(){
        return title;
    }

    public String getDeadline(){
        return deadline;
    }

    public String getPriority(){
        return priority;
    }
    
    public String getStatus(){
        return status;
    }

    public Boolean getCompleted(){
        return completed;
    }

    public boolean isDateValid(String deadlineDate){

        String dateFormat = "dd/MM/yyyy"; // sets the allowed format 
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate currentDate = LocalDate.now();

        try{ // allows for the user to enter a date that could cause an error ut insteda of crashing a program, it handles the error and allows it to continue
            LocalDate date = LocalDate.parse(deadlineDate, dateFormatter);
            if (date.isBefore(currentDate)){
                System.out.println("The date cannot be in the past, enter the assignment deadline in the format dd/mm/yyyy ");
                return false;
            } else {
                this.deadline = deadlineDate;
                return true;
            }
        } catch (Exception e){ // only trigger when its in the wrong format 
            System.out.println("Invalid date format, enter the assignment deadline in the format dd/mm/yyyy");
            return false;
        }
    }

    public boolean setStatus(String newStatus){
        if (newStatus.equalsIgnoreCase("in progress")|| newStatus.equalsIgnoreCase("needs review") || newStatus.equalsIgnoreCase("need assistance") || newStatus.equalsIgnoreCase("completed") || newStatus.equalsIgnoreCase("missed deadline")){
             this.status = newStatus;
             return true;
        } else{
            return false;
        }
    }

    public void setCompleted(){
        String dateFormat = "dd/MM/yyyy"; // sets the allowed format 
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate currentDate = LocalDate.now();
        LocalDate deadlineDate = LocalDate.parse(this.deadline, dateFormatter);
        if (this.status.equalsIgnoreCase("completed")){
            this.completed = true;
        } else if (currentDate.isAfter(deadlineDate)){
            this.status = "missed deadline";
            this.completed = true;
        } else {
            this.completed = false; 

        }
    }

    


}
