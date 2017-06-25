package ca.bcit.comp1510.guppies;

import java.util.Scanner;

/**
 * The scientific binomial name for an animal.
 * 
 * @author griffin
 * @version 1.0
 */
public class BinomialName {
    private String genus;

    private String species;

    public BinomialName() {
        genus = "";
        species = "";
    }

    public BinomialName(String genus, String species) {
        this.genus = "";
        setGenus(genus);
        this.species = "";
        setSpecies(species);
    }

    public BinomialName(String binomialName) {
        if (!binomialName.contains(" ")) {
            throw new IllegalArgumentException(
                    "binomial name must contain space");
        }
        Scanner scan = new Scanner(binomialName);
        genus = "";
        setGenus(scan.next());
        species = "";
        setSpecies(scan.next());
        scan.close();
    }

    /**
     * Returns the genus of the animal.
     * 
     * @return the genus of the animal
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Sets the genus of the animal.
     * 
     * @param newGenus
     *            the new genus for the animal
     */
    public void setGenus(String newGenus) {
        if (newGenus != null && !newGenus.equals("")) {
            String firstLetter = newGenus.substring(0, 1);
            String restOfGenus = newGenus.substring(1, newGenus.length());

            firstLetter = firstLetter.toUpperCase();
            restOfGenus = restOfGenus.toLowerCase();

            genus = firstLetter + restOfGenus;
        }
    }

    /**
     * Returns the species of the animal.
     * 
     * @return the species of the animal
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of the animal.
     * 
     * @param newSpecies
     *            the new species for the animal
     */
    public void setSpecies(String newSpecies) {
        if (newSpecies != null && !newSpecies.trim().equals("")) {
            species = newSpecies.toLowerCase();
        }
    }
}
