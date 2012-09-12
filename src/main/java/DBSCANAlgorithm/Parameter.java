package DBSCANAlgorithm;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-10
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */

public class Parameter {
    private double eps = 0.0;
    private int minPts = 0;

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public int getMinPts() {
        return minPts;
    }

    public void setMinPts(int minPts) {
        this.minPts = minPts;
    }
}
