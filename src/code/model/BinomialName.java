package code.model;

import java.util.Scanner;

/**
 * The scientific binomial name for an animal.
 * 
 * @author griffin
 * @version 1.0
 */
public class BinomialName {

    /**
     * The genus of the animal.
     */
    private String genus;

    /**
     * The species of the animal.
     */
    private String species;

    /**
     * Default constructor; genus and species are empty Strings.
     */
    public BinomialName() {
        genus = "";
        species = "";
    }

    /**
     * Constructor; if genus or species is invalid, sets it to an empty String.
     * 
     * @param genus
     *            the genus of the animal
     * @param species
     *            the species of the animal
     */
    public BinomialName(String genus, String species) {
        this.genus = "";
        setGenus(genus);
        this.species = "";
        setSpecies(species);
    }

    /**
     * Constructor; takes the first two words of the parameter and sets them to
     * genus and species, respectively.
     * 
     * @param binomialName
     *            the binomial name in String form
     */
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
        if (newGenus != null && !newGenus.trim().equals("")) {
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

    /**
     * Returns the genus and species, separated by a space.
     */
    @Override
    public String toString() {
        return genus + " " + species;
    }
}
