package org.dromara.sms4j.core;

import cn.hutool.core.bean.BeanUtil;
import com.dtflys.forest.config.ForestConfiguration;
import org.dromara.sms4j.api.Sms;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.callback.CallBack;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.comm.annotation.Restricted;
import org.dromara.sms4j.comm.config.BaseConfig;
import org.dromara.sms4j.comm.delayedTime.DelayedTime;
import org.dromara.sms4j.comm.enumerate.SupplierType;
import org.dromara.sms4j.comm.factory.BeanFactory;
import org.dromara.sms4j.core.factory.SmsFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * @author <a href = "mailto:kamtohung@gmail.com">KamTo Hung</a>
 */
public abstract class AbstractSmsAdapter<C extends BaseConfig> implements SmsBlend, Sms<C> {

    /**
     * config
     */
    protected final C config;

    /**
     * thread pool executor
     */
    protected final Executor pool;

    /**
     * delayed time
     */
    protected final DelayedTime delayed;

    /**
     * http config
     */
    protected final ForestConfiguration http;

    public AbstractSmsAdapter(C config) {
        this.config = config;
        this.pool = BeanFactory.getExecutor();
        this.delayed = BeanFactory.getDelayedTime();
        this.http = BeanFactory.getForestConfiguration();
        init(config);
    }

    @Override
    @Restricted
    public void sendMessageAsync(String phone, String message, CallBack callBack) {
        pool.execute(() -> {
            SmsResponse smsResponse = sendMessage(phone, message);
            callBack.callBack(smsResponse);
        });
    }

    @Override
    @Restricted
    public void sendMessageAsync(String phone, String message) {
        pool.execute(() -> sendMessage(phone, message));
    }

    @Override
    @Restricted
    public void sendMessageAsync(String phone, String templateId, LinkedHashMap<String, String> messages, CallBack callBack) {
        pool.execute(() -> {
            SmsResponse smsResponse = sendMessage(phone, templateId, messages);
            callBack.callBack(smsResponse);
        });
    }

    @Override
    @Restricted
    public void sendMessageAsync(String phone, String templateId, LinkedHashMap<String, String> messages) {
        pool.execute(() -> sendMessage(phone, templateId, messages));
    }

    @Override
    @Restricted
    public void delayedMessage(String phone, String message, Long delayedTime) {
        this.delayed.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMessage(phone, message);
            }
        }, delayedTime);
    }

    @Override
    @Restricted
    public void delayedMessage(String phone, String templateId, LinkedHashMap<String, String> messages, Long delayedTime) {
        this.delayed.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMessage(phone, templateId, messages);
            }
        }, delayedTime);
    }

    @Override
    @Restricted
    public void delayMassTexting(List<String> phones, String message, Long delayedTime) {
        this.delayed.schedule(new TimerTask() {
            @Override
            public void run() {
                massTexting(phones, message);
            }
        }, delayedTime);
    }

    @Override
    @Restricted
    public void delayMassTexting(List<String> phones, String templateId, LinkedHashMap<String, String> messages, Long delayedTime) {
        this.delayed.schedule(new TimerTask() {
            @Override
            public void run() {
                massTexting(phones, templateId, messages);
            }
        }, delayedTime);
    }

    @Override
    public void init(C config) {
        SmsFactory.register(type(), this);
    }

    @Override
    public void refresh(C config) {
        // need to print what has been changed?
        BeanUtil.copyProperties(config, this.config);
    }

    protected abstract SupplierType type();

}
