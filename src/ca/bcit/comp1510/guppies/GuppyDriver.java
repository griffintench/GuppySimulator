/**
 * 
 */
package ca.bcit.comp1510.guppies;

/**
 * @author griffin
 *
 */
public class GuppyDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        testGettersAndSetters();
    }

    private static void testGettersAndSetters() {
        boolean success = true;

        Guppy guppy1 = new Guppy();
        if (!guppy1.getGenus().equals(Guppy.DEFAULT_GENUS)
                || !guppy1.getSpecies().equals(Guppy.DEFAULT_SPECIES)
                || guppy1.getAgeInWeeks() != 0 || !guppy1.getIsFemale()
                || guppy1.getGenerationNumber() != 0 || !guppy1.getIsAlive()
                || guppy1
                        .getHealthCoefficient() != Guppy.DEFAULT_HEALTH_COEFFICIENT
                || guppy1.getIdentificationNumber() != 1) {
            System.out.println("PROBLEM: Default constructor doesn't work");
        } else {
            System.out.println("Default constructor works");
        }

        guppy1.setGenus(null);
        if (guppy1.getGenus().equals(Guppy.DEFAULT_GENUS)) {
            System.out.println("Null genus works");
        } else {
            System.out.println("PROBLEM: Null genus doesn't work");
        }
        guppy1.setGenus("a");
        if (guppy1.getGenus().equals("A")) {
            System.out.println("One-character string genus works");
        } else {
            System.out.println(
                    "PROBLEM: One-character string genus doesn't work");
        }
        guppy1.setGenus("");
        if (guppy1.getGenus().equals(Guppy.DEFAULT_GENUS)) {
            System.out.println("Empty string genus works");
        } else {
            System.out.println("PROBLEM: Empty string genus doesn't work");
        }
        guppy1.setGenus("gEnUs");
        if (guppy1.getGenus().equals("Genus")) {
            System.out.println("Genus works");
        } else {
            System.out.println("PROBLEM: Genus doesn't work");
        }

        guppy1.setSpecies(null);
        if (guppy1.getSpecies().equals(Guppy.DEFAULT_SPECIES)) {
            System.out.println("Null species works");
        } else {
            System.out.println("PROBLEM: Null species doesn't work");
        }
        guppy1.setSpecies("A");
        if (guppy1.getSpecies().equals("a")) {
            System.out.println("One-character string species works");
        } else {
            System.out.println(
                    "PROBLEM: One-character string species doesn't work");
        }
        guppy1.setSpecies("");
        if (guppy1.getSpecies().equals(Guppy.DEFAULT_SPECIES)) {
            System.out.println("Empty string species works");
        } else {
            System.out.println("PROBLEM: Empty string species doesn't work");
        }
        guppy1.setSpecies("SpEcIeS");
        if (guppy1.getSpecies().equals("species")) {
            System.out.println("Species works");
        } else {
            System.out.println("PROBLEM: Species doesn't work");
        }
        guppy1.setAgeInWeeks(-1);
        if (guppy1.getAgeInWeeks() == 0) {
            System.out.println("Negative age works");
        } else {
            System.out.println("PROBLEM: Negative age doesn't work");
        }
        guppy1.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS + 1);
        if (guppy1.getAgeInWeeks() == 0) {
            System.out.println("Overlarge age works");
        } else {
            System.out.println("PROBLEM: Overlarge age doesn't work");
        }
        guppy1.setAgeInWeeks(2);
        if (guppy1.getAgeInWeeks() == 2) {
            System.out.println("Regular age works");
        } else {
            System.out.println("PROBLEM: Regular age doesn't work");
        }
        guppy1.setIsFemale(false);
        if (!guppy1.getIsFemale()) {
            System.out.println("Male guppy works");
        } else {
            System.out.println("PROBLEM: Male guppy doesn't work");
        }
        guppy1.setIsFemale(true);
        if (guppy1.getIsFemale()) {
            System.out.println("Female guppy works");
        } else {
            System.out.println("PROBLEM: Female guppy doesn't work");
        }
        guppy1.setGenerationNumber(-1);
        if (guppy1.getGenerationNumber() == 0) {
            System.out.println("Negative generation number works");
        } else {
            System.out.println(
                    "PROBLEM: Negative generation number doesn't work");
        }
        guppy1.setGenerationNumber(2);
        if (guppy1.getGenerationNumber() == 2) {
            System.out.println("Regular generation number works");
        } else {
            System.out
                    .println("PROBLEM: Regular generation number doesn't work");
        }
        guppy1.setHealthCoefficient(Guppy.MINIMUM_HEALTH_COEFFICIENT - 1);
        if (guppy1.getHealthCoefficient() == Guppy.DEFAULT_HEALTH_COEFFICIENT) {
            System.out.println("Low health coefficient works");
        } else {
            System.out.println("PROBLEM: Low health coefficient doesn't work");
        }
        guppy1.setHealthCoefficient(Guppy.MINIMUM_HEALTH_COEFFICIENT);
        if (guppy1.getHealthCoefficient() == Guppy.MINIMUM_HEALTH_COEFFICIENT) {
            System.out.println("Regular health coefficient works");
        } else {
            System.out.println(
                    "PROBLEM: Regular health coefficient doesn't work");
        }
        guppy1.setHealthCoefficient(Guppy.MAXIMUM_HEALTH_COEFFICIENT + 1);
        if (guppy1.getHealthCoefficient() == Guppy.DEFAULT_HEALTH_COEFFICIENT) {
            System.out.println("High health coefficient works");
        } else {
            System.out.println("PROBLEM: High health coefficient doesn't work");
        }
        guppy1.setIsAlive(false);
        if (!guppy1.getIsAlive()) {
            System.out.println("Guppy dead, RIP Guppy");
        } else {
            System.out.println("PROBLEM: Guppy confirmed immortal");
        }

    }

}
