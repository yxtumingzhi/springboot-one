package com.hope.one.aop;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-18 17:11
 */
public class PageInfo implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private int rowCount = 0;
    private int currentPage = 1;
    private int pageSize = 20;
    private int pageCount = 0;
    private int totalCount = 0;
    private String orderBy;

    public PageInfo() {
    }

    public int calculatePageCount() {
        this.pageCount = this.totalCount / this.pageSize + (this.totalCount % this.pageSize == 0 ? 0 : 1);
        if (this.currentPage < this.pageCount) {
            this.rowCount = this.pageSize;
        } else if (this.currentPage == this.pageCount) {
            this.rowCount = this.totalCount - (this.pageCount - 1) * this.pageSize;
        }

        return this.pageCount;
    }

    @JSONField(
            serialize = false
    )
    public boolean isOutofBounds() {
        return this.currentPage > this.pageCount || this.currentPage < 1;
    }

    @JSONField(
            serialize = false
    )
    public int getLimitStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
