package code.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A Group of Fish with methods to increment ages, spawn, apply nutrient
 * coefficients, and analyze.
 * 
 * @author griffin
 * @version 1.0
 */
public class FishGroup {
    public static final int MILLILITRES_IN_LITRE = 1000;

    /**
     * The Fish in the Group.
     */
    private List<Fish> fish;

    /**
     * True if we know that the ArrayList is properly sorted; false otherwise.
     */
    private boolean sorted;

    /**
     * If the fish have been sorted, the index of the weakest living fish.
     */
    private int weakestLivingIndex;

    private double volumeRequirementLitres;

    /**
     * Zero-parameter constructor; starts with an empty ArrayList.
     */
    public FishGroup() {
        setFish(new LinkedList<Fish>());
    }

    /**
     * Constructor; sets the ArrayList properly.
     * 
     * @param newFish
     *            an ArrayList of Fish
     */
    public FishGroup(LinkedList<Fish> newFish) {
        setFish(newFish);
    }

    /**
     * Returns an ArrayList with the Fish in this Group.
     * 
     * @return an ArrayList with the Fish in this Group
     */
    public List<Fish> getFish() {
        return fish;
    }

    /**
     * Sets the Fish in this Group.
     * 
     * @param newFish
     *            an ArrayList with the Fish in this Group
     */
    public void setFish(LinkedList<Fish> newFish) {
        if (newFish == null) {
            fish = new LinkedList<Fish>();
            volumeRequirementLitres = 0;
        } else {
            fish = newFish;
            addToVolumeRequirement(newFish);
        }
        sorted = false;
        weakestLivingIndex = -1;
    }

    /**
     * Returns the Fish at the i-th index in the ArrayList containing this
     * Group's Fish.
     * 
     * @param i
     *            the index of the Fish
     * @return the Fish at the i-th index
     */
    public Fish get(int i) {
        return fish.get(i);
    }

    /**
     * Returns true if this FishGroup contains a specified Fish; false
     * otherwise.
     * 
     * @param fishToCheck
     *            the Fish that may or may not be in this FishGroup
     * @return true if this FishGroup contains the Fish; false otherwise
     */
    public boolean contains(Fish fishToCheck) {
        return fish.contains(fishToCheck);
    }

    /**
     * Removes the specified Fish from the Group.
     * 
     * @param fishToRemove
     *            the Fish to remove from the Group
     * @return true if the Fish was successfully removed
     */
    public boolean remove(Fish fishToRemove) {
        sorted = false;
        weakestLivingIndex = -1;
        if (fishToRemove.isAlive()) {
            volumeRequirementLitres -= fishToRemove.getVolumeNeeded()
                    / MILLILITRES_IN_LITRE;
        }
        return fish.remove(fishToRemove);
    }

    /**
     * Adds a Fish to this Group.
     * 
     * @param fishToAdd
     *            the Fish to add to the Group
     * @return true if the Fish was added successfully
     */
    public boolean addFish(Fish fishToAdd) {
        boolean result = false;

        if (fishToAdd != null) {
            fish.add(fishToAdd);
            volumeRequirementLitres += fishToAdd.getVolumeNeeded()
                    / MILLILITRES_IN_LITRE;
            result = true;
        }

        sorted = false;
        weakestLivingIndex = -1;
        return result;
    }

    /**
     * Adds multiple Fish to this Group.
     * 
     * @param fishToAdd
     *            an ArrayList object with all the Fish to add
     * @return true if the Fish were added successfully
     */
    public boolean addFish(LinkedList<Fish> fishToAdd) {
        boolean result = false;

        if (fishToAdd != null) {
            fish.addAll(fishToAdd);
            addToVolumeRequirement(fishToAdd);
            result = true;
        }

        sorted = false;
        weakestLivingIndex = -1;
        return result;
    }

    /**
     * Sorts the Fish, if they have not been sorted already.
     */
    public void sort() {
        if (!sorted) {
            Collections.sort(fish);
            sorted = true;
            // for (int i = 0; i < fish.size() && weakestLivingIndex == -1; i++)
            // {
            // if (fish.get(i).isAlive()) {
            // weakestLivingIndex = i;
            // }
            // }
            Iterator<Fish> iterator = fish.iterator();
            while (weakestLivingIndex == -1 && iterator.hasNext()) {
                Fish next = iterator.next();
                if (next.isAlive()) {
                    weakestLivingIndex = fish.indexOf(next);
                }
            }
        }
    }

    /**
     * Counts and returns the number of Fish in the Group.
     * 
     * @return the number of Fish in the Group
     */
    public int getPopulation() {
        // int population = 0;
        //
        // for (int i = 0; i < fish.size(); i++) {
        // if (fish.get(i) != null) {
        // population++;
        // }
        // }
        //
        // return population;
        return fish.size();
    }

