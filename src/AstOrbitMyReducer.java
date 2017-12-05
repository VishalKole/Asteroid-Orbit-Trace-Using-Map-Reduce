import edu.rit.pj2.vbl.LongVbl;
import edu.rit.pjmr.Reducer;

public class AstOrbitMyReducer extends Reducer<Integer, AstOrbitDistanceVBL>
{

    private LongVbl total;

    public void start
            (String[] args)
    {
        total = new LongVbl.Sum();
        System.out.flush();
    }
    public void reduce
            (Integer key,
             AstOrbitDistanceVBL value)
    {
        System.out.printf ("%d\t%.5g\t%.5g%n", key, value.val1, value.val2);
        System.out.flush();
        total.reduce(1);
    }

    public void finish()
    {
        System.out.println(total.item);
    }
}
