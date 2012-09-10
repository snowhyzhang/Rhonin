package DBSCANAlgorithm.SimilarityProcessor;

import DBSCANAlgorithm.DocumentsSimilarityProcessor;
import DocumentModel.DocumentModel;
import DocumentModel.Documents;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午11:12
 * To change this template use File | Settings | File Templates.
 */
public class CosineSimilarityProcessor implements DocumentsSimilarityProcessor{
    @Override
    public double[][] getDocumentSimilarity(Documents documents) {
        double[][] documentSimilarity = new double[documents.getDocumentSize()][documents.getDocumentSize()];
        for (int i = 0; i < documents.getDocumentSize(); ++i){
            for (int j = i; j < documents.getDocumentSize(); ++j){
                if (j == i){
                    documentSimilarity[i][j] = 0;
                    continue;
                }
                documentSimilarity[i][j] = documentSimilarity[j][i] = getDocumentSimilarity(documents.getDocument(i),
                        documents.getDocument(j));
            }
        }
        return documentSimilarity;
    }


    /*
     * using cosine value to calculate the similarity between two documents.
     */
    private double getDocumentSimilarity(DocumentModel document1, DocumentModel document2) {
        double[] matrix1 = document1.getWordMatrix();
        double[] matrix2 = document2.getWordMatrix();
        double similarity = 0;
        if (matrix1 == null || matrix2 == null){
            return -1;
        }

        if (matrix1.length != matrix2.length){
            return -1;
        }

        double matrixProduct = 0;
        double matrixNorm1 = 0;
        double matrixNorm2 = 0;
        for (int i = 0; i < matrix1.length; ++i){
            matrixProduct += matrix1[i]*matrix2[i];
            matrixNorm1 += matrix1[i]*matrix1[i];
            matrixNorm2 += matrix2[i]*matrix2[i];
        }
        matrixNorm1 = Math.sqrt(matrixNorm1);
        matrixNorm2 = Math.sqrt(matrixNorm2);

        similarity = matrixProduct / (matrixNorm1 * matrixNorm2);

        return similarity;
    }
}
