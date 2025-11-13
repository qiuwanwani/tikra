package com.asus.tikra.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求包装类
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final Long serialVersionUID = 1L;
}
