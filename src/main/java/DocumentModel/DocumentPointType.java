package DocumentModel;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-9-7
 * Time: 下午11:19
 * To change this template use File | Settings | File Templates.
 */
public enum DocumentPointType {
    /*
     * if a document point has MinPts point documents in the EPS distance, it will be a core document point.
     */
    CORE,
    /*
     * if a document point has less MinPts point documents in its EPS distance, but it is closed to a CORE document point,
     * then it will be a BOUND document point.
     */
    BOUND,
    /*
     * if a document point is either a CORE nor a BOUND, it will be a NOISE document point.
     */
    NOISE
}
