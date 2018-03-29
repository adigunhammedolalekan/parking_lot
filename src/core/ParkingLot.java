package core;

import models.Car;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int mSlotCounts = 0;
    private Map<Integer, Car> mSlots;

    public ParkingLot(int n) {
        mSlotCounts = n;
        mSlots = new HashMap<>(mSlotCounts);

       for (int i = 0; i < mSlotCounts; i++) {
           Car emptyCar = new Car();
           mSlots.put(i + 1, emptyCar);
       }
    }

    /*
    * Find next available slot in the parking lot
    * */
    private int nextAvailableSlot() {

        int result = 0;

        for (Integer slot : mSlots.keySet()) {

            Car car = mSlots.get(slot);
            if (car.registrationNumber == "") {
                result = slot;
                break;
            }
        }
        return result;
    }

    public String leave(int slot) {

        Car toLeave = mSlots.get(slot);
        if (toLeave == null)
            return String.format("Car in slot number %d already left", slot);

        Car empty = new Car();
        mSlots.put(slot, empty); //remove car from slot

        return String.format("Slot number %d is free", slot);
    }

    /*
    * Allocate a Car to a slot
    * */
    private String allocate(int slot, Car toAllocate) {

        Car car = mSlots.get(slot);
        if (car.registrationNumber != "") {
            return String.format("Slot number %d is not free yet", slot);
        }

        if (toAllocate == null) {
            //shouldn't happen!
            return "Cannot allocate an empty car";
        }

        mSlots.put(slot, toAllocate);
        return String.format("Allocated slot number %d", slot);
    }

    /*
    * Allocate a given car
    * */

    public String allocate(Car toAllocate) {

        int slot = nextAvailableSlot();
        if (slot == 0) {
            return "Sorry, parking lot is full.";
        }

        return allocate(slot, toAllocate);
    }

    /*
    * Build a status string
    * */

    public String status() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Slot No").append("\t")
                .append("Registration Number").append("\t")
                .append("Colour").append("\n");

        for (Integer slot : mSlots.keySet()) {

            Car next = mSlots.get(slot);
            stringBuilder.append(slot).append("\t")
                    .append(next.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String registrationNumberOfCarsWithColour(String colour) {

        StringBuilder result = new StringBuilder();
        for (Integer key : mSlots.keySet()) {

            Car next = mSlots.get(key);
            if (next.colour.trim().equalsIgnoreCase(colour.trim())) {
                result.append(next.registrationNumber).append(", ");
            }
        }

        return result.toString();
    }

    public String slotNumbersOfCarsWithColour(String colour) {

        StringBuilder result = new StringBuilder();
        for (Integer key : mSlots.keySet()) {

            Car next = mSlots.get(key);
            if (next.colour.trim().equalsIgnoreCase(colour.trim())) {

                result.append(key).append(", ");
            }
        }

        return result.toString();
    }

    public String slotNumbersForRegistrationNumber(String registrationNumber) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer key : mSlots.keySet()) {

            Car next = mSlots.get(key);
            if (next.registrationNumber.trim().equalsIgnoreCase(registrationNumber.trim())) {

                stringBuilder.append(key).append(", ");
            }
        }

        return stringBuilder.toString();
    }
}