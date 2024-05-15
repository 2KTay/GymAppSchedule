all: Main.java GymApp.java Schedule.java User.java FileHandler.java Exercise.java GymComponent.java Workout.java
	javac Main.java
	javac GymApp.java
	javac Schedule.java
	javac User.java 
	javac FileHandler.java 
	javac Exercise.java 
	javac GymComponent.java 
	javac Workout.java

run: all
	java Main

clean:
	rm -f *.class
	rm -f *.jar

jar: all
	jar cfm Pet.jar manifest.txt *.class
	java -jar Pet.jar