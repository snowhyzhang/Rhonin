package ClusterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */
public class ClusterSet {
    private List<Cluster> clusters = new ArrayList<Cluster>();

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void addCluster(Cluster cluster) {
        clusters.add(cluster);
    }
}
