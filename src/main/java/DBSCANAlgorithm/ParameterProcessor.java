package DBSCANAlgorithm;

import DocumentModel.Documents;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-10
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public interface ParameterProcessor{
    /*
     * This function will show the distribution for the kth point similarity.
     */
    public void getEpsStatus(Documents documents, DocumentsSimilarityProcessor dsp, int k, double percent);
    /*
     * This function will return the appropriate eps for kth point document.
     */
    public double getEps(Documents documents, DocumentsSimilarityProcessor dsp,int k, double percent);
}