    /**
     * Counts and returns the number of living Fish in the Group.
     * 
     * @return the number of living Fish in the Group
     */
    public int getLivingPopulation() {
        int population = 0;

        for (int i = 0; i < fish.size(); i++) {
            if (fish.get(i) != null && fish.get(i).isAlive()) {
                population++;
            }
        }

        return population;
    }

    /**
     * Loops through all the Fish in the Group and determines if each survives.
     * Uses the Nutrient Coefficient to determine how likely it is that any
     * given Fish survives.
     * 
     * @param nutrientCoefficient
     *            the Nutrient Coefficient to apply
     * @return the number of newly dead Fish
     */
    public int applyNutrientCoefficient(double nutrientCoefficient) {

        Random generator = new Random();
        double roll;
        int numberOfDeaths = 0;
        // Fish curFish;

        // for (int i = 0; i < fish.size(); i++) {
        // curFish = fish.get(i);
        // if (curFish != null && curFish.isAlive()) {
        // roll = generator.nextDouble();
        //
        // if (roll > nutrientCoefficient) {
        // curFish.kill();
        // volumeRequirementLitres -= curFish.getVolumeNeeded();
        // sorted = false;
        // weakestLivingIndex = -1;
        // numberOfDeaths++;
        // }
        // }
        // }

        for (Fish curFish : fish) {
            if (curFish.isAlive()) {
                roll = generator.nextDouble();
                if (roll > nutrientCoefficient) {
                    curFish.kill();
                    volumeRequirementLitres -= curFish.getVolumeNeeded()
                            / MILLILITRES_IN_LITRE;
                    sorted = false;
                    weakestLivingIndex = -1;
                    numberOfDeaths++;
                }
            }
        }

        return numberOfDeaths;

    }

    /**
     * Removes any dead Fish from the guppiesInPool array.
     * 
     * @return the number of Fish removed from the array
     */
    public int removeDeadFish() {
        // int removedFish = 0;
        // Iterator<Fish> iterator = fish.iterator();
        // while (iterator.hasNext()) {
        // if (!iterator.next().isAlive()) {
        // iterator.remove();
        // sorted = false;
        // weakestLivingIndex = -1;
        // removedFish++;
        // }
        // }
        //
        // return removedFish;

        sort();

        for (int i = 0; i < weakestLivingIndex; i++) {
            fish.remove(0);
        }

        int result = weakestLivingIndex;
        weakestLivingIndex = 0;

        return result;
    }

    /**
     * Returns the total volume of water needed to sustain all the Fish
     * currently in the Group, in litres.
     * 
     * @return the total volume of water needed to sustain all the Fish
     *         currently in the Group, in litres
     */
    public double getFishVolumeRequirementInLitres() {
        return volumeRequirementLitres;
    }

    private void addToVolumeRequirement(LinkedList<Fish> fishList) {
        double totalVolumeLitres = 0.0;
        double totalVolumeMillilitres = 0.0;
        final int millilitresInLitre = 1000;

        for (Fish curFish : fishList) {
            if (curFish != null && curFish.isAlive()) {
                totalVolumeMillilitres += curFish.getVolumeNeeded();
            }
        }

        totalVolumeLitres = totalVolumeMillilitres / millilitresInLitre;
        volumeRequirementLitres += totalVolumeLitres;
    }

