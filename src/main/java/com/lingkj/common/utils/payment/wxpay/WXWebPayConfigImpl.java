package com.lingkj.common.utils.payment.wxpay;

import com.lingkj.config.SystemParams;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXWebPayConfigImpl extends WXPayConfig {

    private byte[] certData;
    private static WXWebPayConfigImpl INSTANCE;

    private WXWebPayConfigImpl() throws Exception {
        String certPath = SystemParams.certPath;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }
    public static WXWebPayConfigImpl getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (WXWebPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXWebPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return WeChatConfig.APP_ID;
    }

    public String getMchID() {
        return  WeChatConfig.MCH_ID;
    }

    public String getKey() {
        return WeChatConfig.KEY;
    }

    public InputStream getCertStream(){
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
