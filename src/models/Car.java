package models;

public class Car {

    public String registrationNumber = "";
    public String colour = "";

    @Override
    public String toString() {
        return registrationNumber + "\t" + colour;
    }
}