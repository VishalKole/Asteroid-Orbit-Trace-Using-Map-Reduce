//******************************************************************************
//
// File:    AstOrbitDistanceVBL.java
//
// This Java source file is part of the parallel programming project.
//
//******************************************************************************

import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.pj2.Vbl;

import java.io.IOException;

/**
 * variable reduction class to reduce the orbit distance reduction. it will just replace the orbit data if the
 * key is already present with the new one considering the new data found will be the latest orbit data
 * <p>
 *
 *
 * This class should be used with the AstOrbit.java class and will be called from the class itself
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 22-September-2017
 */
public class AstOrbitDistanceVBL extends Tuple implements Vbl {

    double val1, val2;

    /**
     * constructor
     */
    AstOrbitDistanceVBL() {
        val1 = 0;
        val2 = 0;
    }

    /**
     * constructor with arguments
     * @param val1 aphelion distance
     * @param val2 perihelion distance
     */
    AstOrbitDistanceVBL(double val1, double val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    /**
     * clones and sends a clone of the object
     * @return clonned object
     */
    public Object clone() {
        try {
            AstOrbitDistanceVBL vbl = (AstOrbitDistanceVBL) super.clone();
            vbl.val1 = this.val1;
            vbl.val2 = this.val2;
            return vbl;
        } catch (Exception ex) {
            throw new RuntimeException("AstOrbitDistanceVBL clone error");
        }

    }

    /**
     * write's the content of the object in stream
     * @param outStream output stream to write to
     * @throws IOException if the stream cannot be used
     */
    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeDouble(val1);
        outStream.writeDouble(val2);
    }

    /**
     * reads the content of the object from the stream
     * @param inStream input stream to read from
     * @throws IOException if the stream cannot be used to read
     */
    @Override
    public void readIn(InStream inStream) throws IOException {
        this.val1 = inStream.readDouble();
        this.val2 = inStream.readDouble();
    }

    /**
     * sets another VBL value to this class
     * @param vbl another AstOrbit VBL
     */
    @Override
    public void set(Vbl vbl) {
        AstOrbitDistanceVBL convertedGraphRadiusVBL = (AstOrbitDistanceVBL) vbl;
        this.val1 = convertedGraphRadiusVBL.val1;
        this.val2 = convertedGraphRadiusVBL.val2;
    }

    /**
     * Reduces and sets another VBL object value to current object
     * @param vbl another AstOrbit VBL
     */
    @Override
    public void reduce(Vbl vbl) {
        AstOrbitDistanceVBL temp = (AstOrbitDistanceVBL) vbl;
        this.val1 = temp.val1;
        this.val2 = temp.val2;
    }
}
