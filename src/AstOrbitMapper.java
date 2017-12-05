import edu.rit.pjmr.Combiner;
import edu.rit.pjmr.Mapper;
import edu.rit.pjmr.TextId;
public class AstOrbitMapper extends Mapper<TextId,String,Integer, AstOrbitDistanceVBL> {
    private static final AstOrbitDistanceVBL distance = new AstOrbitDistanceVBL();
    double mindist,maxdist;

    public void start
            (String[] args,
             Combiner<Integer, AstOrbitDistanceVBL> combiner)
    {
        mindist = Double.parseDouble (args[0]);
        maxdist = Double.parseDouble (args[1]);
    }

    @Override
    public void map(TextId textId, String s, Combiner<Integer, AstOrbitDistanceVBL> combiner) {
        String[] list = s.split("\\s+");

        int astID = Integer.parseInt(list[0].replaceAll("'",""));
        double a = Double.parseDouble (list[2]);
        double e = Double.parseDouble (list[3]);

        double AD,PD;
        AD = a * (1 + e);
        PD = a * (1 - e);

        if(AD>=mindist && PD<=maxdist){
            distance.val1=PD;
            distance.val2=AD;
            //combiner.add (astID, distance);
            combiner.add (astID, new AstOrbitDistanceVBL(PD,AD));
        }

    }
}