    /**
     * Calculates and returns the average age in weeks of all the Fish in the
     * Group.
     * 
     * @return the average age in weeks of all the Fish in the Group
     */
    public double getAverageAgeInWeeks() {
        int fishCount = getPopulation();
        int ageSum = 0;
        double averageAge;

        Fish currentFish;

        if (fishCount == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < fish.size(); i++) {
                currentFish = fish.get(i);

                if (currentFish != null
                        && currentFish.getHealth().getIsAlive()) {
                    ageSum += currentFish.getAgeInWeeks();
                }
            }

            averageAge = (double) ageSum / fishCount;
            return averageAge;
        }
    }

    /**
     * Calculates and returns the average health coefficient of all the Fish in
     * the Group.
     * 
     * @return the average health coefficient of all the Fish in the Group
     */
    public double getAverageHealthCoefficient() {

        int fishCount = getPopulation();
        double healthCoefficientSum = 0.0;
        double averageHealthCoefficient;

        Fish currentFish;

        if (fishCount == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < fish.size(); i++) {
                currentFish = fish.get(i);

                if (currentFish != null && currentFish.isAlive()) {
                    healthCoefficientSum += currentFish.getHealthCoefficient();
                }
            }

            averageHealthCoefficient = healthCoefficientSum / fishCount;
            return averageHealthCoefficient;
        }
    }

    /**
     * Calculates and returns the percentage of living Fish in the Group that
     * are female.
     * 
     * @return the average health coefficient of all the Fish in the Group
     */
    public double getFemalePercentage() {
        int fishCount = getPopulation();
        int femaleCount = 0;
        double femalePercentage;

        Fish currentFish;

        if (getPopulation() == 0) {
            return 0.0;
        } else {
            for (int i = 0; i < fish.size(); i++) {
                currentFish = fish.get(i);

                if (currentFish != null && currentFish.isAlive()
                        && currentFish.getIsFemale()) {
                    femaleCount++;
                }
            }
            femalePercentage = (double) femaleCount / fishCount;
            return femalePercentage;
        }
    }

    /**
     * Evaluates and returns the median age of all the living Fish in the Group.
     * 
     * @return the median age of all the living Fish in the Group
     */
    public double getMedianAge() {

        int population = getPopulation();

        double medianAge;
        double median1;
        double median2;
        int index1;
        int index2;
        int[] fishAges = new int[population];
        Fish currentFish;
        int currentAgeIndex = 0;

        if (population == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < fish.size(); i++) {
                currentFish = fish.get(i);

                if (currentFish != null && currentFish.isAlive()) {
                    fishAges[currentAgeIndex] = currentFish.getAgeInWeeks();
                    currentAgeIndex++;
                }
            }

            Arrays.sort(fishAges);

            if (population % 2 == 0) {
                index1 = population / 2;
                index2 = index1 - 1;

                median1 = fishAges[index1];
                median2 = fishAges[index2];

                medianAge = (median1 + median2) / 2;
            } else {
                index1 = population / 2;

                medianAge = fishAges[index1];
            }

            return medianAge;
        }
    }

    /**
     * Invokes the spawn() method on all the Fish in the Group. Any baby Fish
     * born are added to the Group. Returns the number of baby Fish added to the
     * Group.
     * 
     * @return the number of baby Fish added to the Group
     */
    public int spawn() {
        LinkedList<Fish> newBabies = new LinkedList<Fish>();

        for (Fish currentFish : fish) {
            ArrayList<Fish> newFish = currentFish.spawn();
            if (newFish != null) {
                newBabies.addAll(newFish);
            }
        }
        fish.addAll(newBabies);
        addToVolumeRequirement(newBabies);

        sorted = false;
        weakestLivingIndex = -1;
        return newBabies.size();
    }

    /**
     * Increments the ages of all the Fish in the Group. Returns the number of
     * Fish that die of old age.
     * 
     * @return the number of Fish that have died of old age
     */
    public int incrementAges() {
        int numberOfDead = 0;

        for (Fish currentFish : fish) {
            if (currentFish != null && currentFish.isAlive()) {
                double curFishVolReq = currentFish.getVolumeNeeded();
                currentFish.incrementAge();
                if (!currentFish.isAlive()) {
                    numberOfDead++;
                }
                volumeRequirementLitres = volumeRequirementLitres
                        + (currentFish.getVolumeNeeded() - curFishVolReq)
                                / MILLILITRES_IN_LITRE;
            }
        }

        sorted = false;
        weakestLivingIndex = -1;
        return numberOfDead;
    }

    /**
     * Sorts the ArrayList and then returns the Fish with the lowest health
     * coefficient.
     * 
     * @return the Fish with the lowest health coefficient
     */
    public Fish getWeakest() {
        sort();

        return fish.get(weakestLivingIndex);
    }

    /**
     * Returns an int array with the number of fish of each health type.
     * 
     * @return an int array with the number of fish of each health type
     */
    public int[] getFishHealthNumbers() {
        final int healthy = 0;
        final int okay = 1;
        final int unhealthy = 2;
        final int healthTypes = 3;

        int[] result = new int[healthTypes];

        for (Fish curFish : fish) {
            if (curFish.isHealthy()) {
                result[healthy]++;
            } else if (curFish.isOkay()) {
                result[okay]++;
            } else {
                result[unhealthy]++;
            }
        }

        return result;
    }

    /**
     * Changes the volume requirement.
     * 
     * @param delta
     *            the amount by which to change the volume requirement, in
     *            litres
     */
    public void changeVolumeRequirement(double delta) {
        volumeRequirementLitres += delta;
    }

    /**
     * Sets sorted to false and weakestLivingIndex to -1.
     */
    public void setAsNotSorted() {
        sorted = false;
        weakestLivingIndex = -1;
    }
}
