package com.qccr.fcustomer.common.generator;

import com.qccr.fcustomer.common.cache.CacheKey;
import com.qccr.framework.insight.entry.Insight;
import com.qccr.framework.insight.plugin.onecache.OnecachePlugin;
import com.qccr.framework.insight.plugin.onecache.rcache.object.RLong;
import org.joda.time.DateTime;

/**
 * 商户号生成器
 *
 * @author yankaiqiang
 * @version $$Id: MchNoGenerator.java, v 0.1 2018/6/4 9:28 yankaiqiang Exp $$
 */
public class MchNoGenerator {

    private static final OnecachePlugin ONECACHE = Insight.plugins().get(OnecachePlugin.class);

    private static final Long COUNTER_INTINAL = 1000000L;

    public static String generate(String key) {
        return DateTime.now().toString("yymmMMHHdd") + key + getInc();
    }

    private static long getInc() {

        RLong rLong = ONECACHE.getRLong(CacheKey.MCH_NO_COUNTER, null);

        if (rLong.get() == null) {
            rLong.set(COUNTER_INTINAL);
        }

        return rLong.incr(1);
    }
}
