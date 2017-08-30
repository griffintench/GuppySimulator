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
        this.genus = processGenus(genus, "");
        this.species = processSpecies(species, "");
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
        this.genus = processGenus(genus, defaultGenus);
        this.species = processSpecies(species, defaultSpecies);
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
        genus = processGenus(scan.next(), "");
        species = processSpecies(scan.next(), "");
        scan.close();
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
        genus = processGenus(scan.next(), defaultGenus);
        species = processSpecies(scan.next(), "");
        scan.close();
    }

    private String processGenus(String inputGenus, String defaultGenus) {
        if (inputGenus == null || inputGenus.trim().equals("")) {
            return defaultGenus;
        }
        String firstLetter = inputGenus.substring(0, 1);
        String restOfGenus = inputGenus.substring(1, inputGenus.length());

        firstLetter = firstLetter.toUpperCase();
        restOfGenus = restOfGenus.toLowerCase();

        return firstLetter + restOfGenus;
    }

    private String processSpecies(String inputSpecies, String defaultSpecies) {
        if (inputSpecies == null || inputSpecies.trim().equals("")) {
            return defaultSpecies;
        }
        return inputSpecies.toLowerCase();
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
