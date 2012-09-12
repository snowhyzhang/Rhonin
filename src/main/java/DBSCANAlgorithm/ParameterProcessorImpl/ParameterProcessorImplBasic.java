package DBSCANAlgorithm.ParameterProcessorImpl;

import DBSCANAlgorithm.DocumentsSimilarityProcessor;
import DBSCANAlgorithm.Parameter;
import DBSCANAlgorithm.ParameterProcessor;
import DBSCANAlgorithm.SimilarityProcessor.CosineSimilarityProcessor;
import DocumentModel.Documents;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-10
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class ParameterProcessorImplBasic implements ParameterProcessor{
    @Override
    public void getEpsStatus(Documents documents, DocumentsSimilarityProcessor dsp, int k, double percent) {
        double[][] matrix= dsp.getDocumentSimilarity(documents);
        double[] kDis = null;
        double[] sortEnd = new double[matrix.length];
        for (int i = 0; i < matrix.length; ++i){
            kDis = matrix[i].clone();
            Arrays.sort(kDis);
            sortEnd[i] = kDis[matrix.length - 1 - k];
        }
        Arrays.sort(sortEnd);
        int count = 0;
        for (int i = matrix.length - 1; i < matrix.length && i >= 0; i = i - (int)(matrix.length * (percent / 100))){
            String per = String.valueOf(count * percent);
            if (per.length() > 4)
                per = per.substring(0, 4);
            System.out.println(i + "\t" +per + "％:\t" + sortEnd[i]);
            count++;
        }
    }

    @Override
    public double getEps(Documents documents, DocumentsSimilarityProcessor dsp, int k, double percent) {
        double eps = 0;
        double[][] matrix= dsp.getDocumentSimilarity(documents);
        double[] kDis = null;
        double[] sortEnd = new double[matrix.length];
        for (int i = 0; i < matrix.length; ++i){
            kDis = matrix[i].clone();
            Arrays.sort(kDis);
            sortEnd[i] = kDis[matrix.length - 1 - k];
        }
        Arrays.sort(sortEnd);
        int index = matrix.length - 1 - (int)(matrix.length * (percent / 100));
        eps = sortEnd[index];
        return eps;
    }
}
