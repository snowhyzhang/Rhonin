package DBSCANAlgorithm;

import ClusterModel.Cluster;
import ClusterModel.ClusterSet;
import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.DocumentModel;
import DocumentModel.Documents;
import DocumentModel.DocumentPointType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-8
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class DBSCANProcessorImpl implements DBSCANProcessor {
    @Override
    public ClusterSet getClusterSet(Documents documents, double eps, int minPts) {
        DocumentsSimilarityProcessor dsp = new CosineSimilarityProcessor();
        double[][] similarityMatrix = dsp.getDocumentSimilarity(documents);
        ClusterSet clusterSet = new ClusterSet();
        int clusterNameCount = 0;

        for (int i = 0; i < documents.getDocumentSize(); ++i){
            DocumentModel documentOrig = documents.getDocument(i);
            if (documentOrig.isCluster()){
                continue;
            }
            int closePoints = 0;
            Stack<Integer> closeDocumentPoints = new Stack<Integer>();
            for (int j = i + 1; j < documents.getDocumentSize(); j++) {
                DocumentModel documentProcess = documents.getDocument(j);
                if (documentProcess.isCluster() && documentProcess.getDocumentPointType() != DocumentPointType.BOUND){
                    continue;
                }
                if (similarityMatrix[i][j] >= eps){
                    ++closePoints;
                    if (documentProcess.getDocumentPointType() != DocumentPointType.BOUND)
                    {
                        closeDocumentPoints.push(j);
                    }
                }
            }
            if (closePoints >= minPts){
                String clusterName = "Cluster" + String.valueOf(clusterNameCount);
                Cluster cluster = new Cluster(clusterName);
                clusterNameCount++;
                Documents subDocuments = new Documents();

                documentOrig.setDocumentPointType(DocumentPointType.CORE);
                documentOrig.setProcessed(true);
                subDocuments.addDocuments(documentOrig);

                processSubDocumentPoint(closeDocumentPoints, subDocuments, eps, minPts, documents, similarityMatrix);

                cluster.setDocuments(subDocuments);
                cluster.setDocumentsIsCluster();
                clusterSet.addCluster(cluster);
            }
        }

        return clusterSet;
    }

    private void processSubDocumentPoint(Stack<Integer> closeDocumentPoints, Documents subDocuments, double eps,
                                         int minPts, Documents documents, double[][] similarityMatrix) {
        setProcessed(closeDocumentPoints, documents);
        for (int i = 0; i < closeDocumentPoints.size(); ++i){
            int orig = closeDocumentPoints.pop();
            DocumentModel documentOrig =  documents.getDocument(orig);
            subDocuments.addDocuments(documentOrig);

            int closePoints = 0;
            List<Integer> closePointList = new ArrayList<Integer>();
            for (int j = 0; j < documents.getDocumentSize(); j++) {
                DocumentModel documentProcess = documents.getDocument(j);
                if (documentProcess.isCluster()){
                    continue;
                }
                if (similarityMatrix[orig][j] >= eps){
                    ++closePoints;
                    if (!documentProcess.isProcessed()){
                        closePointList.add(j);
                    }
                }
            }
            if (closePoints >= minPts){
                documentOrig.setDocumentPointType(DocumentPointType.CORE);
                for (Integer p: closePointList){
                    closeDocumentPoints.push(p);
                }
                processSubDocumentPoint(closeDocumentPoints, subDocuments, eps, minPts, documents, similarityMatrix);
            } else {
                documentOrig.setDocumentPointType(DocumentPointType.BOUND);
            }
        }
    }

    private void setProcessed(Stack<Integer> closeDocumentPoints, Documents documents) {
        for (Integer i: closeDocumentPoints){
            DocumentModel document = documents.getDocument(i);
            document.setProcessed(true);
        }
    }
}
