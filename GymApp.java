//GymApp.java
/*
Purpose:

This is the menu to all the GymApp functions
Most Complex of the files

It uses the Exercise.java, Workout.Java, and Schedule.java files to call functions

what it does is...
create the welcomeMessage from the abstract Class GymComponent
shows the menu of what can the user do
uses all the functions of said above files to add it to the csv file to create their workout schedule
allows User to see their workouts

 */

 import java.util.Scanner;

 public class GymApp extends GymComponent {
 
     // Constructor
     public GymApp(String name) {
         super(name);
     }
 
     //welcomeMessage method to customize welcome message from abstract class
     @Override
     public void welcomeMessage() {
         System.out.println("\n(CANNOT SKIP CARDIO DO AT LEAST 15-25 MIN PER DAY)");
         System.out.println("\nWelcome, " + getName() + " to the gymApp\n");
     }
 
     static Scanner scanner = new Scanner(System.in);
 
     //menu method to display options and get user input
     public static void menu() {
         boolean exitRequested = false;
         while (!exitRequested) {
             String fileName = User.username + ".csv";
             try {
                 System.out.println("What would you like to do today" +
                         "\n(1) add Workout" +
                         "\n(2) view the workout plan" +
                         "\n(3) see the info of each exercise " +
                         "\n(4) Exit Out");
                 String menuChoice = scanner.nextLine();
                 switch (menuChoice) {
                     case "1":
                         addWorkout(fileName);
                         break;
                     case "2":
                         FileHandler.printFileContents(fileName);
                         break;
                     case "3":
                         showExerciseInfo();
                         break;
                     case "4":
                         exitRequested = true;
                         System.out.println("exiting out");
                         break;
                     default:
                         System.out.println("Invalid choice, please try again");
                 }
             } catch (Exception e) {
                 System.out.println("An error occurred: " + e.getMessage());
             }
         }
     }
 
     // Method to add a workout to the user's schedule
     private static void addWorkout(String fileName) {
         boolean addAnotherWorkout = true;
         while (addAnotherWorkout) {
 
             //asks the day of the week
             System.out.println("What is the day");
             String dayChoice = scanner.nextLine();
 
             //asks which type of exercise
             System.out.println("Which  type of Gym?\t(must do for all 3 sets) " +
                     "\n(type power)\t" +
                     "power: [1-5 reps, explosive movement to create atheltism" +
                     "\n(type strength)\t" +
                     "strength: [5-8 reps, strong, secure lifts to create strength]" +
                     "\n(type hyper)\t" +
                     "HyperTrophy: [8-12 reps, slow lifts to create the maximum muscle growth");
             String typeChoice = scanner.nextLine();
 
             if (typeChoice.equals("power") || typeChoice.equals("strength") || typeChoice.equals("hyper")) {
                 Schedule.addType(dayChoice, typeChoice, fileName);
 
                 String fileTypeChoice = typeChoice + ".csv";
 
                 boolean returnToWorkoutsPrompt = false;
                 while (!returnToWorkoutsPrompt) {
                     Workout.showWorkout(fileTypeChoice);
 
                     //asks what are the workouts
                     System.out.println("What are the workout");
                     String workChoice = scanner.nextLine();
 
                     if (Workout.checkWorkout(fileTypeChoice, workChoice)) {
                         Workout.addWorkout(dayChoice, workChoice, fileName);
 
                         Exercise.showExer(workChoice, fileTypeChoice, ",", 1);
 
                         //asks what are the exercise chosen
                         System.out.println("what are the exercise");
                         String exerChoice = scanner.nextLine();
 
                         String[] exercisesForWorkout = Exercise.findExercisesForWorkout(workChoice, fileTypeChoice, ",");
                         if (Exercise.isWorkoutValid(workChoice, exerChoice, exercisesForWorkout, ",")) {
                             Exercise.addExercise(dayChoice, exerChoice, fileName);
 
                             boolean validInput = false;
                             while (!validInput) {
 
                                 //asks if they want another workout
                                 System.out.println("Do you want to add another workout? (y/n)");
                                 String anotherWork = scanner.nextLine();
                                 if (anotherWork.equals("n")) {
                                     if (!Schedule.checkSchedule(fileName)) {
                                         System.out.println("\nNeed to get rid of entries or add entries\n");
                                     }
                                     addAnotherWorkout = false;
                                     validInput = true;
                                     returnToWorkoutsPrompt = true;
                                 } else if (anotherWork.equals("y")) {
                                     validInput = true;
 
                                 //all of these else statements will manage the error-handling
                                 } else {
                                     System.out.println("Invalid input. Please enter 'y' or 'n'.");
                                 }
                             }
                         } else {
                             System.out.println("Please choose an actual exercise");
                         }
                     } else {
                         System.out.println("Choose an actual workout");
                     }
                 }
             } else {
                 System.out.println("please choose something which is valid");
             }
         }
     }
 
     // method to display exercise information
     private static void showExerciseInfo() {
         System.out.println("\npower" +
                 "\nhypertrophy(hyper)" +
                 "\nstrength\n");
         System.out.println("Which type");
         String fileChoice = scanner.nextLine();
 
         String fileChoiceCSV = fileChoice + ".csv";
         Workout.showWorkout(fileChoiceCSV);
 
         System.out.println("Which workout");
         String workoutChoice = scanner.nextLine();
 
         Exercise.showExer(workoutChoice, fileChoiceCSV, ",", 1);
         System.out.println("What exercise you want to see info about");
         String seeExer = scanner.nextLine();
 
         FileHandler.showExerInfo(seeExer, fileChoiceCSV, ",", 1);
     }
 }