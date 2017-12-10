//******************************************************************************
//
// File:    AstOrbit.java
//
// This Java source file is part of the parallel programming project.
//
//******************************************************************************

import edu.rit.pjmr.PjmrJob;
import edu.rit.pjmr.TextFileSource;
import edu.rit.pjmr.TextId;

/**
 * This Java program computes the asteroids which are between a range from the center of the earth with map reduce.
 * The AstDyS-2 web site maintains a dataset (text file) of asteroid orbit data. Each line of the file comprises orbit
 * data for one asteroid.
 * <P>
 * Usage: <TT>java pj2 [threads=<I>NT</I>] AstOrbit
 * <I>nodes</I> <I>file</I> <I>MinDistance</I>  <I>MaxDistance</I> </TT>
 * <P>
 * <I>MinDistance</I> minimum distance from the sun (greater than zero)
 * <I>MaxDistance</I> maximum distance from the sun (greater than minimum distance)
 *
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class AstOrbit extends PjmrJob<TextId, String, Integer, AstOrbitDistanceVBL> {

    /**
     * Starting point of the program
     *
     * @param args input arguments to the program
     *             comma-separated node names, the name of the file containing the orbit data, mindist, and maxdist.
     */
    public void main(String[] args) throws Exception {

        if (ValidateInputParameters(args)) {
            String file = args[1];
            int NT = Math.max(threads(), 1);
            String[] nodes = args[0].split(",");

            //run on all the nodes
            for (String node : nodes)
                mapperTask(node)
                        .source(new TextFileSource(file))
                        .mapper(NT, AstOrbitMapper.class, args[2], args[3]);

            reducerTask()
                    .runInJobProcess()
                    .customizer(AstOrbitCustomizer.class)
                    .reducer(AstOrbitReducer.class);

            startJob();
        }
    }

    /**
     * Validate all the input parameters and then proceed
     * @param args input string arguments
     * @return returns true if the inputs are valid
     * @throws Exception throws exception if the input arguments are not correct.
     */
    private boolean ValidateInputParameters(String[] args) throws Exception {

        //Arguments are four
        if (args.length != 4)
            throw new IllegalArgumentException("missing arguments. Usage:java pj2 jar=<jar> threads=<NT> " +
                    "AstOrbit <nodes> <file> <mindist> <maxdist>");

        //the mindistance and maxdistance are floating point
        try{
            Double.parseDouble(args[2]);
            Double.parseDouble(args[3]);
        }catch( Exception e){
            throw new IllegalArgumentException("<mindist> <maxdist> are not double precision floating point digits.");
        }

        //check for the range correctness
        if(!(Double.parseDouble(args[2]) >= 0 && Double.parseDouble(args[3])>=Double.parseDouble(args[2])))
            throw new IllegalArgumentException("0 <= mindist <= maxdist. Invalid input.");

        return true;
    }

}
