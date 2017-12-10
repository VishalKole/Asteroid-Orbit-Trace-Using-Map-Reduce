//******************************************************************************
//
// File:    AstOrbitCustomizer.java
//
// This Java source file is part of the parallel programming project.
//
//******************************************************************************

import edu.rit.pjmr.Customizer;

/**
 * this file orders the asteroids in an ascending format.
 * <p>
 *
 *
 * This class should be used with the AstOrbit.java class and will be called from the class itself
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class AstOrbitCustomizer extends Customizer<Integer, AstOrbitDistanceVBL> {

    /**
     * comparator to arrange the combiner objects
     * @param key_1 key from combiner
     * @param value_1 value of the key
     * @param key_2 another key from combiner
     * @param value_2 value of the key
     * @return true if it key1 should come before key2, else false
     */
    public boolean comesBefore
    (Integer key_1, AstOrbitDistanceVBL value_1,
     Integer key_2, AstOrbitDistanceVBL value_2) {
        if (key_1 < key_2) {
            return true;
        } else
            return false;
    }
}