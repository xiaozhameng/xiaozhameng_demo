package com.xiaozhameng.springboot.controller;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.xiaozhameng.sdks.unionpayextend.AcpServiceExtend;
import com.xiaozhameng.sdks.unionpayextend.SDKConfigExtend;
import com.xiaozhameng.util.CommonUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 小蚱蜢的测试程序</p>
 *
 * 银联测试程序
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/6/16
 */
@RestController
@RequestMapping("com.xiaozhameng")
public class TestController {

    public static final Logger logger = LoggerFactory.getLogger(TestController.class);

    protected final String VERSION = "1.0.0";
    protected final String ENCODING = "UTF-8";
    protected final String DATE_FORMAT = "yyyyMMddHHmmss";

    /**
     * 如下三个值必须在子类中赋值
     */
    protected String issCode = "49993041";
    static String configFileName = "unionconfig/unionpay_payer_acp_sdk.properties";

    /**
     * 自定义实现银联扩展工具服务类
     */
    static AcpServiceExtend acpServiceExtend;

    /**
     * 初始化方法
     */
    static {
        try {
            logger.info("===== 银联通道：初始化方法开始 =====");
            SDKConfigExtend configExtend = new SDKConfigExtend(configFileName);
            acpServiceExtend = new AcpServiceExtend(configExtend);
            logger.info("===== 银联通道：初始化方法结束 =====");
        } catch (Exception e) {
            logger.info("银联通道：初始化方法异常 ，异常信息：", e);
        }
    }

    @RequestMapping("/ipTest")
    @ResponseBody
    public String httpIpTest(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();

            String header = request.getHeader(nextElement);

            logger.info("nextElement:{} --- value:{}", nextElement,header);
        }

        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = localHost.getHostAddress();
        logger.info("hostAddress:{}",hostAddress);


        String ip = request.getHeader( "x-forwarded-for" );
        logger.info("--------ip:{}",ip);
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "Proxy-Client-IP" );
            logger.info("--------ip:{}",ip);
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "WL-Proxy-Client-IP" );
            logger.info("--------ip:{}",ip);
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getRemoteAddr();
            logger.info("--------ip:{}",ip);
        }
        return "完成测试！";
    }

    /**
     * 处理银联二维码付款方接口银联通知报文
     *
     * @return
     */
    @RequestMapping("/union/payerPayResultNotify")
    @ResponseBody
    public String unionPayerNotify(HttpServletRequest request) {
        return this.resolveNotify(request);
    }

    /**
     * 处理银联二维码付款方接口银联通知报文
     *
     * @return
     */
    @RequestMapping("/union/unionPayerAddnOp")
    @ResponseBody
    public String unionPayerAddnOp(HttpServletRequest request) {
        return this.resolveNotify(request);
    }

    /**
     * 处理银联二维码付款方接口银联通知报文
     *
     * @return
     */
    @RequestMapping("/openPayer/notify_addnOp")
    @ResponseBody
    public String pathParamReceiveTest(HttpServletRequest request) {
        if(null != request){
            logger.info("初始付款方通知参数 OpenPayerNotifyReceiveServlet.request="+request.toString());
        }else{
            logger.info("初始付款方通知参数 OpenPayerNotifyReceiveServlet.request=null");
        }

        // 服务器通知
        String servletPath = request.getPathInfo();
        String servletPath1 = request.getServletPath();
        logger.info("servletPath1:{}", servletPath1);
        logger.info(String.format("初始三方支付结果通知参数,serverName:%s,servletPath:%s,requestParamMap:%s",
                request.getServerName(), servletPath, request.getParameterMap()));

        logger.info("hhahah");
        return "完成测试！";
    }

    /**
     * 处理通知
     * @param request
     * @return
     */
    private String resolveNotify(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            logger.info("接收到银联通知报文[key:{}],[value:{}]：", key, parameterMap.get(key));
        }

        String reqType = "";
        String[] origReqTypes = parameterMap.get("origReqType");
        if (origReqTypes != null) {
            String exOrigReqType = this.exOrigReqType(origReqTypes[0]);
            reqType = exOrigReqType;
        }

        // 响应报文是queryString 的格式
        Map<String, String> contentData = new HashMap<>();
        contentData.put("version", VERSION);

        String[] reqTypes = parameterMap.get("reqType");
        if (reqTypes != null){
            String exReqType = this.exOrigReqType(reqTypes[0]);

            if (Strings.isNullOrEmpty(reqType) && !Strings.isNullOrEmpty(exReqType)){
                reqType = exReqType;
            }
            contentData.put("reqType", reqTypes[0]);
        }
        logger.info("================= 本次通知为：{}", reqType);
        logger.info("假装正在处理银联通知报文 ===========");

        String[] acqCodes = parameterMap.get("acqCode");
        if (acqCodes != null) {
            contentData.put("acqCode", acqCodes[0]);
        }

        contentData.put("respCode", "00");
        contentData.put("respMsg", "处理成功");

        // 签名信息
        Map<String, String> reqData = acpServiceExtend.sign(contentData, ENCODING);

        String reMsg = CommonUtils.mapToQueryString(reqData);
        logger.info("同步响应银联通知:{}", reMsg);
        return reMsg;
    }

    /**
     * 处理交易通知
     * 被通知的交易的类型，包括
     * 付款交易           0130000903
     * 消费撤销交易       0170000903
     * 退款交易           0150000903
     * 取现交易           0590000903
     * 取现冲正交易       0560000903
     *
     * <br/>
     * 消费交易           0310000903
     * 消费冲正交易       0320000903
     * 消费撤销交易       0330000903
     * 退货交易           0340000903
     *
     * @param orgType
     * @return
     */
    private String exOrigReqType(String orgType) {
        if (Objects.equal(orgType, "0130000903")) {
            return "主扫-付款交易";
        } else if (Objects.equal(orgType, "0170000903")) {
            return "主扫-消费撤销交易";
        } else if (Objects.equal(orgType, "0150000903")) {
            return "主扫-退款交易";
        } else if (Objects.equal(orgType, "0590000903")) {
            return "主扫-取现交易";
        } else if (Objects.equal(orgType, "0560000903")) {
            return "主扫-取现冲正交易";
        }

        if (Objects.equal(orgType, "0310000903")) {
            return "被扫-消费交易";
        } else if (Objects.equal(orgType, "0320000903")) {
            return "被扫-消费冲正交易";
        } else if (Objects.equal(orgType, "0330000903")) {
            return "被扫-消费撤销交易";
        } else if (Objects.equal(orgType, "0340000903")) {
            return "被扫-退货交易";
        }else if (Objects.equal(orgType,"0230000903")){
            return "被扫-验密通知";
        }else {
            return "未知交易通知";
        }
    }

}
