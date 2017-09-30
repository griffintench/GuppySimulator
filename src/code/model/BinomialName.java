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
    private final String genus;

    /**
     * The species of the animal.
     */
    private final String species;

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
        this.genus = NameProcessor.firstUpperRestLower(genus, "");
        this.species = NameProcessor.allLower(species, "");
    }

    /**
     * Constructor; if genus or species is invalid, sets it to the default.
     * 
     * @param genus
     *            the genus of the animal
     * @param species
     *            the species of the animal
     * @param defaultGenus
     *            the default genus
     * @param defaultSpecies
     *            the default species
     */
    public BinomialName(String genus, String species, String defaultGenus,
            String defaultSpecies) {
        this.genus = NameProcessor.firstUpperRestLower(genus, defaultGenus);
        this.species = NameProcessor.allLower(species, defaultSpecies);
    }

    /**
     * Constructor; takes the first two words of the parameter and sets them to
     * genus and species, respectively.
     * 
     * @param binomialName
     *            the binomial name in String form
     */
    public BinomialName(String binomialName) {
        this(binomialName, "", "");
    }

    /**
     * Constructor; takes the first two words of the name parameter and sets
     * them to genus and species, respectively. If either genus or species is
     * invalid, sets it to the default.
     * 
     * @param binomialName
     *            the binomial name in String form
     * @param defaultGenus
     *            the default genus
     * @param defaultSpecies
     *            the default species
     */
    public BinomialName(String binomialName, String defaultGenus,
            String defaultSpecies) {
        if (!binomialName.contains(" ")) {
            throw new IllegalArgumentException(
                    "binomial name must contain space");
        }
        Scanner scan = new Scanner(binomialName);
        genus = NameProcessor.firstUpperRestLower(scan.next(), defaultGenus);
        species = NameProcessor.allLower(scan.next(), defaultSpecies);
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
     * Returns the species of the animal.
     * 
     * @return the species of the animal
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Returns the genus and species, separated by a space.
     */
    @Override
    public String toString() {
        return genus + " " + species;
    }
}
