package com.qccr.fcustomer.biz.service.customer;

import com.qccr.commons.datetime.Time;
import com.qccr.fcustomer.common.cache.CacheKey;
import com.qccr.fcustomer.common.cache.OneCacheTool;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerRo;
import org.apache.commons.lang3.StringUtils;

/**
 * 客户缓存管理
 *
 * @author yankaiqiang
 * @version $$Id: CustomerCacheManager.java, v 0.1 2018/8/21 15:15 yankaiqiang Exp $$
 */
public final class CustomerCacheManager {

    /**
     * 缓存客户信息
     * <p>
     * 第一层缓存 业务key - 数据缓存key
     * 第二层缓存 数据缓存key - 数据
     *
     * @param customerQueryRo 查询参数
     * @param customerRo      数据对象
     * @param <T>             数据对象类型
     */
    public static <T extends CustomerRo> void cache(CustomerQueryRo customerQueryRo, T customerRo) {

        if (customerQueryRo == null || customerRo == null) {
            return;
        }

        String customerQueryCacheKey = getCustomerQueryCacheKey(customerQueryRo);
        String cacheKey = CacheKey.CUSTOMER_DATA_CACHE_PREFIX + customerRo.getId();
        OneCacheTool.setBean(cacheKey, customerRo, Time.minutes(5));
        if (StringUtils.isNotBlank(customerQueryCacheKey)) {
            OneCacheTool.setBean(customerQueryCacheKey, cacheKey, Time.minutes(5));
        }
    }

    /**
     * 获取客户缓存
     *
     * @param customerQueryRo 查询参数
     * @param <T>             客户类型
     * @return 客户
     */
    public static <T extends CustomerRo> T get(CustomerQueryRo customerQueryRo) {

        T customerRo = null;

        String customerQueryCacheKey = getCustomerQueryCacheKey(customerQueryRo);
        if (StringUtils.isNotBlank(customerQueryCacheKey)) {
            String cacheKey = OneCacheTool.getBean(customerQueryCacheKey);
            if (StringUtils.isNotBlank(cacheKey)) {
                customerRo = OneCacheTool.getBean(cacheKey);
            }
        }

        return customerRo;
    }

    private static String getCustomerQueryCacheKey(CustomerQueryRo customerQueryRo) {

        if (customerQueryRo == null) {
            return null;
        }

        if (customerQueryRo.getCustomerId() != null) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_ID + customerQueryRo.getCustomerId();
        }

        if (StringUtils.isNotBlank(customerQueryRo.getBizStoreId())) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_STORE + customerQueryRo.getBizStoreId();
        }

        if (StringUtils.isNotBlank(customerQueryRo.getBizUserId())) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_USER + customerQueryRo.getBizUserId();
        }

        if (StringUtils.isNotBlank(customerQueryRo.getBusinessLicenceNo())) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_LICENCENO + customerQueryRo.getBusinessLicenceNo();
        }

        if (StringUtils.isNotBlank(customerQueryRo.getBizShopCode())) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_SHOPCODE + customerQueryRo.getBizShopCode();
        }

        if (StringUtils.isNotBlank(customerQueryRo.getAccountNo())) {
            return CacheKey.CUSTOMER_CACHE_PREFIX_WITH_FANO + customerQueryRo.getAccountNo();
        }

        return null;
    }

    /**
     * 刷新客户缓存
     *
     * @param customerId 客户ID
     */
    public static void refreshCache(Long customerId) {
        String cacheKey = CacheKey.CUSTOMER_DATA_CACHE_PREFIX + customerId;
        OneCacheTool.removeBean(cacheKey);
    }

}
