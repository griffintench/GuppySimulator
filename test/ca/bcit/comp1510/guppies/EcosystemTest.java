package ca.bcit.comp1510.guppies;

/*
 * Questions for Chris:
 * -is it necessary to test the simulation methods
 * -spawn
 * 
 * I had to modify removeDeadGuppies() in Pool in a weird way
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.number.IsCloseTo;

public class EcosystemTest {

    private static final double VOLUME = 2000.0;

    private static Ecosystem ecosystem;

    @Before
    public void setUp() throws Exception {
        ecosystem = new Ecosystem();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPoolsNoPools() {
        assertThat(ecosystem.getPools(), is(equalTo(new ArrayList<Pool>())));
    }

    @Test
    public void testSetPoolsValid() {
        ArrayList<Pool> poolArray = new ArrayList<Pool>();
        poolArray.add(new Pool());
        ecosystem.setPools(poolArray);
        assertThat(ecosystem.getPools(), is(equalTo(poolArray)));
    }

    @Test
    public void testSetPoolsNull() {
        ecosystem.setPools(null);
        assertThat(ecosystem.getPools(), is(equalTo(new ArrayList<Pool>())));
    }

    @Test
    public void testAddPoolNull() {
        ecosystem.addPool(null);
        assertThat(ecosystem.getPools(), is(equalTo(new ArrayList<Pool>())));
    }

    @Test
    public void testAddPoolValid() {
        Pool pool = new Pool();
        ecosystem.addPool(pool);
        ArrayList<Pool> poolArray = new ArrayList<Pool>();
        poolArray.add(pool);
        assertThat(ecosystem.getPools(), is(equalTo(poolArray)));
    }

    @Test
    public void testReset() {
        ecosystem.addPool(new Pool());
        ecosystem.reset();
        assertThat(ecosystem.getPools(), is(equalTo(new ArrayList<Pool>())));
    }

    @Test
    public void testGetGuppyPopulationNoPools() {
        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(0)));
    }

    @Test
    public void testGetGuppyPopulationOnePoolNoGuppies() {
        ecosystem.addPool(new Pool());
        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(0)));
    }

    @Test
    public void testGetGuppyPopulationOnePoolWithGuppies() {
        Pool pool = new Pool();
        pool.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool.setGuppiesInPool(guppyArray);
        pool.addGuppy(new Guppy());

        ecosystem.addPool(pool);

        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(1)));
    }

    @Test
    public void testGetGuppyPopulationMultiplePoolsNoGuppies() {
        ecosystem.addPool(new Pool());
        ecosystem.addPool(new Pool());
        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(0)));
    }

    @Test
    public void testGetGuppyPopulationMultiplePoolsSomeWithGuppies() {
        final int numberOfGuppies = 3;

        Pool pool1 = new Pool();
        Pool pool2 = new Pool();
        pool1.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray = new ArrayList<Guppy>();
        pool1.setGuppiesInPool(guppyArray);

        for (int i = 1; i <= numberOfGuppies; i++) {
            pool1.addGuppy(new Guppy());
        }

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getGuppyPopulation(),
                is(equalTo(numberOfGuppies)));

    }

    @Test
    public void testGetGuppyPopulationMultiplePoolsAllWithGuppies() {
        final int numberOfGuppies1 = 3;
        final int numberOfGuppies2 = 5;

        Pool pool1 = new Pool();
        Pool pool2 = new Pool();
        pool1.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray1 = new ArrayList<Guppy>();
        pool1.setGuppiesInPool(guppyArray1);

        pool2.setVolumeLitres(VOLUME);
        ArrayList<Guppy> guppyArray2 = new ArrayList<Guppy>();
        pool2.setGuppiesInPool(guppyArray2);

        for (int i = 1; i <= numberOfGuppies1; i++) {
            pool1.addGuppy(new Guppy());
        }
        for (int i = 1; i <= numberOfGuppies2; i++) {
            pool2.addGuppy(new Guppy());
        }

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getGuppyPopulation(),
                is(equalTo(numberOfGuppies1 + numberOfGuppies2)));
    }

}
