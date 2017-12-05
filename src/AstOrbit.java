import edu.rit.pjmr.PjmrJob;
import edu.rit.pjmr.TextFileSource;
import edu.rit.pjmr.TextId;

public class AstOrbit extends PjmrJob<TextId, String, Integer, AstOrbitDistanceVBL> {

    public void main(String[] args) {

        String file = args[1];
        int NT = Math.max(threads(), 1);

        String[] nodes = args[0].split(",");
        for (String node : nodes)
            mapperTask(node)
                    .source(new TextFileSource(file))
                    .mapper(NT, AstOrbitMapper.class, args[2], args[3]);

        reducerTask()
                .runInJobProcess()
                .customizer (AstOrbitCustomizer.class)
                .reducer(AstOrbitMyReducer.class);

        startJob();
    }

}
