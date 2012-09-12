package DBSCANAlgorithm;

import ClusterModel.Cluster;
import ClusterModel.ClusterSet;
import DBSCANAlgorithm.DBSCANProcessorImpl.DBSCANProcessorImplBasic;
import DBSCANAlgorithm.ParameterProcessorImpl.ParameterProcessorImplBasic;
import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.DocumentModel;
import DocumentModel.Documents;
import DocumentModel.DocumentPointType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-10
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
public class ClusterProcessor {
    public static void main(String[] args){
        String fileName = "src/main/resources/product.txt";
        String matrixInfo = "src/main/resources/matrix.txt";

        Documents documents = new Documents();
        documents.generateDocuments(fileName, matrixInfo);
        DBSCANProcessor dp = new DBSCANProcessorImplBasic();

        DocumentsSimilarityProcessor dsp = new CosineSimilarityProcessor();
        ParameterProcessor pp = new ParameterProcessorImplBasic();
        double eps = pp.getEps(documents, dsp, 1, 50);

        ClusterSet clusters = dp.getClusterSet(documents, eps, 1);
        List<Cluster> clusterList = clusters.getClusters();

        for (Cluster cluster: clusterList){
            System.out.println(cluster.getName());
            Documents subDocuments = cluster.getDocuments();
            for (int i = 0; i < subDocuments.getDocumentSize(); ++i){
                System.out.println(subDocuments.getDocument(i).getDocumentName());
            }
            System.out.println("+++++++++++++++++");
        }
        System.out.println("==============\noutlier:");
        int outlierCount = 0;
        for (int i = 0; i < documents.getDocumentSize(); ++i){
            DocumentModel doc = documents.getDocument(i);
            if (doc.getDocumentPointType() == DocumentPointType.NOISE){
                System.out.println(doc.getDocumentName());
                outlierCount++;
            }
        }
        System.out.println("***************\nTotal clusters: " + clusterList.size());
        for (Cluster cluster: clusterList){
            System.out.println(cluster.getName() + ": " + cluster.getDocuments().getDocumentSize());
        }
        System.out.println("Outlier: " + outlierCount);
    }
}
