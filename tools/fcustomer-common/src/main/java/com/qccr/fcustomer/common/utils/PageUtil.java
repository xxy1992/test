package com.qccr.fcustomer.common.utils;

/**
 * 分页工具
 * Created by yuntian on 2017/4/6.
 */
public final class PageUtil {

    private PageUtil() {
    }

    public static Integer getStart(Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            return null;
        }
        return (page - 1) * pageSize;
    }
}
