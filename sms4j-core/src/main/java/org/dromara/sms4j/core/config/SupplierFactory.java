package org.dromara.sms4j.core.config;

import org.dromara.sms4j.emay.config.EmayConfig;
import org.dromara.sms4j.aliyun.config.AlibabaConfig;
import org.dromara.sms4j.cloopen.config.CloopenConfig;
import org.dromara.sms4j.huawei.config.HuaweiConfig;
import org.dromara.sms4j.jdcloud.config.JdCloudConfig;
import org.dromara.sms4j.tencent.config.TencentConfig;
import org.dromara.sms4j.unisms.config.UniConfig;
import org.dromara.sms4j.yunpian.config.YunpianConfig;

/**
 * SupplierFactory
 * <p> 差异化配置工厂
 * @author :Wind
 * 2023/4/8  15:02
 **/
public class SupplierFactory {
    private SupplierFactory() {
    }

    /** 阿里云差异化配置*/
    private static AlibabaConfig alibabaConfig;

    /** 华为云差异化配置*/
    private static HuaweiConfig huaweiConfig;

    /** 合一短信差异化配置*/
    private static UniConfig uniConfig;

    /** 腾讯云短信差异化配置*/
    private static TencentConfig tencentConfig;

    /** 云片短信差异配置*/
    private static YunpianConfig yunpianConfig;

    /** 京东云短信差异配置 */
    private static JdCloudConfig jdCloudConfig;

    /** 容联云短信差异配置 */
    private static CloopenConfig cloopenConfig;

    /**
     * 亿美软通短信差异配置
     */
    private static EmayConfig emayConfig;

    /** 阿里云配置获取*/
    public static AlibabaConfig getAlibabaConfig() {
        if (alibabaConfig == null){
            alibabaConfig = new AlibabaConfig();
        }
        return alibabaConfig;
    }

    /** 华为云配置获取*/
    public static HuaweiConfig getHuaweiConfig() {
        if (huaweiConfig == null){
            huaweiConfig = new HuaweiConfig();
        }
        return huaweiConfig;
    }

    /** 合一短信配置获取*/
    public static UniConfig getUniConfig() {
        if (uniConfig == null){
            uniConfig = new UniConfig();
        }
        return uniConfig;
    }

    /** 腾讯短信配置获取*/
    public static TencentConfig getTencentConfig() {
        if (tencentConfig == null){
            tencentConfig = new TencentConfig();
        }
        return tencentConfig;
    }

    /** 云片短信配置获取*/
    public static YunpianConfig getYunpianConfig() {
        if (yunpianConfig == null){
            yunpianConfig = new YunpianConfig();
        }
        return yunpianConfig;
    }

    /** 京东云短信配置获取 */
    public static JdCloudConfig getJdCloudConfig() {
        if (jdCloudConfig == null){
            jdCloudConfig = new JdCloudConfig();
        }
        return jdCloudConfig;
    }

    /** 容联云短信配置获取 */
    public static CloopenConfig getCloopenConfig() {
        if (cloopenConfig == null){
            cloopenConfig = new CloopenConfig();
        }
        return cloopenConfig;
    }

    /**
     * 亿美软通配置获取
     */
    public static EmayConfig getEmayConfig() {
        if (emayConfig == null) {
            emayConfig = new EmayConfig();
        }
        return emayConfig;
    }
}
