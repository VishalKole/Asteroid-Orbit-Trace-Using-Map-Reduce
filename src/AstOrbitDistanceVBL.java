import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.pj2.Vbl;
import java.io.IOException;


public class AstOrbitDistanceVBL extends Tuple implements Vbl {

    double val1,val2;

    AstOrbitDistanceVBL(){
        val1 =0;
        val2 =0;
    }

    AstOrbitDistanceVBL(double val1, double val2){
        this.val1 =val1;
        this.val2 =val2;
    }

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

        @Override
    public void writeOut(OutStream outStream) throws IOException {
            outStream.writeDouble(val1);
            outStream.writeDouble(val2);
    }

    @Override
    public void readIn(InStream inStream) throws IOException {
        this.val1 = inStream.readDouble();
        this.val2 = inStream.readDouble();
    }

    @Override
    public void set(Vbl vbl) {
        AstOrbitDistanceVBL convertedGraphRadiusVBL = (AstOrbitDistanceVBL) vbl;
        this.val1 = convertedGraphRadiusVBL.val1;
        this.val2 = convertedGraphRadiusVBL.val2;
    }

    @Override
    public void reduce(Vbl vbl) {
        AstOrbitDistanceVBL temp = (AstOrbitDistanceVBL) vbl;
        if(this.val1 < temp.val1)this.val1 = temp.val1;
        if(this.val2 < temp.val2)this.val2 = temp.val2;
    }
}
