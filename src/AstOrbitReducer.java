//******************************************************************************
//
// File:    AstOrbitReducer.java
//
// This Java source file is part of the parallel programming project.
//
//******************************************************************************

import edu.rit.pjmr.Reducer;

/**
 * This file reduces the asteroid orbit data in the container and prints the data to the console as well
 * <p>
 *
 *
 * This class should be used with the AstOrbit.java class and will be called from the class itself
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class AstOrbitReducer extends Reducer<Integer, AstOrbitDistanceVBL> {
    // to keep the total count
    private int total;

    /** initialization
     * @param args argments to the file
     */
    public void start
    (String[] args) {
    }

    /** prints the data in the container to the console and counts the items available
     * @param key key in the container
     * @param value value from the container for the key
     */
    public void reduce
    (Integer key,
     AstOrbitDistanceVBL value) {
        System.out.printf("%d\t%.5g\t%.5g%n", key, value.val1, value.val2);
        System.out.flush();
        ++total;
    }

    /**
     * runs at the end of the class reduction
     */
    public void finish() {
        System.out.println(total);
    }
}
