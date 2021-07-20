package com.qccr.fcustomer.common.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

/**
 * facade 接口业务参数日志打印
 *
 * @author yankaiqiang
 * @version $$Id: FacadeLogUtils.java, v 0.1 2018/6/1 10:16 yankaiqiang Exp $$
 */
public class FacadeLogUtils {

    private FacadeLogUtils() {
    }

    public static void info(Logger logger, Object params, String mark) {
        if (params != null) {
            logger.info(mark + "-{}, params,  {}", params.hashCode(), toJSONString(params));
        } else {
            logger.info(mark + ", params,  null");
        }
    }

    public static void error(Logger logger, Object params, String mark) {
        if (params != null) {
            logger.error(mark + "-{}, params,  {}", params.hashCode(), toJSONString(params));
        } else {
            logger.error(mark + ", params,  null");
        }
    }

    public static void info(Logger logger, Object params, Object res, String mark) {
        if (params != null) {
            logger.info(mark + "-{}, params, {}, res, {}", params.hashCode(), toJSONString(params), toJSONString(res));
        } else {
            logger.info(mark + ", params,  null, res, {}", toJSONString(res));
        }
    }

    public static void error(Logger logger, Object params, Object res, String mark) {
        if (params != null) {
            logger.error(mark + "-{}, params, {}, res, {}", params.hashCode(), toJSONString(params), toJSONString(res));
        } else {
            logger.error(mark + ", params,  null, res, {}", toJSONString(res));
        }
    }

    private static String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
