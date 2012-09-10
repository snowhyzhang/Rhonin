package ClusterModel;

import DocumentModel.DocumentModel;
import DocumentModel.Documents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午10:26
 * To change this template use File | Settings | File Templates.
 */

public class Cluster {
    private String name = null;
    private Documents documents = new Documents();

    public Cluster() {
    }

    public Cluster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }

    public void setDocumentsIsCluster(){
        for (int i = 0; i < documents.getDocumentSize(); ++i){
            documents.getDocument(i).setCluster(true);
        }
    }
}
