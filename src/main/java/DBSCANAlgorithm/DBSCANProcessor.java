package DBSCANAlgorithm;

import ClusterModel.ClusterSet;
import DocumentModel.Documents;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-8
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public interface DBSCANProcessor {
    /*
     * Using DBSACN algorithm to process the documents and get the cluster set.
     * input:
     * documents: document set
     * EPS: the minimal similarity
     * MinPts: the minimal number of points
     * output:
     * cluster set
     */
    public ClusterSet getClusterSet (Documents documents, double EPS, int MinPts);
}
