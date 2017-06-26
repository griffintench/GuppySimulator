package ca.bcit.comp1510.guppies;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/*
 * Potential issues:
 * Javadoc??
 * what order do these run in?
 * depending on default constructor for most
 * maletofemale is dependent on femaletomale (others too)
 * testValidHealthCoefficient - reasonable to test just one? since values are (sort of) continuous
 * toString?
 * same order? even though incrementAge?
 * incrementAge and the default constructor - test at once?
 */

public class GuppyTest {
/*
    private static final double TOLERANCE = 0.0000001;

    private static Guppy guppy;
    private static Guppy complexGuppy;

    @Before
    public void setUp() {
        guppy = new Guppy();
        final double healthCoefficient = 0.25;
        complexGuppy = new Guppy("genus", "species", 1, false, 1,
                healthCoefficient);
    }

    @Test
    public void testGetGenus() {
        assertEquals(Guppy.DEFAULT_GENUS, guppy.getGenus());
    }

    @Test
    public void testSetGenusOneCharUppercase() {
        guppy.setGenus("A");
        assertEquals("A", guppy.getGenus());
    }

    @Test
    public void testSetGenusOneCharLowercase() {
        guppy.setGenus("a");
        assertEquals("A", guppy.getGenus());
    }

    @Test
    public void testSetGenusMultiCharNoCaseChangesNecessary() {
        guppy.setGenus("Hello");
        assertEquals("Hello", guppy.getGenus());
    }

    @Test
    public void testSetGenusMultiCharCaseChangesNecessary() {
        guppy.setGenus("hElLo");
        assertEquals("Hello", guppy.getGenus());
    }

    @Test
    public void testSetGenusNullGenus() {
        guppy.setGenus(null);
        assertEquals(Guppy.DEFAULT_GENUS, guppy.getGenus());
    }

    @Test
    public void testSetGenusEmptyGenus() {
        guppy.setGenus("");
        assertEquals(Guppy.DEFAULT_GENUS, guppy.getGenus());
    }

    @Test
    public void testGetSpecies() {
        assertEquals(Guppy.DEFAULT_SPECIES, guppy.getSpecies());
    }

    @Test
    public void testSetSpeciesLowercaseSpecies() {
        guppy.setSpecies("species");
        assertEquals("species", guppy.getSpecies());
    }

    @Test
    public void testSetSpeciesNonLowercaseSpecies() {
        guppy.setSpecies("sPeCiEs");
        assertEquals("species", guppy.getSpecies());
    }

    @Test
    public void testSetSpeciesNullSpecies() {
        guppy.setSpecies(null);
        assertEquals(Guppy.DEFAULT_SPECIES, guppy.getSpecies());
    }

    @Test
    public void testSetSpeciesEmptySpecies() {
        guppy.setSpecies("");
        assertEquals(Guppy.DEFAULT_SPECIES, guppy.getSpecies());
    }

    @Test
    public void testGetAgeInWeeks() {
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksValidAgeUpperBound() {
        guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS - 1);
        assertEquals(Guppy.MAXIMUM_AGE_IN_WEEKS - 1, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksValidAgeWithinBounds() {
        final int newAge = 30;
        guppy.setAgeInWeeks(newAge);
        assertEquals(newAge, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksValidAgeLowerBound() {
        guppy.setAgeInWeeks(1);
        guppy.setAgeInWeeks(0);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksNegativeAgeUpperBound() {
        guppy.setAgeInWeeks(-1);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksNegativeAgeBelowBound() {
        final int newAge = -50;
        guppy.setAgeInWeeks(newAge);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksHighAgeLowerBound() {
        guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS + 1);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testSetAgeInWeeksHighAgeAboveBound() {
        final int newAge = 500;
        guppy.setAgeInWeeks(newAge);
        assertEquals(0, guppy.getAgeInWeeks());
    }

    @Test
    public void testGetIsFemale() {
        assertEquals(true, guppy.getIsFemale());
    }

    @Test
    public void testSetIsFemaleToMale() {
        guppy.setIsFemale(false);
        assertEquals(false, guppy.getIsFemale());
    }

    @Test
    public void testSetIsFemaleToFemale() {
        guppy.setIsFemale(false);
        guppy.setIsFemale(true);
        assertEquals(true, guppy.getIsFemale());
    }

    @Test
    public void testGetGenerationNumber() {
        assertEquals(0, guppy.getGenerationNumber());
    }

    @Test
    public void testSetGenerationNumberValidGenerationNumberLowerBound() {
        guppy.setGenerationNumber(1);
        guppy.setGenerationNumber(0);
        assertEquals(0, guppy.getGenerationNumber());
    }

    @Test
    public void testSetGenerationNumberValidGenerationNumberAboveBound() {
        final int newGenerationNumber = 50;
        guppy.setGenerationNumber(newGenerationNumber);
        assertEquals(newGenerationNumber, guppy.getGenerationNumber());
    }

    @Test
    public void testSetGenerationNumberNegativeGenerationNumberUpperBound() {
        guppy.setGenerationNumber(-1);
        assertEquals(0, guppy.getGenerationNumber());
    }

    @Test
    public void testSetGenerationNumberNegativeGenerationNumberBelowBound() {
        final int newGenerationNumber = -50;
        guppy.setGenerationNumber(newGenerationNumber);
        assertEquals(0, guppy.getGenerationNumber());
    }

    @Test
    public void testGetIsAlive() {
        assertEquals(true, guppy.getIsAlive());
    }

    @Test
    public void testSetIsAliveFalse() {
        guppy.setIsAlive(false);
        assertEquals(false, guppy.getIsAlive());
    }

    @Test
    public void testGetHealthCoefficient() {
        assertEquals(Guppy.DEFAULT_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testSetHealthCoefficientValidHealthCoefficient() {
        final double healthCoefficient = 0.1;
        guppy.setHealthCoefficient(healthCoefficient);
        assertEquals(healthCoefficient, guppy.getHealthCoefficient(),
                TOLERANCE);
    }

    @Test
    public void testSetHealthCoefficientLowHealthCoefficientUpperBound() {
        final double healthCoefficient = 0.1;
        final double decrementAmount = 0.1;
        guppy.setHealthCoefficient(healthCoefficient);
        guppy.setHealthCoefficient(
                Guppy.MINIMUM_HEALTH_COEFFICIENT - decrementAmount);
        assertEquals(Guppy.DEFAULT_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testSetHealthCoefficientLowHealthCoefficientBelowBound() {
        final double validHealthCoefficient = 0.1;
        final double lowHealthCoefficient = -50.0;
        guppy.setHealthCoefficient(validHealthCoefficient);
        guppy.setHealthCoefficient(lowHealthCoefficient);
        assertEquals(Guppy.DEFAULT_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testSetHealthCoefficientHighHealthCoefficient() {
        final double healthCoefficient = 0.1;
        final double incrementAmount = 0.1;
        guppy.setHealthCoefficient(healthCoefficient);
        guppy.setHealthCoefficient(
                Guppy.MAXIMUM_HEALTH_COEFFICIENT + incrementAmount);
        assertEquals(Guppy.DEFAULT_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testGetIdentificationNumber() {
        assertEquals(Guppy.getNumberOfGuppiesBorn(),
                complexGuppy.getIdentificationNumber());
    }

    @Test
    public void testGetNumberOfGuppiesBorn() {
        int currentNumber = Guppy.getNumberOfGuppiesBorn();
        Guppy newGuppy = new Guppy();
        assertEquals(currentNumber + 1, Guppy.getNumberOfGuppiesBorn());
    }

    @Test
    public void testGetVolumeNeededYoungFishLowerBound() {
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML, guppy.getVolumeNeeded(),
                TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededYoungFishWithinBounds() {
        final int youngAge = 5;
        guppy.setAgeInWeeks(youngAge);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML, guppy.getVolumeNeeded(),
                TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededYoungFishUpperBound() {
        guppy.setAgeInWeeks(Guppy.YOUNG_FISH_WEEKS - 1);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML, guppy.getVolumeNeeded(),
                TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededMatureFishLowerBound() {
        guppy.setAgeInWeeks(Guppy.YOUNG_FISH_WEEKS);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML, guppy.getVolumeNeeded(),
                TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededMatureFishWithinBounds() {
        final int matureAge = 20;
        final int matureRatio = 2;
        guppy.setAgeInWeeks(matureAge);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML * matureRatio,
                guppy.getVolumeNeeded(), TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededMatureFishUpperBound() {
        final int matureRatio = 3;

        guppy.setAgeInWeeks(Guppy.MATURE_FISH_WEEKS);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML * matureRatio,
                guppy.getVolumeNeeded(), TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededOldFishLowerBound() {
        final double oldFishWaterRatio = 1.5;

        guppy.setAgeInWeeks(Guppy.MATURE_FISH_WEEKS + 1);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML * oldFishWaterRatio,
                guppy.getVolumeNeeded(), TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededOldFishWithinBounds() {
        final double oldFishWaterRatio = 1.5;
        final int oldAge = 40;

        guppy.setAgeInWeeks(oldAge);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML * oldFishWaterRatio,
                guppy.getVolumeNeeded(), TOLERANCE);
    }

    @Test
    public void testGetVolumeNeededOldFishUpperBound() {
        final double oldFishWaterRatio = 1.5;

        guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS - 1);
        assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML * oldFishWaterRatio,
                guppy.getVolumeNeeded(), TOLERANCE);
    }

    @Test
    public void testChangeHealthCoefficientNoChange() {
        guppy.changeHealthCoefficient(0.0);
        assertEquals(Guppy.DEFAULT_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testChangeHealthCoefficientValidChange() {
        guppy.setHealthCoefficient(0.0);
        guppy.changeHealthCoefficient(1.0);
        assertEquals(1.0, guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testChangeHealthCoefficientTooLow() {
        guppy.changeHealthCoefficient(-1.0);
        assertEquals(0.0, guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testChangeHealthCoefficientDeath() {
        guppy.changeHealthCoefficient(-1.0);
        assertEquals(false, guppy.getIsAlive());
    }

    @Test
    public void testChangeHealthCoefficientTooHigh() {
        guppy.changeHealthCoefficient(1.0);
        assertEquals(Guppy.MAXIMUM_HEALTH_COEFFICIENT,
                guppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testIncrementAgeStillAlive() {
        guppy.setAgeInWeeks(1);
        guppy.incrementAge();
        assertEquals(2, guppy.getAgeInWeeks());
    }

    @Test
    public void testIncrementAgeDeath() {
        guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS - 1);
        guppy.incrementAge();
        assertEquals(false, guppy.getIsAlive());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleAgeInWeeks() {
        assertEquals(1, complexGuppy.getAgeInWeeks());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleGenerationNumber() {
        assertEquals(1, complexGuppy.getGenerationNumber());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleNullGenus() {
        final double healthCoefficient = 0.5;
        Guppy customGuppy = new Guppy(null, "species", 1, true, 1,
                healthCoefficient);
        assertEquals(Guppy.DEFAULT_GENUS, customGuppy.getGenus());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleEmptyGenus() {
        final double healthCoefficient = 0.5;
        Guppy customGuppy = new Guppy("", "species", 1, true, 1,
                healthCoefficient);
        assertEquals(Guppy.DEFAULT_GENUS, customGuppy.getGenus());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleValidGenus() {
        assertEquals("Genus", complexGuppy.getGenus());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleNullSpecies() {
        final double healthCoefficient = 0.5;
        Guppy customGuppy = new Guppy("genus", null, 1, true, 1,
                healthCoefficient);
        assertEquals(Guppy.DEFAULT_SPECIES, customGuppy.getSpecies());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleEmptySpecies() {
        final double healthCoefficient = 0.5;
        Guppy customGuppy = new Guppy("genus", "", 1, true, 1,
                healthCoefficient);
        assertEquals(Guppy.DEFAULT_SPECIES, customGuppy.getSpecies());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleValidSpecies() {
        assertEquals("species", complexGuppy.getSpecies());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleIsFemale() {
        assertEquals(false, complexGuppy.getIsFemale());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleIsAlive() {
        assertEquals(true, complexGuppy.getIsAlive());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleHealthCoefficient() {
        final double expectedHealthCoefficient = 0.25;
        assertEquals(expectedHealthCoefficient,
                complexGuppy.getHealthCoefficient(), TOLERANCE);
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleIncrementNumber() {
        int currentNumber = Guppy.getNumberOfGuppiesBorn();
        Guppy customGuppy = new Guppy("genus", "species", 1, true, 1, 1.0);
        assertEquals(currentNumber + 1, Guppy.getNumberOfGuppiesBorn());
    }

    @Test
    public void testGuppyStringStringIntBooleanIntDoubleIdentificationNumber() {
        assertEquals(Guppy.getNumberOfGuppiesBorn(),
                complexGuppy.getIdentificationNumber());
    }

    @Test
    public void testSpawnMale() {
        guppy.setIsFemale(false);
        assertEquals(new ArrayList<Guppy>(), guppy.spawn());
    }

    @Test
    public void testSpawnUnderageBelowUpperBound() {
        guppy.setAgeInWeeks(0);
        assertEquals(new ArrayList<Guppy>(), guppy.spawn());
    }

    @Test
    public void testSpawnUnderageUpperBound() {
        final int oldestUnderage = 9;
        guppy.setAgeInWeeks(oldestUnderage);
        assertEquals(new ArrayList<Guppy>(), guppy.spawn());
    }

    @Test
    public void testSpawn25PercentSuccessRate() {
        ArrayList<Guppy> emptySet = new ArrayList<Guppy>();
        ArrayList<Guppy> potentialMothers = new ArrayList<Guppy>();
        ArrayList<Guppy> spawn;
        int mothers = 0;
        final int numberOfGuppies = 200;
        final int ageInWeeks = 10;
        for (int i = 1; i <= numberOfGuppies; i++) {
            potentialMothers
                    .add(new Guppy(null, null, ageInWeeks, true, 0, 0.0));
        }
        for (Guppy potentialMother : potentialMothers) {
            spawn = potentialMother.spawn();
            if (!spawn.equals(emptySet)) {
                mothers++;
            }
        }

        final int lowerBound = 30;
        final int upperBound = 70;
        System.out.println(mothers);
        assertTrue(mothers >= lowerBound && mothers <= upperBound);
    }

    @Test
    public void testSpawnNumberOfFry() {
        ArrayList<Guppy> potentialMothers = new ArrayList<Guppy>();
        final int numberOfPotentialMothers = 4000;
        ArrayList<Integer> numbersOfFry = new ArrayList<Integer>();
        ArrayList<Guppy> spawn;
        Integer numberOfFry;

        final int ageInWeeks = 10;

        for (int i = 1; i <= numberOfPotentialMothers; i++) {
            potentialMothers
                    .add(new Guppy(null, null, ageInWeeks, true, 0, 0.0));
        }

        for (Guppy potentialMother : potentialMothers) {
            spawn = potentialMother.spawn();
            numberOfFry = spawn.size();
            if (!numbersOfFry.contains(numberOfFry)) {
                numbersOfFry.add(numberOfFry);
            }
        }

        final int possibleNumbersOfFry = 101;

        assertEquals(possibleNumbersOfFry, numbersOfFry.size());
    }

    @Test
    public void testSpawnSameGenusAsMom() {
        final int ageInWeeks = 10;
        Guppy mother = new Guppy("Genus", null, ageInWeeks, true, 0, 0.0);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals("Genus", spawn.get(0).getGenus());
    }

    @Test
    public void testSpawnSameSpeciesAsMom() {
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, "species", ageInWeeks, true, 0, 0.0);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals("species", spawn.get(0).getSpecies());
    }

    @Test
    public void testSpawnHalfFemale() {
        final int ageInWeeks = 10;
        final int minimumSpawn = 15;
        Guppy mother = new Guppy(null, null, ageInWeeks, true, 0, 0.0);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() < minimumSpawn);

        boolean atLeastOneMale = false;
        boolean atLeastOneFemale = false;

        Guppy fry;
        for (int i = 0; i < spawn.size()
                && (!atLeastOneMale || !atLeastOneFemale); i++) {
            fry = spawn.get(i);
            atLeastOneMale = (!fry.getIsFemale()) ? true : atLeastOneMale;
            atLeastOneFemale = (fry.getIsFemale()) ? true : atLeastOneFemale;
        }

        assertTrue(atLeastOneMale && atLeastOneFemale);
    }

    @Test
    public void testSpawnHealthCoefficientLowerBound() {
        final double motherHealthCoefficient = 0.0;
        final double fryHealthCoefficient = 0.5;
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, null, ageInWeeks, true, 0,
                motherHealthCoefficient);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals(fryHealthCoefficient, spawn.get(0).getHealthCoefficient(),
                TOLERANCE);
    }

    @Test
    public void testSpawnHealthCoefficientBetweenBounds() {
        final double motherHealthCoefficient = 0.3;
        final double fryHealthCoefficient = 0.65;
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, null, ageInWeeks, true, 0,
                motherHealthCoefficient);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals(fryHealthCoefficient, spawn.get(0).getHealthCoefficient(),
                TOLERANCE);
    }

    @Test
    public void testSpawnHealthCoefficientUpperBound() {
        final double motherHealthCoefficient = 1.0;
        final double fryHealthCoefficient = 1.0;
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, null, ageInWeeks, true, 0,
                motherHealthCoefficient);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals(fryHealthCoefficient, spawn.get(0).getHealthCoefficient(),
                TOLERANCE);
    }

    @Test
    public void testSpawnGenerationNumberLowerBound() {
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, null, ageInWeeks, true, 0, 0.0);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals(1, spawn.get(0).getGenerationNumber());
    }

    @Test
    public void testSpawnGenerationNumberAboveLowerBound() {
        final int motherGenerationNumber = 5;
        final int fryGenerationNumber = motherGenerationNumber + 1;
        final int ageInWeeks = 10;
        Guppy mother = new Guppy(null, null, ageInWeeks, true,
                motherGenerationNumber, 0.0);
        ArrayList<Guppy> spawn = new ArrayList<Guppy>();

        do {
            spawn = mother.spawn();
        } while (spawn.size() == 0);

        assertEquals(fryGenerationNumber, spawn.get(0).getGenerationNumber());
    }
*/
}
