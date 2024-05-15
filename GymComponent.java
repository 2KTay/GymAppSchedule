//GymComponent.java
/*
Purpose:
Abstract class to give a welcomeMessage but with their username
I put it in GymApp where it uses it.

 */
abstract class GymComponent{
    protected String name;


    //Constructor
    public GymComponent(String name){
        this.name=name;

    }

    //WelcomeMessageFunction
    public abstract void welcomeMessage();


    //gets the Name
    public String getName() {
        return name;
    }

    //sets the Name
    public void setName(String newName) {
        name = newName;
    }

}