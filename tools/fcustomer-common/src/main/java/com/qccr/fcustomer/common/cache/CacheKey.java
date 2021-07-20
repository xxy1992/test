package com.qccr.fcustomer.common.cache;

/**
 * 缓存key统一管理， 避免key 冲突
 */
public interface CacheKey {

    /**
     * 商户号计数器
     */
    String MCH_NO_COUNTER = "FCUSTOMER_MCH_NO_COUNTER";

    /**
     * 商户缓存KEY前缀
     */
    String MCH_CACHE_PREFIX = "FCUSTOMER_MCH_CACHE:";

    /**
     * 客户数据缓存KEY前缀
     */
    String CUSTOMER_DATA_CACHE_PREFIX = "FCUSTOMER_CUSTOMER_CACHE:";

    /**
     * 客户缓存KEY前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_ID = "FCUSTOMER_CUSTOMER_KEY_CACHE:ID_";

    /**
     * 客户KEY缓存前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_STORE = "FCUSTOMER_CUSTOMER_KEY_CACHE:STORE_";

    /**
     * 客户KEY缓存前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_USER = "FCUSTOMER_CUSTOMER_KEY_CACHE:USER_";

    /**
     * 客户KEY缓存前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_LICENCENO = "FCUSTOMER_CUSTOMER_KEY_CACHE:LICENCE_";

    /**
     * 客户KEY缓存前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_FANO = "FCUSTOMER_CUSTOMER_KEY_CACHE:FANO_";

    /**
     * 客户KEY缓存前缀
     */
    String CUSTOMER_CACHE_PREFIX_WITH_SHOPCODE = "FCUSTOMER_CUSTOMER_CACHE:SHOPCODE_";

}
