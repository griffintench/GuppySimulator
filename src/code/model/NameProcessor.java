package code.model;

/**
 * Provides static methods to handle naming.
 * 
 * @author griffin
 * @version 1.0
 */
public class NameProcessor {

    /**
     * A default to give in the case of null objects.
     */
    public static final String DEFAULT_NAME = "Unnamed";

    /**
     * Returns the name, but with the first letter uppercase and the rest
     * lowercase.
     * 
     * @param name
     *            a String
     * @return the name, but with the first letter uppercase and the rest
     *         lowercase
     */
    public static String firstUpperRestLower(String name) {
        return firstUpperRestLower(name, DEFAULT_NAME);
    }

    /**
     * Returns the name, but with the first letter uppercase and the rest
     * lowercase.
     * 
     * @param name
     *            a String
     * @param defaultName
     *            the default name, in the case of a null name
     * @return the name, but with the first letter uppercase and the rest
     *         lowercase
     */
    public static String firstUpperRestLower(String name, String defaultName) {
        if (name == null) {
            return defaultName;
        }
        name = name.replaceAll("\\s", "");
        if (name.equals("")) {
            return defaultName;
        }
        String firstLetter = name.substring(0, 1);
        String restOfName = name.substring(1, name.length());
        firstLetter = firstLetter.toUpperCase();
        restOfName = restOfName.toLowerCase();

        return firstLetter + restOfName;
    }

    /**
     * Returns the name in lowercase.
     * 
     * @param name
     *            a String
     * @return the name in lowercase
     */
    public static String allLower(String name) {
        return allLower(name, DEFAULT_NAME);
    }

    /**
     * Returns the name in lowercase.
     * 
     * @param name
     *            a String
     * @param defaultName
     *            the default name, in the case of a null name
     * @return the name in lowercase
     */
    public static String allLower(String name, String defaultName) {
        if (name == null) {
            return defaultName;
        }
        name = name.replaceAll("\\s", "");
        if (name.equals("")) {
            return defaultName;
        }
        return name.toLowerCase();
    }
}
