package DBSCANAlgorithm;

import DocumentModel.Documents;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-8
 * Time: 上午12:46
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentsSimilarityProcessor {
    public double[][] getDocumentSimilarity(Documents documents);
}
