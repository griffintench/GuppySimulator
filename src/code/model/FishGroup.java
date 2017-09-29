package code.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A Group of Fish with methods to increment ages, spawn, apply nutrient
 * coefficients, and analyze.
 * 
 * @author griffin
 * @version 1.0
 */
public class FishGroup {

    /**
     * The number of millilitres in a litre.
     */
    public static final int MILLILITRES_IN_LITRE = 1000;

    /**
     * The Fish in the Group.
     */
    private final List<Fish> fish;

    /**
     * True if we know that the ArrayList is properly sorted; false otherwise.
     */
    private boolean sorted;

    /**
     * If the fish have been sorted, the index of the weakest living fish.
     */
    private int weakestLivingIndex;

    /**
     * Zero-parameter constructor; starts with an empty LinkedList.
     */
    public FishGroup() {
        this(new LinkedList<Fish>());
    }

    /**
     * Constructor; sets the LinkedList properly.
     * 
     * @param newFish
     *            an LinkedList of Fish
     */
    public FishGroup(LinkedList<Fish> newFish) {
        fish = (newFish == null) ? new LinkedList<Fish>() : newFish;
        setAsNotSorted();
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
        setAsNotSorted();
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
            result = true;
            setAsNotSorted();
        }

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
            setAsNotSorted();
            result = true;
        }

        return result;
    }

    /**
     * Sorts the Fish, if they have not been sorted already.
     */
    public void sort() {
        if (!sorted) {
            Collections.sort(fish);
            sorted = true;

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
     * Returns the number of Fish in the Group.
     * 
     * @return the number of Fish in the Group
     */
    public int getPopulation() {
        return fish.size();
    }

    /**
     * Counts and returns the number of living Fish in the Group.
     * 
     * @return the number of living Fish in the Group
     */
    public int getLivingPopulation() {
        return countIf(Fish::isAlive);
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

        for (Fish curFish : fish) {
            if (curFish.isAlive()) {
                roll = generator.nextDouble();
                if (roll > nutrientCoefficient) {
                    killFish(curFish);
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
        double result = 0.0;
        for (Fish curFish : fish) {
            result += curFish.getVolumeNeeded() / MILLILITRES_IN_LITRE;
        }
        return result;
    }

    private double getArrayStatistic(Function<Fish, Integer> fishOp,
            Function<int[], Double> statsOp) {
        final int[] stats = new int[getLivingPopulation()];
        final Iterator<Fish> iterator = fish.iterator();

        Fish currentFish;

        int i = 0;
        while (iterator.hasNext()) {
            currentFish = iterator.next();

            if (currentFish != null && currentFish.isAlive()) {
                stats[i] = fishOp.apply(currentFish);
                i++;
            }
        }

        return statsOp.apply(stats);
    }

    /**
     * Calculates and returns the average age in weeks of all the Fish in the
     * Group.
     * 
     * @return the average age in weeks of all the Fish in the Group
     */
    public double getAverageAgeInWeeks() {
        return getArrayStatistic(Fish::getAgeInWeeks, Statistics::arrayMean);
    }

    // TODO figure out what's happening with ints/doubles in getAverageStatistic
    /**
     * Calculates and returns the average health coefficient of all the Fish in
     * the Group.
     * 
     * @return the average health coefficient of all the Fish in the Group
     */
    public double getAverageHealthCoefficient() {
        final double[] healths = new double[getLivingPopulation()];
        final Iterator<Fish> iterator = fish.iterator();

        Fish currentFish;

        int i = 0;
        while (iterator.hasNext()) {
            currentFish = iterator.next();

            if (currentFish != null && currentFish.isAlive()) {
                healths[i] = currentFish.getHealthCoefficient();
                i++;
            }
        }

        return Statistics.arrayMean(healths);
    }

    /**
     * Calculates and returns the percentage of living Fish in the Group that
     * are female.
     * 
     * @return the percentage of living Fish in the group that are female
     */
    public double getFemalePercentage() {
        return getArrayStatistic(f -> (f.getIsFemale()) ? 1 : 0,
                Statistics::arrayMean);
    }

    /**
     * Evaluates and returns the median age of all the living Fish in the Group.
     * 
     * @return the median age of all the living Fish in the Group
     */
    public double getMedianAge() {
        return getArrayStatistic(Fish::getAgeInWeeks, Statistics::arrayMedian);
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

        setAsNotSorted();
        return newBabies.size();
    }

    /**
     * Increments the ages of all the Fish in the Group. Returns the number of
     * Fish that die of old age.
     * 
     * @return the number of Fish that have died of old age
     */
    public int incrementAges() {
        setAsNotSorted();
        return countIf((Fish f) -> f.isAlive() && f.incrementAge());
    }
    
    private int countIf(Predicate<Fish> pred) {
        int count = 0;
        
        for (Fish currentFish : fish) {
            if (currentFish != null && pred.test(currentFish)) {
                count++;
            }
        }
        
        return count;
    }

    /**
     * Sorts the List and then returns another List containing the weakest Fish,
     * the amount of which is specified by the parameter.
     * 
     * @param amount
     *            how many Fish to return
     * @return a List containing the weakest Fish
     */
    public List<Fish> getWeakest(int amount) {
        sort();
        List<Fish> result = new LinkedList<Fish>();

        Iterator<Fish> iterator = fish.listIterator(weakestLivingIndex);
        for (int n = 1; n <= amount && iterator.hasNext(); n++) {
            result.add(iterator.next());
        }

        return result;
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
     * Sets sorted to false and weakestLivingIndex to -1.
     */
    public void setAsNotSorted() {
        sorted = false;
        weakestLivingIndex = -1;
    }

    /**
     * Kills a Fish in this FishGroup.
     * 
     * @param fishToKill
     *            the fish to kill, in this FishGroup
     */
    public void killFish(Fish fishToKill) {
        if (fishToKill != null && fishToKill.isAlive()) {
            fishToKill.kill();
            setAsNotSorted();
        }
    }
}
