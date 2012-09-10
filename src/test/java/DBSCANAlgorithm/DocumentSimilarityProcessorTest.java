package DBSCANAlgorithm;

import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.DocumentModel;
import DocumentModel.Documents;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-8
 * Time: 上午12:12
 * To change this template use File | Settings | File Templates.
 */
public class DocumentSimilarityProcessorTest extends TestCase{
    public void testGenerateSimilarity(){
        DocumentModel docApple = new DocumentModel("apple.txt");
        double[] appleWord = new double[]{0, 1, 2, 1, 0};
        docApple.setWordMatrix(appleWord);

        DocumentModel docBag = new DocumentModel("bag.txt");
        double[] bagWord = new double[]{1, 1, 0, 1, 0};
        docBag.setWordMatrix(bagWord);

        DocumentModel docCat = new DocumentModel("cat.txt");
        double[] catWord = new double[]{0, 1, 0, 1, 0};
        docCat.setWordMatrix(catWord);

        Documents documents = new Documents();
        documents.addDocuments(docApple);
        documents.addDocuments(docBag);
        documents.addDocuments(docCat);

        CosineSimilarityProcessor dsp = new CosineSimilarityProcessor();
        double[][] similarity = dsp.getDocumentSimilarity(documents);
        double result = 2 / (Math.sqrt((double)6) * Math.sqrt((double)2));

        assertEquals(similarity[0][1], similarity[1][0]);
        assertEquals(result, similarity[0][2]);
        assertEquals(result, similarity[2][0]);
        assertEquals ((double)0, similarity[1][1]);
    }
}
