package com.noah.dianping.request;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/12 16:53
 * @ClassName dianping
 * @Description 分页
 **/
public class PageQuery {

    private Integer page=1;

    private Integer size=20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
