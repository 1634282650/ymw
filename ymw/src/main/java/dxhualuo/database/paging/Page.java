package dxhualuo.database.paging;

import java.io.Serializable;

/**
 * @author dx_hualuo
 */
public class Page implements Serializable {
    private int pageIndex;
    private String dataJson;
    public Page(int pageIndex, String dataJson) {
        this.pageIndex = pageIndex;
    }

}
