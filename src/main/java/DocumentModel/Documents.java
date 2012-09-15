package DocumentModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午10:33
 * To change this template use File | Settings | File Templates.
 */
public class Documents {
    private List<DocumentModel> documents = new ArrayList<DocumentModel>();

    public Documents() {
    }

    public void addDocuments(DocumentModel doc){
        documents.add(doc);
    }

    public int getDocumentSize(){
        return documents.size();
    }

    public DocumentModel getDocument(String documentName){
        for (DocumentModel doc: documents){
            if (doc.getDocumentName().equals(documentName)){
                return doc;
            }
        }
        return null;
    }

    public DocumentModel getDocument(int index){
        if (index < 0 || index >=documents.size()){
            return null;
        } else {
            return documents.get(index);
        }
    }

    public void generateDocuments(String fileName, String matrixInfo){
        try {
            File file = new File(fileName);
            BufferedReader brFileName = new BufferedReader(new InputStreamReader (new FileInputStream(file), "UTF-8"));
            File matrix = new File(matrixInfo);
            BufferedReader brMatrix = new BufferedReader(new InputStreamReader(new FileInputStream(matrix), "UTF-8"));
            String documentName = null;

            while ((documentName = brFileName.readLine()) != null){
                DocumentModel document = new DocumentModel(documentName);
                String[] matrixLine = brMatrix.readLine().split("\t");
                double[] documentMatrix = new double[matrixLine.length];
                for (int i = 0; i < matrixLine.length; ++i){
                    documentMatrix[i] = Double.parseDouble(matrixLine[i]);
                }
                document.setWordMatrix(documentMatrix);
                documents.add(document);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
