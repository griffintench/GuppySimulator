package ca.bcit.comp1510.guppies;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.number.IsCloseTo;

/*
 * Questions:
 * If I do import static org.hamcrest.CoreMatchers.is, the "is" is striked through. Why?
 * Is there any way to change the colours of the Errors and Failures x-boxes?
 * closeTo acting weird
 * not sure about my spawn test - is it sufficient?
 * testIncrementAgesIncrementedMultipleGuppies() - can I really not use multiple asserts?
 */

public class PoolTest {
/*
    private static final double POSITIVE_DOUBLE = 5.0;

    private static final double VALID_TEMP = (Pool.MINIMUM_POOL_TEMP_CELSIUS
            + Pool.MAXIMUM_POOL_TEMP_CELSIUS) / 2;

    private static final double TOLERANCE = 0.0000001;

    private static final double VOLUME = 2000.0;

    private static Pool pool;

    @Before
    public void setUp() throws Exception {
        pool = new Pool();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleValidName() {
        Pool complexPool = new Pool("Name", 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getName(), is("Name"));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleNullName() {
        Pool complexPool = new Pool(null, 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getName(), is(Pool.DEFAULT_POOL_NAME));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleEmptyName() {
        Pool complexPool = new Pool("", 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getName(), is(Pool.DEFAULT_POOL_NAME));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleWhitespaceName() {
        Pool complexPool = new Pool(" ", 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getName(), is(Pool.DEFAULT_POOL_NAME));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoublePositiveVolume() {
        Pool complexPool = new Pool("", 1.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getVolumeLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleZeroVolume() {
        Pool complexPool = new Pool("", 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getVolumeLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleNegativeVolume() {
        Pool complexPool = new Pool("", -1.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getVolumeLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleTemperatureBelowMin() {
        Pool complexPool = new Pool("", 0.0,
                Pool.MINIMUM_POOL_TEMP_CELSIUS - 1.0, 0.0, 0.0);
        assertThat(complexPool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.DEFAULT_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleMinTemperature() {
        Pool complexPool = new Pool("", 0.0, Pool.MINIMUM_POOL_TEMP_CELSIUS,
                0.0, 0.0);
        assertThat(complexPool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.MINIMUM_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleValidTemperature() {
        Pool complexPool = new Pool("", 0.0,
                Pool.MINIMUM_POOL_TEMP_CELSIUS + 1.0, 0.0, 0.0);
        assertThat(complexPool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo.closeTo(
                        Pool.MINIMUM_POOL_TEMP_CELSIUS + 1.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleMaxTemperature() {
        Pool complexPool = new Pool("", 0.0, Pool.MAXIMUM_POOL_TEMP_CELSIUS,
                0.0, 0.0);
        assertThat(complexPool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.MAXIMUM_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleTemperatureAboveMax() {
        Pool complexPool = new Pool("", 0.0,
                Pool.MAXIMUM_POOL_TEMP_CELSIUS + 1.0, 0.0, 0.0);
        assertThat(complexPool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.DEFAULT_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoublePHBelowMin() {
        Pool complexPool = new Pool("", 0.0, 0.0, -1.0, 0.0);
        assertThat(complexPool.getPH(), is(org.hamcrest.number.IsCloseTo
                .closeTo(Pool.NEUTRAL_PH, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleMinPH() {
        Pool complexPool = new Pool("", 0.0, 0.0, 0.0, 0.0);
        assertThat(complexPool.getPH(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleValidPH() {
        Pool complexPool = new Pool("", 0.0, 0.0, 1.0, 0.0);
        assertThat(complexPool.getPH(),
                is(org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleMaxPH() {
        Pool complexPool = new Pool("", 0.0, 0.0, 2 * Pool.NEUTRAL_PH, 0.0);
        assertThat(complexPool.getPH(), is(org.hamcrest.number.IsCloseTo
                .closeTo(2 * Pool.NEUTRAL_PH, TOLERANCE)));
    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoublePHAboveMax() {
        Pool complexPool = new Pool("", 0.0, 0.0, 2 * Pool.NEUTRAL_PH + 1.0,
                0.0);
        assertThat(complexPool.getPH(), is(org.hamcrest.number.IsCloseTo
                .closeTo(Pool.NEUTRAL_PH, TOLERANCE)));
    }

    @Test
    public void testGetName() {
        assertThat(pool.getName(), is(Pool.DEFAULT_POOL_NAME));
    }

    @Test
    public void testSetNameValidNameNoFormattingNecessary() {
        pool.setName("Name");
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testSetNameValidNameWhitespace() {
        pool.setName("N ame");
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testSetNameValidNameImproperCase() {
        pool.setName("nAmE");
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testSetNameNullName() {
        pool.setName("Name");
        pool.setName(null);
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testSetNameEmptyName() {
        pool.setName("Name");
        pool.setName("");
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testSetNameWhitespaceName() {
        pool.setName("Name");
        pool.setName(" ");
        assertThat(pool.getName(), is("Name"));
    }

    @Test
    public void testGetVolumeLitres() {
        assertThat(pool.getVolumeLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testSetVolumeLitresPositiveVolume() {
        pool.setVolumeLitres(POSITIVE_DOUBLE);
        assertThat(pool.getVolumeLitres(), is(org.hamcrest.number.IsCloseTo
                .closeTo(POSITIVE_DOUBLE, TOLERANCE)));
    }

    @Test
    public void testSetVolumeLitresZeroVolume() {
        pool.setVolumeLitres(POSITIVE_DOUBLE);
        pool.setVolumeLitres(0.0);
        assertThat(pool.getVolumeLitres(), is(org.hamcrest.number.IsCloseTo
                .closeTo(POSITIVE_DOUBLE, TOLERANCE)));
    }

    @Test
    public void testSetVolumeLitresNegativeVolume() {
        pool.setVolumeLitres(POSITIVE_DOUBLE);
        pool.setVolumeLitres(-1.0 * POSITIVE_DOUBLE);
        assertThat(pool.getVolumeLitres(), is(org.hamcrest.number.IsCloseTo
                .closeTo(POSITIVE_DOUBLE, TOLERANCE)));
    }

    @Test
    public void testGetTemperatureCelsius() {
        assertThat(pool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.DEFAULT_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testSetTemperatureCelsiusValidTemperatureWithinBounds() {
        pool.setTemperatureCelsius(VALID_TEMP);
        assertThat(pool.getTemperatureCelsius(), is(
                org.hamcrest.number.IsCloseTo.closeTo(VALID_TEMP, TOLERANCE)));
    }

    @Test
    public void testSetTemperatureCelsiusValidTemperatureLowerBound() {
        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS);
        assertThat(pool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.MINIMUM_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testSetTemperatureCelsiusValidTemperatureUpperBound() {
        pool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS);
        assertThat(pool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.MAXIMUM_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testSetTemperatureCelsiusLowTemperature() {
        pool.setTemperatureCelsius(VALID_TEMP);
        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS - 1.0);
        assertThat(pool.getTemperatureCelsius(), is(
                org.hamcrest.number.IsCloseTo.closeTo(VALID_TEMP, TOLERANCE)));
    }

    @Test
    public void testSetTemperatureCelsiusHighTemperature() {
        pool.setTemperatureCelsius(VALID_TEMP);
        pool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS * 2);
        assertThat(pool.getTemperatureCelsius(), is(
                org.hamcrest.number.IsCloseTo.closeTo(VALID_TEMP, TOLERANCE)));
    }

    @Test
    public void testGetPH() {
        assertThat(pool.getPH(), is(org.hamcrest.number.IsCloseTo
                .closeTo(Pool.NEUTRAL_PH, TOLERANCE)));
    }

    @Test
    public void testSetPHValidPH() {
        pool.setPH(Pool.NEUTRAL_PH - 1.0);
        assertThat(pool.getPH(), org.hamcrest.number.IsCloseTo
                .closeTo(Pool.NEUTRAL_PH - 1.0, TOLERANCE));
    }

    @Test
    public void testGetNutrientCoefficient() {
        assertThat(pool.getNutrientCoefficient(), org.hamcrest.number.IsCloseTo
                .closeTo(Pool.DEFAULT_NUTRIENT_COEFFICIENT, TOLERANCE));
    }

    @Test
    public void testSetNutrientCoefficientValidBetweenBounds() {
        final double validNutrientCoefficient = 0.1;
        pool.setNutrientCoefficient(validNutrientCoefficient);
        assertThat(pool.getNutrientCoefficient(), org.hamcrest.number.IsCloseTo
                .closeTo(validNutrientCoefficient, TOLERANCE));
    }

    @Test
    public void testSetNutrientCoefficientValidLowerBound() {
        pool.setNutrientCoefficient(0);
        assertThat(pool.getNutrientCoefficient(),
                org.hamcrest.number.IsCloseTo.closeTo(0, TOLERANCE));
    }

    @Test
    public void testSetNutrientCoefficientValidUpperBound() {
        pool.setNutrientCoefficient(1.0);
        assertThat(pool.getNutrientCoefficient(),
                org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE));
    }

    @Test
    public void testSetNutrientCoefficientNegative() {
        pool.setNutrientCoefficient(1.0);
        pool.setNutrientCoefficient(-1.0);
        assertThat(pool.getNutrientCoefficient(),
                org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE));
    }

    @Test
    public void testSetNutrientCoefficientTooHigh() {
        final double highNutrientCoefficient = 2.0;
        pool.setNutrientCoefficient(0);
        pool.setNutrientCoefficient(highNutrientCoefficient);
        assertThat(pool.getNutrientCoefficient(),
                org.hamcrest.number.IsCloseTo.closeTo(0, TOLERANCE));
    }

    @Test
    public void testGetIdentificationNumber() {
        assertThat(pool.getIdentificationNumber(), is(Pool.getNumberCreated()));
    }

    @Test
    public void testGetGuppiesInPool() {
        assertThat(pool.getGuppiesInPool(),
                is(equalTo(new ArrayList<Guppy>())));
    }

    @Test
    public void testSetGuppiesInPoolOneGuppy() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        guppyArray.add(new Guppy());
        pool.setGuppiesInPool(guppyArray);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testSetGuppiesInPoolMaxGuppies() {
        pool.setVolumeLitres(VOLUME);
        final int maxNumberOfGuppies = (int) (pool.getVolumeLitres()
                / Guppy.MINIMUM_WATER_VOLUME_ML);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        for (int i = 0; i < maxNumberOfGuppies; i++) {
            guppyArray.add(new Guppy());
        }
        pool.setGuppiesInPool(guppyArray);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testSetGuppiesInPoolTooManyGuppiesLowerBound() {
        pool.setVolumeLitres(VOLUME);

        ArrayList<Guppy> validGuppyArray = new ArrayList<Guppy>();
        validGuppyArray.add(new Guppy());
        pool.setGuppiesInPool(validGuppyArray);

        final int maxNumberOfGuppies = (int) (pool.getVolumeLitres()
                / Guppy.MINIMUM_WATER_VOLUME_ML);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        for (int i = 1; i <= maxNumberOfGuppies + 1; i++) {
            guppyArray.add(new Guppy());
        }
        pool.setGuppiesInPool(guppyArray);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testSetGuppiesInPoolTooManyGuppiesAboveLowerBound() {
        pool.setVolumeLitres(VOLUME);

        ArrayList<Guppy> validGuppyArray = new ArrayList<Guppy>();
        validGuppyArray.add(new Guppy());
        pool.setGuppiesInPool(validGuppyArray);

        final int numberOfGuppies = 500;
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        for (int i = 0; i < numberOfGuppies; i++) {
            guppyArray.add(new Guppy());
        }
        pool.setGuppiesInPool(guppyArray);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testSetGuppiesInPoolNull() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> validGuppyArray = new ArrayList<Guppy>();
        validGuppyArray.add(new Guppy());
        pool.setGuppiesInPool(validGuppyArray);

        pool.setGuppiesInPool(null);
        assertThat(pool.getGuppiesInPool(), is(equalTo(validGuppyArray)));
    }

    @Test
    public void testChangeTemperatureUpperBound() {
        pool.setTemperatureCelsius(0.0);
        pool.changeTemperature(Pool.MAXIMUM_POOL_TEMP_CELSIUS);
        assertThat(pool.getTemperatureCelsius(),
                is(org.hamcrest.number.IsCloseTo
                        .closeTo(Pool.MAXIMUM_POOL_TEMP_CELSIUS, TOLERANCE)));
    }

    @Test
    public void testGetNumberCreated() {
        int number = Pool.getNumberCreated();
        Pool anotherPool = new Pool();
        assertThat(Pool.getNumberCreated(), is(number + 1));
    }

    @Test
    public void testAddGuppyToEmptyArray() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        Guppy guppyToAdd = new Guppy();

        pool.addGuppy(guppyToAdd);
        assertThat(pool.getGuppiesInPool().get(0), is(guppyToAdd));
    }

    @Test
    public void testAddGuppyToEndOfArray() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();

        Guppy guppy0 = new Guppy();
        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        guppyArray.add(guppy0);
        guppyArray.add(guppy1);

        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy2);
        assertThat(pool.getGuppiesInPool().get(2), is(guppy2));
    }

    // obsolete now that guppiesInPool is an ArrayList
    // @Test
    // public void testAddGuppyToMiddleOfArray() {
    // pool.setVolumeLitres(VOLUME);
    // final int sizeOfGuppyArray = (int) (pool.getVolumeLitres()
    // / Guppy.MINIMUM_WATER_VOLUME_ML);
    // ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
    //
    // Guppy guppy0 = new Guppy();
    // Guppy guppy1 = new Guppy();
    // Guppy guppy2 = new Guppy();
    // guppyArray[0] = guppy0;
    // guppyArray[2] = guppy2;
    //
    // pool.setGuppiesInPool(guppyArray);
    // pool.addGuppy(guppy1);
    // assertThat(pool.getGuppiesInPool()[1], is(guppy1));
    // }

    @Test
    public void testAddGuppyArrayFull() {
        Guppy guppy = new Guppy();
        pool.addGuppy(guppy);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        guppyArray.add(guppy);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testAddGuppyNull() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(null);
        assertThat(pool.getGuppiesInPool(), is(equalTo(guppyArray)));
    }

    @Test
    public void testGetPopulationZero() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        assertThat(pool.getPopulation(), is(0));
    }

    @Test
    public void testGetPopulationMoreThanZero() {
        final int numberOfGuppies = 3;
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        for (int i = 1; i <= numberOfGuppies; i++) {
            pool.addGuppy(new Guppy());
        }
        assertThat(pool.getPopulation(), is(numberOfGuppies));
    }

    private static int numberOfDeadGuppies(ArrayList<Guppy> guppyArray) {
        Guppy currentGuppy;
        int numberOfDeadGuppies = 0;
        for (int i = 0; i < guppyArray.size(); i++) {
            currentGuppy = guppyArray.get(i);
            if (!currentGuppy.getIsAlive()) {
                numberOfDeadGuppies++;
            }
        }
        return numberOfDeadGuppies;
    }

    @Test
    public void testApplyNutrientCoefficientZeroNutrientCoefficient() {
        pool.setNutrientCoefficient(0.0);
        pool.setVolumeLitres(VOLUME);
        final int sizeOfGuppyArray = (int) (pool.getVolumeLitres()
                / Guppy.MINIMUM_WATER_VOLUME_ML);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        for (int i = 1; i <= sizeOfGuppyArray; i++) {
            pool.addGuppy(new Guppy());
        }

        pool.applyNutrientCoefficient();
        assertThat(numberOfDeadGuppies(pool.getGuppiesInPool()),
                is(sizeOfGuppyArray));
    }

    @Test
    public void testApplyNutrientCoefficientOneNutrientCoefficient() {
        pool.setNutrientCoefficient(1.0);
        pool.setVolumeLitres(VOLUME);
        final int sizeOfGuppyArray = (int) (pool.getVolumeLitres()
                / Guppy.MINIMUM_WATER_VOLUME_ML);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        for (int i = 1; i <= sizeOfGuppyArray; i++) {
            pool.addGuppy(new Guppy());
        }

        pool.applyNutrientCoefficient();
        assertThat(numberOfDeadGuppies(pool.getGuppiesInPool()), is(0));
    }

    @Test
    public void testApplyNutrientCoefficientBetweenZeroAndOne() {
        final int numberOfTries = 2000;
        final double nutrientCoefficient = 0.5;
        Pool trialPool;
        Integer dead;
        int sizeOfGuppyArray = 0;

        ArrayList<Integer> numbersOfDeadGuppies = new ArrayList<Integer>();

        for (int j = 1; j <= numberOfTries; j++) {
            trialPool = new Pool();
            trialPool.setVolumeLitres(VOLUME);
            trialPool.setNutrientCoefficient(nutrientCoefficient);
            sizeOfGuppyArray = (int) (trialPool.getVolumeLitres()
                    / Guppy.MINIMUM_WATER_VOLUME_ML);
            ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
            trialPool.setGuppiesInPool(guppyArray);
            for (int i = 1; i <= sizeOfGuppyArray; i++) {
                trialPool.addGuppy(new Guppy());
            }

            trialPool.applyNutrientCoefficient();
            dead = numberOfDeadGuppies(trialPool.getGuppiesInPool());
            if (!numbersOfDeadGuppies.contains(dead)) {
                numbersOfDeadGuppies.add(dead);
            }
        }

        assertThat(numbersOfDeadGuppies.size(), is(sizeOfGuppyArray + 1));
    }

    @Test
    public void testRemoveDeadGuppiesNoDeadGuppies() {
        final int numberOfGuppies = 3;
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        for (int i = 1; i <= numberOfGuppies; i++) {
            pool.addGuppy(new Guppy());
        }

        pool.removeDeadGuppies();

        assertThat(pool.getPopulation(), is(numberOfGuppies));
    }

    @Test
    public void testRemoveDeadGuppiesOneDeadGuppy() {
        final int numberOfGuppies = 3;
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        for (int i = 1; i <= numberOfGuppies; i++) {
            pool.addGuppy(new Guppy());
        }
        pool.getGuppiesInPool().get(0).setIsAlive(false);

        pool.removeDeadGuppies();

        assertThat(pool.getPopulation(), is(numberOfGuppies - 1));
    }

    @Test
    public void testRemoveDeadGuppiesAllGuppiesDead() {
        final int numberOfGuppies = 3;
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        Guppy currentGuppy;
        for (int i = 1; i <= numberOfGuppies; i++) {
            currentGuppy = new Guppy();
            currentGuppy.setIsAlive(false);
            pool.addGuppy(currentGuppy);
        }

        pool.removeDeadGuppies();

        assertThat(pool.getPopulation(), is(0));
    }

    @Test
    public void testGetGuppyVolumeRequirementInLitresNoGuppies() {
        assertThat(pool.getGuppyVolumeRequirementInLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetGuppyVolumeRequirementInLitresOneGuppy() {
        final int millilitresInLitre = 1000;

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        pool.addGuppy(new Guppy());
        assertThat(pool.getGuppyVolumeRequirementInLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(
                        Guppy.MINIMUM_WATER_VOLUME_ML / millilitresInLitre,
                        TOLERANCE)));
    }

    @Test
    public void testGetGuppyVolumeRequirementInLitresMultipleGuppies() {
        final int millilitresInLitre = 1000;

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        pool.addGuppy(new Guppy());
        pool.addGuppy(new Guppy());
        assertThat(pool.getGuppyVolumeRequirementInLitres(),
                is(org.hamcrest.number.IsCloseTo.closeTo(
                        2 * Guppy.MINIMUM_WATER_VOLUME_ML / millilitresInLitre,
                        TOLERANCE)));
    }

    @Test
    public void testGetAverageAgeInWeeksNoGuppies() {
        assertThat(pool.getAverageAgeInWeeks(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetAverageAgeInWeeksOneGuppy() {
        Guppy guppy = new Guppy();
        guppy.setAgeInWeeks(2);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy);

        assertThat(pool.getAverageAgeInWeeks(),
                is(org.hamcrest.number.IsCloseTo.closeTo(2.0, TOLERANCE)));
    }

    @Test
    public void testGetAverageAgeInWeeksMultipleGuppies() {
        final double average = 1.25;

        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        Guppy guppy3 = new Guppy();
        Guppy guppy4 = new Guppy();

        guppy1.setAgeInWeeks(1);
        guppy2.setAgeInWeeks(2);
        guppy3.setAgeInWeeks(1);
        guppy4.setAgeInWeeks(1);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy1);
        pool.addGuppy(guppy2);
        pool.addGuppy(guppy3);
        pool.addGuppy(guppy4);

        assertThat(pool.getAverageAgeInWeeks(),
                is(org.hamcrest.number.IsCloseTo.closeTo(average, TOLERANCE)));
    }

    @Test
    public void testGetAverageHealthCoefficientNoGuppies() {
        assertThat(pool.getAverageHealthCoefficient(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetAverageHealthCoefficientOneGuppy() {
        final double validHealthCoefficient = 0.75;
        Guppy guppy = new Guppy();
        guppy.setHealthCoefficient(validHealthCoefficient);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy);

        assertThat(pool.getAverageHealthCoefficient(),
                is(org.hamcrest.number.IsCloseTo.closeTo(validHealthCoefficient,
                        TOLERANCE)));
    }

    @Test
    public void testGetAverageHealthCoefficientMultipleGuppies() {
        final double average = 0.25;

        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        Guppy guppy3 = new Guppy();
        Guppy guppy4 = new Guppy();

        guppy1.setHealthCoefficient(0.0);
        guppy2.setHealthCoefficient(0.0);
        guppy3.setHealthCoefficient(0.0);
        guppy4.setHealthCoefficient(1.0);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy1);
        pool.addGuppy(guppy2);
        pool.addGuppy(guppy3);
        pool.addGuppy(guppy4);

        assertThat(pool.getAverageHealthCoefficient(),
                is(org.hamcrest.number.IsCloseTo.closeTo(average, TOLERANCE)));
    }

    @Test
    public void testGetFemalePercentageNoGuppies() {
        assertThat(pool.getFemalePercentage(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetFemalePercentageNoFemale() {
        Guppy guppy = new Guppy();
        guppy.setIsFemale(false);

        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy);

        assertThat(pool.getFemalePercentage(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetFemalePercentageAllFemale() {
        Guppy guppy = new Guppy();

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy);

        assertThat(pool.getFemalePercentage(),
                is(org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE)));
    }

    @Test
    public void testGetFemalePercentageSomeFemale() {
        final double percentage = 0.25;

        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        Guppy guppy3 = new Guppy();
        Guppy guppy4 = new Guppy();

        guppy2.setIsFemale(false);
        guppy3.setIsFemale(false);
        guppy4.setIsFemale(false);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy1);
        pool.addGuppy(guppy2);
        pool.addGuppy(guppy3);
        pool.addGuppy(guppy4);

        assertThat(pool.getFemalePercentage(), is(
                org.hamcrest.number.IsCloseTo.closeTo(percentage, TOLERANCE)));
    }

    @Test
    public void testGetMedianAgeNoGuppies() {
        assertThat(pool.getMedianAge(),
                is(org.hamcrest.number.IsCloseTo.closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void testGetMedianAgeOneGuppy() {
        Guppy guppy = new Guppy();
        guppy.setAgeInWeeks(2);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy);

        assertThat(pool.getMedianAge(),
                is(org.hamcrest.number.IsCloseTo.closeTo(2.0, TOLERANCE)));
    }

    @Test
    public void testGetMedianAgeOddNumber() {
        final int oldAge = 3;

        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        Guppy guppy3 = new Guppy();

        guppy1.setAgeInWeeks(0);
        guppy2.setAgeInWeeks(1);
        guppy3.setAgeInWeeks(oldAge);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy1);
        pool.addGuppy(guppy2);
        pool.addGuppy(guppy3);

        assertThat(pool.getMedianAge(),
                is(org.hamcrest.number.IsCloseTo.closeTo(1.0, TOLERANCE)));
    }

    @Test
    public void testGetMedianAgeEvenNumber() {
        final int oldAge = 3;
        final double median = (1 + oldAge) / 2.0;

        Guppy guppy1 = new Guppy();
        Guppy guppy2 = new Guppy();
        Guppy guppy3 = new Guppy();
        Guppy guppy4 = new Guppy();

        guppy1.setAgeInWeeks(0);
        guppy2.setAgeInWeeks(1);
        guppy3.setAgeInWeeks(oldAge);
        guppy4.setAgeInWeeks(oldAge);

        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(guppy1);
        pool.addGuppy(guppy2);
        pool.addGuppy(guppy3);
        pool.addGuppy(guppy4);

        assertThat(pool.getMedianAge(),
                is(org.hamcrest.number.IsCloseTo.closeTo(median, TOLERANCE)));
    }

    @Test
    public void testSpawn() {
        pool.setVolumeLitres(VOLUME);
        final int sizeOfGuppyArray = (int) (pool.getVolumeLitres()
                / Guppy.MINIMUM_WATER_VOLUME_ML);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int numberOfMothers = 10;
        final int ageInWeeks = 10;
        for (int i = 1; i <= numberOfMothers; i++) {
            pool.addGuppy(new Guppy(null, null, ageInWeeks, true, 0, 1.0));
        }

        int numberOfBabies = pool.spawn();

        assertThat(pool.getPopulation(),
                is(equalTo(numberOfMothers + numberOfBabies)));
    }

    @Test
    public void testIncrementAgesOneGuppyAgeZeroIncremented() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        pool.addGuppy(new Guppy(null, null, 0, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getAgeInWeeks(), is(1));
    }

    @Test
    public void testIncrementAgesOneGuppyPositiveAgeIncremented() {
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge = 10;
        pool.addGuppy(new Guppy(null, null, originalAge, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getAgeInWeeks(),
                is(originalAge + 1));
    }

    @Test
    public void testIncrementAgesOneGuppyDoesntDie() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge = 10;
        pool.addGuppy(new Guppy(null, null, originalAge, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getIsAlive(), is(true));
    }

    @Test
    public void testIncrementAgesOneGuppyDeathIncrementation() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getAgeInWeeks(),
                is(equalTo(Guppy.MAXIMUM_AGE_IN_WEEKS)));
    }

    @Test
    public void testIncrementAgesOneGuppyDeath() {
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getIsAlive(), is(false));
    }

    @Test
    public void testIncrementAgesReturnValueNoGuppies() {
        assertThat(pool.incrementAges(), is(0));
    }

    @Test
    public void testIncrementAgesReturnValueOneGuppyDoesntDie() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        pool.addGuppy(new Guppy(null, null, 0, true, 0, 1.0));

        assertThat(pool.incrementAges(), is(0));
    }

    @Test
    public void testIncrementAgesReturnValueOneGuppyDies() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge, true, 0, 1.0));

        assertThat(pool.incrementAges(), is(1));
    }

    @Test
    public void testIncrementAgesIncrementedMultipleGuppies() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = 5;
        final int originalAge2 = 16;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0)
                .getAgeInWeeks() == originalAge1 + 1
                && pool.getGuppiesInPool().get(1)
                        .getAgeInWeeks() == originalAge2 + 1,
                is(true));
    }

    @Test
    public void testIncrementAgesMultipleGuppiesOneKilled() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = 5;
        final int originalAge2 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(1).getIsAlive(), is(false));
    }

    @Test
    public void testIncrementAgesMultipleGuppiesOneNotKilled() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = 5;
        final int originalAge2 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        pool.incrementAges();

        assertThat(pool.getGuppiesInPool().get(0).getIsAlive(), is(true));
    }

    @Test
    public void testIncrementAgesMultipleGuppiesAllKilled() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        final int originalAge2 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        pool.incrementAges();

        assertThat(
                !pool.getGuppiesInPool().get(0).getIsAlive()
                        && !pool.getGuppiesInPool().get(1).getIsAlive(),
                is(true));
    }

    @Test
    public void testIncrementAgesReturnValueMultipleGuppiesNoneDies() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = 5;
        final int originalAge2 = 16;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        assertThat(pool.incrementAges(), is(0));
    }

    @Test
    public void testIncrementAgesReturnValueMultipleGuppiesOneDies() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = 5;
        final int originalAge2 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        assertThat(pool.incrementAges(), is(1));
    }

    @Test
    public void testIncrementAgesReturnValuesMultipleGuppiesAllDie() {
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);

        final int originalAge1 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        final int originalAge2 = Guppy.MAXIMUM_AGE_IN_WEEKS - 1;
        pool.addGuppy(new Guppy(null, null, originalAge1, true, 0, 1.0));
        pool.addGuppy(new Guppy(null, null, originalAge2, true, 0, 1.0));

        assertThat(pool.incrementAges(), is(2));
    }

    @Test
    public void testAdjustForCrowdingZeroPopulation() {
        assertThat(pool.adjustForCrowding(), is(0));
    }
    
    @Test
    public void testAdjustForCrowdingNoDeathsNecessary() {
        pool.setVolumeLitres(VOLUME);
        pool.addGuppy(new Guppy(null, null, 0, true, 0, 1.0));
        
        assertThat(pool.adjustForCrowding(), is(0));
    }
    
    @Test
    public void testAdjustForCrowdingDeathsNecessary() {
        final double enoughForOneGuppy = 0.4;
        final double lowerHealth = 0.25;
        final double higherHealth = 0.75;
        
        pool.setVolumeLitres(enoughForOneGuppy);
        pool.addGuppy(new Guppy(null, null, 0, true, 0, lowerHealth));
        pool.addGuppy(new Guppy(null, null, 0, true, 0, higherHealth));

        assertThat(pool.adjustForCrowding(), is(1));
    }
    
    @Test
    public void testAdjustForCrowdingKillsWeaker() {
        final double enoughForOneGuppy = 0.4;
        final double lowerHealth = 0.25;
        final double higherHealth = 0.75;
        
        pool.setVolumeLitres(enoughForOneGuppy);
        Guppy weakerGuppy = new Guppy(null, null, 0, true, 0, lowerHealth);
        Guppy strongerGuppy = new Guppy(null, null, 0, true, 0, higherHealth);
        pool.addGuppy(weakerGuppy);
        pool.addGuppy(strongerGuppy);
        
        pool.adjustForCrowding();

        assertThat(weakerGuppy.getIsAlive(), is(false));
    }
    
    @Test
    public void testAdjustForCrowdingDoesNotKillStronger() {
        final double enoughForOneGuppy = 0.4;
        final double lowerHealth = 0.25;
        final double higherHealth = 0.75;
        
        pool.setVolumeLitres(enoughForOneGuppy);
        Guppy weakerGuppy = new Guppy(null, null, 0, true, 0, lowerHealth);
        Guppy strongerGuppy = new Guppy(null, null, 0, true, 0, higherHealth);
        pool.addGuppy(weakerGuppy);
        pool.addGuppy(strongerGuppy);
        
        pool.adjustForCrowding();

        assertThat(strongerGuppy.getIsAlive(), is(true));
    }
    */
}
