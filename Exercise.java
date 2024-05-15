//Exercise.java
/*
Purpose:

Exercise contains all the functionalities associated with exercise

it can add an exercise
show an exercises but from a selected workout
checks if a given exercise choice is valid for a workout
finds exercises associated with a specific workout choice but returns a string array

it inherits the FileHandler functions, but I call it with "FileHandler." to be more specific

 */


 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.util.ArrayList;
 
 public class Exercise extends FileHandler {
 
     // adds an exercise to a specific day in a workout file
     public static void addExercise(String day, String exer, String fileName) {
         String comExer = "," + exer + ",";
         FileHandler.writeToRow(fileName, day, comExer);
     }
 
     // shows exercises for a given workout choice from a workout file
     public static void showExer(String workoutChoice, String filepath, String delimiter, int num) {
         String[] workouts = FileHandler.ReadCol(0, filepath, delimiter);
 
         if (workouts == null) {
             System.out.println("Error.");
             return;
         }
 
         ArrayList<String> exercises = new ArrayList<>();
 
         for (int i = 1; i < workouts.length; i++) {
             if (workouts[i].equalsIgnoreCase(workoutChoice)) {
                 String[] exerciseInfo = FileHandler.ReadCol(num, filepath, delimiter);
                 if (exerciseInfo != null && i < exerciseInfo.length) {
                     exercises.add(exerciseInfo[i]);
                 }
             }
         }
 
         System.out.println("Exercises for " + workoutChoice + ":");
         for (String exercise : exercises) {
             System.out.println(exercise);
         }
     }
 
     // checks if a given exercise choice is valid for a workout
     public static boolean isWorkoutValid(String workoutChoice, String exerChoice, String[] exercisesForWorkout, String delimiter) {
         for (String exercise : exercisesForWorkout) {
             if (exercise.equalsIgnoreCase(exerChoice)) {
                 return true;
             }
         }
         return false;
     }
 
     // finds exercises associated with a specific workout choice
     public static String[] findExercisesForWorkout(String workoutChoice, String filepath, String delimiter) {
         ArrayList<String> exercisesForWorkout = new ArrayList<>();
         try {
             FileReader fr = new FileReader(filepath);
             BufferedReader br = new BufferedReader(fr);
             String currentLine;
 
             br.readLine();
 
             while ((currentLine = br.readLine()) != null) {
                 String[] data = currentLine.split(delimiter);
                 if (data.length == 3 && data[0].equalsIgnoreCase(workoutChoice)) {
                     exercisesForWorkout.add(data[1]);
                 }
             }
             br.close();
             fr.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
 
         return exercisesForWorkout.toArray(new String[0]);
     }
 }
 