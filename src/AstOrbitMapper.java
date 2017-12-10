//******************************************************************************
//
// File:    AstOrbitMapper.java
//
// This Java source file is part of the parallel programming project.
//
//******************************************************************************

import edu.rit.pjmr.Combiner;
import edu.rit.pjmr.Mapper;
import edu.rit.pjmr.TextId;

/**
 * This file takes the input from the input file provided and extracts the orbit information and
 * maps it into a combiner to further reduce it.
 *
 * This class should be used with the AstOrbit.java class and will be called from the class itself
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class AstOrbitMapper extends Mapper<TextId, String, Integer, AstOrbitDistanceVBL> {
    double mindist, maxdist;

    /**
     * initiates the process by allocating the values
     * @param args arguments to the class file
     * @param combiner reference to the combiner used
     */
    public void start
    (String[] args,
     Combiner<Integer, AstOrbitDistanceVBL> combiner) {
        //assign the values of min and max distance to the class
        mindist = Double.parseDouble(args[0]);
        maxdist = Double.parseDouble(args[1]);
    }

    /**
     * maps the input into the combiner by extracting the data from the string line provided
     * @param textId the string number from the file provided to the AstOrbit
     * @param s the string line from the file provided to the AstOrbit
     * @param combiner reference to the combiner object used to reduce
     */
    @Override
    public void map(TextId textId, String s, Combiner<Integer, AstOrbitDistanceVBL> combiner) {
        String[] list = s.split("\\s+");
        double AD, PD;

        //get astroid ID
        int astID = Integer.parseInt(list[0].replaceAll("'", ""));

        //store values
        double a = Double.parseDouble(list[2]);
        double e = Double.parseDouble(list[3]);

        //compute the distances
        AD = a * (1 + e);
        PD = a * (1 - e);

        //check if it is in the danger zone provided, add if it is.
        if (AD >= mindist && PD <= maxdist) {
            combiner.add(astID, new AstOrbitDistanceVBL(PD, AD));
        }
    }
}
