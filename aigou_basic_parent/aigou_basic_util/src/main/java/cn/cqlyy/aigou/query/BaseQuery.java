package cn.cqlyy.aigou.query;

/**
 * @Author: cqlyy
 * @Date: 2019/06/23 11:23
 * @Version 1.0
 */
public class BaseQuery {
    private Integer page=1;
    private Integer rows=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
