package DocumentModel;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
public class DocumentsTest extends TestCase {
    public void testGetDocument(){
        DocumentModel docApple = new DocumentModel("apple.txt");
        DocumentModel docBag = new DocumentModel("bag.txt");

        Documents documents = new Documents();

        documents.addDocuments(docApple);
        documents.addDocuments(docBag);

        DocumentModel testDoc1 = documents.getDocument(0);

        DocumentModel testDoc2 = documents.getDocument("bag.txt");
        int size = documents.getDocumentSize();
        DocumentModel testDoc3 = documents.getDocument(size);

        assertEquals("apple.txt", testDoc1.getDocumentName());
        assertEquals("bag.txt", testDoc2.getDocumentName());
        assertEquals(2, size);
        assertEquals(null, testDoc3);
    }

    public void testGenerateDocuments(){
        String fileName = "src/main/resources/dataName.txt";
        String matrixInfo = "src/main/resources/data.txt";

        Documents documents = new Documents();
        documents.generateDocuments(fileName, matrixInfo);
        double[] matrix0 = documents.getDocument(0).getWordMatrix();

        assertEquals ("玉兰油玉兰凝萃护肤沐浴露玫瑰营养滋润型1升" ,documents.getDocument(0).getDocumentName());
        assertEquals ((double)0, matrix0[0]);
        assertEquals (0.042875083400775824, matrix0[2]);

    }
}
