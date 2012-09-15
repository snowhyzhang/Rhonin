package DBSCANAlgorithm.DBSCANProcessorImpl;

import ClusterModel.Cluster;
import ClusterModel.ClusterSet;
import DBSCANAlgorithm.DBSCANProcessor;
import DBSCANAlgorithm.DocumentsSimilarityProcessor;
import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.DocumentModel;
import DocumentModel.Documents;
import DocumentModel.DocumentPointType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-12
 * Time: 下午11:06
 * To change this template use File | Settings | File Templates.
 */
public class DBSCANProcessorImplBoundTuning implements DBSCANProcessor {
    @Override
    public ClusterSet getClusterSet(Documents documents, double eps, int minPts) {
        DocumentsSimilarityProcessor dsp = new CosineSimilarityProcessor();
        double[][] similarityMatrix = dsp.getDocumentSimilarity(documents);
        ClusterSet clusterSet = new ClusterSet();
        List<Integer> corePoint = new ArrayList<Integer>();
        List<Integer> boundPoint = new ArrayList<Integer>();

        int ndoc = 0;

        setDocumentPointType(documents, similarityMatrix, eps, minPts, corePoint, boundPoint);
        System.out.println(corePoint.size());
        System.out.println(boundPoint.size());
        for (int i = 0; i < corePoint.size(); ++i){
            int docNum = corePoint.get(i);
            DocumentModel documentCoreOrig = documents.getDocument(docNum);
            if (documentCoreOrig.isCluster()){
                continue;
            }
            String clusterName = "Cluster" + String.valueOf(clusterSet.getClusters().size());
            Cluster cluster = new Cluster(clusterName);
            Documents subDocuments = new Documents();
            List<Integer> subCoreList = new ArrayList<Integer>();
            Stack<Integer> processCoreList = new Stack<Integer>();
            documentCoreOrig.setProcessed(true);
            subDocuments.addDocuments(documentCoreOrig);
            subCoreList.add(docNum);
            for (int j = i + 1; j < corePoint.size(); ++j){
                int docProcess = corePoint.get(j);
                DocumentModel documentCoreProcess = documents.getDocument(docProcess);
                if (documentCoreProcess.isCluster()){
                    continue;
                }
                if (similarityMatrix[docNum][docProcess] >= eps) {
                    documentCoreProcess.setProcessed(true);
                    processCoreList.push(docProcess);
                    subCoreList.add(docProcess);
                }
            }
            processSubDocuments(subDocuments, documents, subCoreList, processCoreList, similarityMatrix, eps, corePoint);
            setBoundDocuments(subDocuments, documents, subCoreList, boundPoint);
            cluster.setDocuments(subDocuments);
            ndoc = ndoc + subDocuments.getDocumentSize();
            cluster.setDocumentsIsCluster();
            clusterSet.addCluster(cluster);
        }
        System.out.println(ndoc);

        return clusterSet;
    }

    private void setDocumentPointType(Documents documents, double[][] similarityMatrix, double eps, int minPts,
                                      List<Integer> corePoint, List<Integer> boundPoint) {
        for (int i = 0; i < documents.getDocumentSize(); ++i){
            int closedPoint = 0;
            for (int j = 0; j < documents.getDocumentSize(); ++j){
                if (similarityMatrix[i][j] >= eps) {
                    ++closedPoint;
                }
            }
            if (closedPoint >= minPts){
                DocumentModel document = documents.getDocument(i);
                document.setDocumentPointType(DocumentPointType.CORE);
                corePoint.add(i);
            }
        }
        for (int k = 0; k < corePoint.size(); ++k){
            for (int l = 0; l < documents.getDocumentSize(); ++l){
                if (corePoint.contains(l)){
                    continue;
                }
                int corePointNum = corePoint.get(k);
                if (similarityMatrix[corePointNum][l] >= eps){
                    DocumentModel document = documents.getDocument(l);
                    if (!boundPoint.contains(l)){
                        boundPoint.add(l);
                        document.setDocumentPointType(DocumentPointType.BOUND);
                    }
                    if (similarityMatrix[corePointNum][l] > document.getMaxSimilarity()){
                        document.setMaxSimilarity(similarityMatrix[corePointNum][l]);
                        document.setNearestPoint(corePointNum);
                    }
                }
            }
        }
    }

    private void processSubDocuments(Documents subDocuments, Documents documents, List<Integer> subCoreList,
                                     Stack<Integer> processCoreList, double[][] similarityMatrix, double eps,
                                     List<Integer> corePoint) {
        while (!processCoreList.isEmpty()){
            int docOrig = processCoreList.pop();
            DocumentModel documentOrig = documents.getDocument(docOrig);
            documentOrig.setProcessed(true);
            subDocuments.addDocuments(documentOrig);
            for (Integer docProcess: corePoint){
                DocumentModel documentProcess = documents.getDocument(docProcess);
                if (documentProcess.isCluster() || documentProcess.isProcessed()){
                    continue;
                }
                if (similarityMatrix[docOrig][docProcess] >= eps){
                    documentProcess.setProcessed(true);
                    subCoreList.add(docProcess);
                    processCoreList.push(docProcess);
                }
            }
            processSubDocuments(subDocuments, documents, subCoreList, processCoreList, similarityMatrix, eps, corePoint);
        }
    }

    private void setBoundDocuments(Documents subDocuments, Documents documents, List<Integer> subCoreList, List<Integer> boundPoint) {
        for (Integer i: boundPoint){
            DocumentModel boundDoc = documents.getDocument(i);
            if (subCoreList.contains(boundDoc.getNearestPoint()) && !boundDoc.isCluster()){
                subDocuments.addDocuments(boundDoc);
            }
        }
    }
}
