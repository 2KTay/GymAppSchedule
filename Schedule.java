//Schedule.java
/*
Purpose:

it allows us to...
add a workout type in the file
check if the schedule has enough entries



it inherits the FileHandler functions, but I call it with "FileHandler." to be more specific
 */

 import java.util.HashMap;
 import java.util.Map;
 
 public class Schedule extends FileHandler {
 
     //to add a workout type to a specific day in the schedule file
     public static void addType(String day, String type, String fileName) {
         FileHandler.addRecord(fileName, day, type, "null", 0);
     }
 
     // to check if the schedule has enough entries
     public static boolean checkSchedule(String fileName) {
 
 
         String[] data1 = FileHandler.ReadCol(1, fileName, ",");
         Map<String, Integer> valueCounts = new HashMap<>();
 
         // Count occurrences of each type of workouts
         //had an issue with repeating workouts
         for (String data : data1) {
             if (data != null && !data.isEmpty() && !"null".equals(data)) {
                 valueCounts.put(data, valueCounts.getOrDefault(data, 0) + 1);
             }
         }
 
         // Calculate total count of entries
         int totalCount = 0;
         for (int count : valueCounts.values()) {
             totalCount += count;
         }
 
         // if total count is within the required range. if it is, returns true
         return totalCount >= 3 && totalCount <= 6;
     }
 
 }