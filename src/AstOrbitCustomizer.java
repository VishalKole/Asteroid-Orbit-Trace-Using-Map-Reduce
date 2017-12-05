import edu.rit.pjmr.Customizer;

public class AstOrbitCustomizer extends Customizer<Integer, AstOrbitDistanceVBL> {
    public boolean comesBefore
            (Integer key_1, AstOrbitDistanceVBL value_1,
             Integer key_2, AstOrbitDistanceVBL value_2) {
        if(key_1<key_2){
            return true;
        }
        else
            return false;
    }
}