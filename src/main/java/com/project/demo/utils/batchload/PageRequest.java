package com.project.demo.utils.batchload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tools.ant.types.resources.Sort;

import java.io.Serializable;

/**
 * create by zhenhang.ning  2018-07-01 上午12:33
 */
public class PageRequest {
    private Page page = new Page(15, 1, null);

    public PageRequest() {
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @JsonIgnore
    public int getOffset() {
        if (page == null) {
            return 0;
        }
        int tmp = (page.pageNo - 1) * page.pageSize;
        return tmp < 0 ? 0 : tmp;
    }

    @JsonIgnore
    public int getLimit() {
        if (page == null) {
            return 0;
        }
        return page.pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageRequest)) return false;

        PageRequest that = (PageRequest) o;

        return page != null ? page.equals(that.page) : that.page == null;
    }

    @Override
    public int hashCode() {
        return page != null ? page.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "page=" + page +
                '}';
    }

    public static class Page implements Serializable {

        private static final long serialVersionUID = -9116229816861557536L;

        int pageSize = 15;

        int pageNo = 1;

        Sort sort;

        public Page() {
        }

        public Page(int pageSize, int pageNo) {
            this(pageSize, pageNo, null);
        }

        public Page(int pageSize, int pageNo, Sort sort) {
            this.pageSize = pageSize;
            this.pageNo = pageNo;
            this.sort = sort;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        @JsonIgnore
        public Limit getLimit() {
            return Limit.createByPage(pageNo, pageSize);
        }

        @JsonIgnore
        public void setLimit(Limit limit) {
            this.pageNo = limit.getPage();
            this.pageSize = limit.getSize();
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public Sort getSort() {
            return sort;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Page)) return false;

            Page page = (Page) o;

            if (pageSize != page.pageSize) return false;
            if (pageNo != page.pageNo) return false;
            return sort != null ? sort.equals(page.sort) : page.sort == null;
        }

        @Override
        public int hashCode() {
            int result = pageSize;
            result = 31 * result + pageNo;
            result = 31 * result + (sort != null ? sort.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Page{" +
                    "pageSize=" + pageSize +
                    ", pageNo=" + pageNo +
                    ", sort=" + sort +
                    '}';
        }
    }
}
