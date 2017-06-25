package ca.bcit.comp1510.guppies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * @author griffin
 *
 */
public class FishGroup {
    /**
     * The Fish in the Group.
     */
    private ArrayList<Fish> fish;

    /**
     * True if we know that the ArrayList is properly sorted; false otherwise.
     */
    private boolean sorted;

    /**
     * Default constructor; starts with an empty ArrayList.
     */
    public FishGroup() {
        setFish(new ArrayList<Fish>());
    }

    /**
     * Constructor; sets the ArrayList properly.
     * 
     * @param newFish
     *            an ArrayList of Fish
     */
    public FishGroup(ArrayList<Fish> newFish) {
        setFish(newFish);
    }

    public ArrayList<Fish> getFish() {
        return fish;
    }

    public void setFish(ArrayList<Fish> newFish) {
        if (newFish == null) {
            fish = new ArrayList<Fish>();
        } else {
            fish = newFish;
        }
        sorted = false;
    }
    
    public Fish get(int i) {
        return fish.get(i);
    }
    
    public boolean contains(Fish fish) {
        return this.fish.contains(fish);
    }
    
    public boolean remove(Fish fishToRemove) {
        if (!contains(fishToRemove)) {
            throw new IllegalArgumentException();
        }
        return fish.remove(fishToRemove);
    }

    public boolean addFish(Fish fishToAdd) {
        boolean result = false;

        if (fishToAdd != null) {
            fish.add(fishToAdd);
            result = true;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        if (!sorted) {
            Collections.sort(fish);
            sorted = true;
        }
    }

    /**
     * Counts and returns the number of Fish in the Group.
     * 
     * @return the number of Fish in the Group
     */
    public int getPopulation() {
        int population = 0;

        for (int i = 0; i < fish.size(); i++) {
            if (fish.get(i) != null) {
                population++;
            }
        }

        return population;
    }

    /**
     * Counts and returns the number of living Fish in the Group.
     * 
     * @return the number of living Fish in the Group
     */
    public int getLivingPopulation() {
        int population = 0;

        for (int i = 0; i < fish.size(); i++) {
            if (fish.get(i) != null && fish.get(i).getHealth().getIsAlive()) {
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
        Fish curFish;

        for (int i = 0; i < fish.size(); i++) {
            curFish = fish.get(i);
            if (curFish != null) {
                roll = generator.nextDouble();

                if (roll > nutrientCoefficient) {
                    curFish.getHealth().setIsAlive(false);
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
        int removedFish = 0;
        Iterator<Fish> iterator = fish.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getHealth().getIsAlive()) {
                iterator.remove();
                removedFish++;
            }
        }

        return removedFish;
    }

    /**
     * Calculates and returns the total volume of water needed to sustain all
     * the Fish currently in the Group, in litres.
     * 
     * @return the total volume of water needed to sustain all the Fish
     *         currently in the Group, in litres
     */
    public double getFishVolumeRequirementInLitres() {
        double totalVolumeLitres = 0.0;
        double totalVolumeMillilitres = 0.0;
        final int millilitresInLitre = 1000;

        Fish currentFish;
        for (int i = 0; i < fish.size(); i++) {
            currentFish = fish.get(i);

            if (currentFish != null && currentFish.getHealth().getIsAlive()) {
                totalVolumeMillilitres += currentFish.getVolumeNeeded();
            }
        }

        totalVolumeLitres = totalVolumeMillilitres / millilitresInLitre;
        return totalVolumeLitres;
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

                if (currentFish != null
                        && currentFish.getHealth().getIsAlive()) {
                    healthCoefficientSum += currentFish.getHealth()
                            .getHealthCoefficient();
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

                if (currentFish != null && currentFish.getHealth().getIsAlive()
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
        int currentAge = 0;

        if (population == 0) {
            return 0.0;
        } else {

            for (int i = 0; i < fish.size(); i++) {
                currentFish = fish.get(i);

                if (currentFish != null
                        && currentFish.getHealth().getIsAlive()) {
                    fishAges[currentAge] = currentFish.getAgeInWeeks();
                    currentAge++;
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
        ArrayList<Fish> newBabies = new ArrayList<Fish>();

        for (Fish currentFish : fish) {
            ArrayList<Fish> newFish = currentFish.spawn();
            newBabies.addAll(newFish);
        }
        fish.addAll(newBabies);

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
            if (currentFish != null) {
                currentFish.incrementAge();
                if (!currentFish.getHealth().getIsAlive()) {
                    numberOfDead++;
                }
            }
        }

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
        return fish.get(0);
    }
}
