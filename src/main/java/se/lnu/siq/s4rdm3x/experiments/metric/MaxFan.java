package se.lnu.siq.s4rdm3x.experiments.metric;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import se.lnu.siq.s4rdm3x.cmd.HuGMe;

public class MaxFan extends Metric {

    public String getName() {
        return "maxfan";
    }

    public void assignMetric(Iterable<Node> a_nodes) {
        FanHelper fh = new FanHelper(a_nodes);

        for(Node n : a_nodes) {
            setMetric(n, Math.max(fh.getFanIn(n),fh.getFanOut(n)));
        }
    }

    public void reassignMetric(Iterable<Node> a_nodes) {
        // the fan in will not change so...
    }
}
