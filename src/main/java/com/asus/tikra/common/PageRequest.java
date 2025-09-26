package com.asus.tikra.common;

import lombok.Data;

@Data
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式（默认降序）
     */
    private String sortOrder = "descend";
}
