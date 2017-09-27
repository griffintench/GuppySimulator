package code.model;

import java.util.Arrays;

/**
 * Holds some static methods for computing statistics.
 * 
 * @author griffin
 * @version 1.0
 */
public class Statistics {

    /**
     * Computes the mean of an int array.
     * 
     * @param array
     *            an int array
     * @return the mean of the array (a double)
     */
    public static double arrayMean(int[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return (0.0 + sum) / array.length;
    }

    /**
     * Computes the mean of a double array.
     * 
     * @param array
     *            a double array
     * @return the mean of the array (a double)
     */
    public static double arrayMean(double[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        
        double sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sum / array.length;
    }

    /**
     * Computes the median of an int array.
     * 
     * @param array an int array
     * @return the median of the array (a double)
     */
    public static double arrayMedian(int[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        
        double medianAge;
        double median1;
        double median2;
        int index1;
        int index2;

        Arrays.sort(array);

        if (array.length % 2 == 0) {
            index1 = array.length / 2;
            index2 = index1 - 1;

            median1 = array[index1];
            median2 = array[index2];

            medianAge = (median1 + median2) / 2;
        } else {
            index1 = array.length / 2;

            medianAge = array[index1];
        }

        return medianAge;
    }
}
