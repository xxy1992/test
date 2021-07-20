package com.qccr.fcustomer.common.cache;

import com.qccr.commons.datetime.Time;
import com.qccr.framework.insight.entry.Insight;
import com.qccr.framework.insight.plugin.onecache.OnecachePlugin;
import com.qccr.framework.insight.plugin.onecache.rcache.collection.RList;
import com.qccr.framework.insight.plugin.onecache.rcache.object.RBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * OneCahce redis
 *
 * @author yankaiqiang
 * @version $$Id: OneCacheTool.java, v 0.1 2018/6/7 10:16 yankaiqiang Exp $$
 */
public class OneCacheTool {

    private static Logger logger = LoggerFactory.getLogger(OneCacheTool.class);

    private static final OnecachePlugin ONECACHE = Insight.plugins().get(OnecachePlugin.class);

    private OneCacheTool() {

    }

    /**
     * 缓存bean对象
     *
     * @param key   缓存key
     * @param value 缓存对象
     * @param ttl   超时时间 可以为空
     * @param <T>   对象类型
     */
    public static <T> void setBean(String key, T value, Time ttl) {
        RBean<T> rBean = ONECACHE.getRBean(key);
        if (ttl != null) {
            rBean.set(value, ttl);
        } else {
            rBean.set(value);
        }
    }

    /**
     * 获取缓存对象
     *
     * @param key 缓存key
     * @param <T> 对象类型
     * @return 缓存对象
     */
    public static <T> T getBean(String key) {
        RBean<T> rBean = ONECACHE.getRBean(key);
        return rBean.get();
    }

    /**
     * 删除bean
     *
     * @param key 缓存key
     */
    public static void removeBean(String key) {
        RBean rBean = ONECACHE.getRBean(key);
        rBean.delete();
    }

    /**
     * 获取缓存list数据
     *
     * @param key 缓存key
     * @param <T> 数据类型
     * @return list
     */
    public static <T> List<T> getList(String key) {
        RList<T> rList = ONECACHE.getRList(key);
        return rList.getAll();
    }

    /**
     * 添加数据到list
     *
     * @param key  缓存key
     * @param data 数据
     * @param <T>  数据类型
     */
    public static <T> void addToList(String key, T data) {
        RList<T> rList = ONECACHE.getRList(key);
        rList.rpush(data);
    }
}
