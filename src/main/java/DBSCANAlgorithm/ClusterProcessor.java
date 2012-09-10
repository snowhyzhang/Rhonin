package DBSCANAlgorithm;

import ClusterModel.Cluster;
import ClusterModel.ClusterSet;
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
        String fileName = "src/main/resources/dataName.txt";
        String matrixInfo = "src/main/resources/data.txt";

        Documents documents = new Documents();
        documents.generateDocuments(fileName, matrixInfo);
        DBSCANProcessor dp = new DBSCANProcessorImpl();

        ClusterSet clusters = dp.getClusterSet(documents, 0.65git remote add origin https://github.com/snowhyzhang/Rhonin.gi, 2);
        List<Cluster> clusterList = clusters.getClusters();

        System.out.println("Total clusters: " + clusterList.size() + "\n**************");
        for (Cluster cluster: clusterList){
            System.out.println(cluster.getName());
            Documents subDocuments = cluster.getDocuments();
            for (int i = 0; i < subDocuments.getDocumentSize(); ++i){
                System.out.println(subDocuments.getDocument(i).getDocumentName());
            }
            System.out.println("+++++++++++++++++");
        }
        System.out.println("==============\noutlier:");
        for (int i = 0; i < documents.getDocumentSize(); ++i){
            DocumentModel doc = documents.getDocument(i);
            if (doc.getDocumentPointType() == DocumentPointType.NOISE){
                System.out.println(doc.getDocumentName());
            }
        }
    }
}
