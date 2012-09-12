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
    public void getEpsStatus(Documents documents, DocumentsSimilarityProcessor dsp, int k, double percent);

    public double getEps(Documents documents, DocumentsSimilarityProcessor dsp,int k, double percent);
}
