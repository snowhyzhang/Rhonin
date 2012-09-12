package DBSCANAlgorithm;

import DBSCANAlgorithm.ParameterProcessorImpl.ParameterProcessorImplBasic;
import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.Documents;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-10
 * Time: 下午10:00
 * To change this template use File | Settings | File Templates.
 */
public class ParameterAnalysis {
    public static void main(String[] args){
        ParameterProcessor pp = new ParameterProcessorImplBasic();
        String fileName = "src/main/resources/product.txt";
        String matrixInfo = "src/main/resources/matrix.txt";

        Documents documents = new Documents();
        documents.generateDocuments(fileName, matrixInfo);
        DocumentsSimilarityProcessor dsp = new CosineSimilarityProcessor();
        pp.getEpsStatus(documents, dsp, 3, 1.0);

    }
}
