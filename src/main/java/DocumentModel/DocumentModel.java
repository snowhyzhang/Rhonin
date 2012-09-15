package DocumentModel;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午10:23
 * To change this template use File | Settings | File Templates.
 */

public class DocumentModel {
    private double[] wordMatrix = null;
    private boolean isCluster = false;
    private boolean isProcessed = false;
    private String documentName = null;
    private DocumentPointType documentPointType = DocumentPointType.NOISE;
    private int nearestPoint = -1;
    private double maxSimilarity = -1;

    public DocumentModel(){
    }

    public DocumentModel(String documentName) {
        this.documentName = documentName;
    }

    public double[] getWordMatrix() {
        return wordMatrix;
    }

    public void setWordMatrix(double[] wordMatrix) {
        this.wordMatrix = wordMatrix;
    }

    public boolean isCluster() {
        return isCluster;
    }

    public void setCluster(boolean cluster) {
        isCluster = cluster;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public DocumentPointType getDocumentPointType() {
        return documentPointType;
    }

    public void setDocumentPointType(DocumentPointType documentPointType) {
        this.documentPointType = documentPointType;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public int getNearestPoint() {
        return nearestPoint;
    }

    public void setNearestPoint(int nearestPoint) {
        this.nearestPoint = nearestPoint;
    }

    public double getMaxSimilarity() {
        return maxSimilarity;
    }

    public void setMaxSimilarity(double maxSimilarity) {
        this.maxSimilarity = maxSimilarity;
    }
}
