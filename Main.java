//Main.java
/*

Motivation:
I like going to the gym and would appreciate it if I had a program that organized my workouts. I
would waste less time and can use this on a real-life day-to-day basis. As a result, I can
accomplish my gym goals and submit this Final Project.


Description:
The Gym App is a Java program that allows users to organize 3 types of Gym. Hypertrophy,
Strength, and Power.
Users can put whatever workout is available on either Sunday, Monday,
Tuesday, Wednesday, Thursday, Friday, or Saturday.
 The exercises provided will include
training for the chest, shoulders, arms, back, legs, and abs.
The 3 types of workouts will include
all of these exercises, targeting muscle groups.

The database of all these exercises comes from
strength.csv
power.csv
hyper.csv


It has

Encapsulation
Composition or Aggregation
Inheritance
Abstraction (or Interfaces)
Save the state through CSV files
Makefile (make, make run, make clean, make jar)
UML diagram


to run the makeFile
"make" : to make the class files
"make jar": to contain the complied class files
"make run": to run the files
"make clean": to get rid the class files

Purpose of Main:
It gives the log ins and sign up option
once logged in, it goes to the GymApp.java
 */


 import java.util.Scanner;

 public class Main {
     public static void main(String[] args) {
         @SuppressWarnings("resource")
         Scanner scanner = new Scanner(System.in);
 
         int i = 0;
         while (i <= 0) {
 
             // option to login or sign up
 
             System.out.println("Welcome to the Gym App" +
                     "\nDo you want to log in(1) or sign up(2)?");
             String perChoice = scanner.nextLine();
 
             if (perChoice.equals("1")) {
 
                 // Login
                 System.out.println("What is your username");
                 String logUN = scanner.nextLine();
 
                 System.out.println("What is your password");
                 String logPW = scanner.nextLine();
 
                 // check login credentials
                 if (User.login(logUN, logPW)) {
                     System.out.println("Accepted");
                     i = i + 1;
 
                     // Set username and password
                     User.username = logUN;
                     User.password = logPW;
 
                     // create the user's schedule file if it doesn't exist
                     String fileName = User.username + ".csv";
                     String[] daysOfWeek = {"sunday,null", "\nmonday,null", "\ntuesday,null", "\nwednesday,null", "\nthursday,null", "\nfriday,null", "\nsaturday,null"};
                     FileHandler.createFile(fileName, daysOfWeek);
 
                     // initializing GymApp instance and creating the welcomeMessage
                     GymApp gymmer = new GymApp(logUN);
                     gymmer.welcomeMessage();
                     GymApp.menu();
 
                 } else {
                     System.out.println("Not accepted; please try again");
                 }
 
             } else if (perChoice.equals("2")) {
 
                 // Sign up process
                 System.out.println("What is your username");
                 String signUN = scanner.nextLine();
 
                 System.out.println("What is your password");
                 String signPW = scanner.nextLine();
 
                 // Create new user account
                 if (User.makeAccount(signUN, signPW)) {
                     System.out.println("Accepted");
                 } else {
                     System.out.println("Please try again. Username not accepted\n");
                 }
             }
         }
     }
 }
 