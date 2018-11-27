/**
 *
 * Licensed Property to China UnionPay Co., Ltd.
 *
 * (C) Copyright of China UnionPay Co., Ltd. 2010
 *     All Rights Reserved.
 *
 *
 * Modification History:
 * =============================================================================
 *   Author         Date          Description
 *   ------------ ---------- ---------------------------------------------------
 *   xshu       2014-05-28       证书工具类.
 * =============================================================================
 */
package com.xiaozhameng.sdks.unionpayextend;

import com.google.common.base.Strings;


import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.RSAPublicKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.xiaozhameng.sdks.unionpayextend.SDKConstants.UNIONPAY_CNNAME;


/**
 * @ClassName: CertUtilExtend
 * @Description: acpsdk证书工具类，主要用于对证书的加载和使用
 * @date 2016-7-22 下午2:46:20
 *
 */
public class CertUtilExtend {

	private static Logger logger = LoggerFactory.getLogger(CertUtilExtend.class);

	/** 证书容器，存储对商户请求报文签名私钥证书. */
	private static KeyStore keyStore = null;
	/** 敏感信息加密公钥证书 */
	private static X509Certificate encryptCert = null;
	/** 磁道加密公钥 */
	private static PublicKey encryptTrackKey = null;
	/** 验证银联返回报文签名证书. */
	private static X509Certificate validateCert = null;
	/** 验签中级证书 */
	private static X509Certificate middleCert = null;
	/** 验证银联返回报文签名的公钥证书存储Map. */
	private static Map<String, X509Certificate> certMap = new HashMap<String, X509Certificate>();
	/** 商户私钥存储Map */
	private final static Map<String, KeyStore> keyStoreMap = new ConcurrentHashMap<String, KeyStore>();

	/**
	 * 配置对象
	 * @param sdkConfigExtend
	 */
	private SDKConfigExtend sdkConfigExtend;

	/**
	 * 构造器
	 * @param sdkConfigExtend
	 */
	public CertUtilExtend(SDKConfigExtend sdkConfigExtend) {
		if (sdkConfigExtend == null ){
			logger.error("===== 配置对象com.yeepay.g3.core.bankcommunicate.util.unionpayextend.CertUtilExtend.sdkConfigExtend初始化失败 =====");
			throw new RuntimeException("配置对象sdkConfigExtend 初始化失败");
		}
		this.sdkConfigExtend = sdkConfigExtend;
		init();
	}

	/**
	 * 初始化所有证书.
	 */
	private void init() {
		try {
			addProvider();//向系统添加BC provider
			initSignCert();//初始化签名私钥证书
			initMiddleCert();//初始化中级公钥证书
			initEncryptCert();//初始化加密公钥
			initTrackKey();//构建磁道加密公钥
			initValidateCertFromDir();//初始化所有的验签证书
		} catch (Exception e) {
			logger.error("init失败。（如果是用对称密钥签名的可无视此异常。）", e);
		}
	}

	/**
	 * 添加签名，验签，加密算法提供者
	 */
	private void addProvider(){
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} else {
			//解决eclipse调试时tomcat自动重新加载时，BC存在不明原因异常的问题。
			Security.removeProvider("BC");
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		}
		//printSysInfo();
	}

	/**
	 * 用配置文件acp_sdk.properties中配置的私钥路径和密码 加载签名证书
	 */
	private void initSignCert() {
		if(!"01".equals(sdkConfigExtend.getSignMethod())){
			return;
		}
		if (sdkConfigExtend.getSignCertPath() == null
				|| sdkConfigExtend.getSignCertPwd() == null
				|| sdkConfigExtend.getSignCertType() == null) {
			return;
		}
		if (null != keyStore) {
			keyStore = null;
		}
		try {
			keyStore = getKeyInfo(sdkConfigExtend.getSignCertPath(),
					sdkConfigExtend.getSignCertPwd(), sdkConfigExtend.getSignCertType());
		} catch (Exception e) {
			logger.error("InitSignCert Error", e);
		}
	}

	/**
	 * 用配置文件acp_sdk.properties配置路径 加载敏感信息加密证书
	 */
	private void initMiddleCert() {
		if (!Strings.isNullOrEmpty(sdkConfigExtend.getMiddleCertPath())) {
			middleCert = initCert(sdkConfigExtend.getMiddleCertPath());
		} else {

		}
	}

	/**
	 * 用配置文件acp_sdk.properties配置路径 加载银联公钥上级证书（中级证书）
	 */
	private void initEncryptCert() {
		if (!Strings.isNullOrEmpty(sdkConfigExtend.getEncryptCertPath())) {
			encryptCert = initCert(sdkConfigExtend.getEncryptCertPath());
		} else {
			//logger.info("WARN: acpsdk.encryptCert.path is empty");
		}
	}

	/**
	 * 用配置文件acp_sdk.properties配置路径 加载磁道公钥
	 */
	private void initTrackKey() {
		if (!Strings.isNullOrEmpty(sdkConfigExtend.getEncryptTrackKeyModulus())
				&& !Strings.isNullOrEmpty(sdkConfigExtend.getEncryptTrackKeyExponent())) {
			encryptTrackKey = getPublicKey(sdkConfigExtend.getEncryptTrackKeyModulus(),
					sdkConfigExtend.getEncryptTrackKeyExponent());
		} else {
			//logger.info("WARN: acpsdk.encryptTrackKey.modulus or acpsdk.encryptTrackKey.exponent is empty");
		}
	}

	/**
	 * 用配置文件acp_sdk.properties配置路径 加载验证签名证书
	 */
	private void initValidateCertFromDir() {
		if(!"01".equals(sdkConfigExtend.getSignMethod())){
			return;
		}
		certMap.clear();
		String dir = sdkConfigExtend.getValidateCertDir();
		if (Strings.isNullOrEmpty(dir)) {
			logger.info("WARN: acpsdk.validateCert.dir is empty");
			return;
		}
		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509", "BC");
			File fileDir = new File(dir);
			File[] files = fileDir.listFiles(new CerFilter());
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				in = new FileInputStream(file.getAbsolutePath());
				validateCert = (X509Certificate) cf.generateCertificate(in);
				certMap.put(validateCert.getSerialNumber().toString(),
						validateCert);
			}
		} catch (Exception e) {
			logger.error("LoadVerifyCert Error No BC Provider", e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					logger.error("LoadVerifyCert",e);
				}
			}
		}
	}

	/**
	 * 用给定的路径和密码 加载签名证书，并保存到certKeyStoreMap
	 *
	 * @param certFilePath
	 * @param certPwd
	 */
	private void loadSignCert(String certFilePath, String certPwd) {
		KeyStore keyStore = null;
		try {
			keyStore = getKeyInfo(certFilePath, certPwd, "PKCS12");
			keyStoreMap.put(certFilePath, keyStore);
		} catch (Exception e) {
			logger.error("LoadRsaCert Error", e);
		}
	}

	/**
	 * 通过证书路径初始化为公钥证书
	 * @param path
	 * @return
	 */
	private X509Certificate initCert(String path) {
		X509Certificate encryptCertTemp = null;
		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509", "BC");
			in = new FileInputStream(path);
			encryptCertTemp = (X509Certificate) cf.generateCertificate(in);
		}  catch (Exception e) {
			logger.error("LoadVerifyCert Error No BC Provider", e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					logger.error("LoadVerifyCert", e);
				}
			}
		}
		return encryptCertTemp;
	}

	/**
	 * 通过keyStore 获取私钥签名证书PrivateKey对象
	 *
	 * @return
	 */
	public PrivateKey getSignCertPrivateKey() {
		try {
			Enumeration<String> aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
			}
			PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias,
					sdkConfigExtend.getSignCertPwd().toCharArray());
			return privateKey;
		} catch (Exception e) {
			logger.error("getSignCertPrivateKey Error", e);
			return null;
		}
	}
	/**
	 * 通过指定路径的私钥证书  获取PrivateKey对象
	 *
	 * @return
	 */
	public PrivateKey getSignCertPrivateKeyByStoreMap(String certPath,
			String certPwd) {
		if (!keyStoreMap.containsKey(certPath)) {
			loadSignCert(certPath, certPwd);
		}
		try {
			Enumeration<String> aliasenum = keyStoreMap.get(certPath)
					.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
			}
			PrivateKey privateKey = (PrivateKey) keyStoreMap.get(certPath)
					.getKey(keyAlias, certPwd.toCharArray());
			return privateKey;
		}  catch (Exception e) {
			logger.error("getSignCertPrivateKeyByStoreMap Error", e);
			return null;
		}
	}

	/**
	 * 获取敏感信息加密证书PublicKey
	 *
	 * @return
	 */
	public PublicKey getEncryptCertPublicKey() {
		if (null == encryptCert) {

			String path = sdkConfigExtend.getEncryptCertPath();
			if (!Strings.isNullOrEmpty(path)) {
				encryptCert = initCert(path);
				return encryptCert.getPublicKey();
			} else {
				logger.info("acpsdk.encryptCert.path is empty");
				return null;
			}
		} else {
			return encryptCert.getPublicKey();
		}
	}

	/**
	 * 重置敏感信息加密证书公钥
	 */
	public void resetEncryptCertPublicKey() {
		encryptCert = null;
	}

	/**
	 * 获取磁道加密证书PublicKey
	 *
	 * @return
	 */
	public PublicKey getEncryptTrackPublicKey() {
		if (null == encryptTrackKey) {
			initTrackKey();
		}
		return encryptTrackKey;
	}

	/**
	 * 通过certId获取验签证书Map中对应证书PublicKey
	 *
	 * @param certId 证书物理序号
	 * @return 通过证书编号获取到的公钥
	 */
	public PublicKey getValidatePublicKey(String certId) {
		X509Certificate cf = null;
		if (certMap.containsKey(certId)) {
			// 存在certId对应的证书对象
			cf = certMap.get(certId);
			return cf.getPublicKey();
		} else {
			// 不存在则重新Load证书文件目录
			initValidateCertFromDir();
			if (certMap.containsKey(certId)) {
				// 存在certId对应的证书对象
				cf = certMap.get(certId);
				return cf.getPublicKey();
			} else {
				logger.error("缺少certId=[" + certId + "]对应的验签证书.");
				return null;
			}
		}
	}

	/**
	 * 获取配置文件acp_sdk.properties中配置的签名私钥证书certId
	 *
	 * @return 证书的物理编号
	 */
	public String getSignCertId() {
		try {
			Enumeration<String> aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
			}
			X509Certificate cert = (X509Certificate) keyStore
					.getCertificate(keyAlias);
			return cert.getSerialNumber().toString();
		} catch (Exception e) {
			logger.error("getSignCertId Error", e);
			return null;
		}
	}

	/**
	 * 获取敏感信息加密证书的certId
	 *
	 * @return
	 */
	public String getEncryptCertId() {
		if (null == encryptCert) {
			String path = sdkConfigExtend.getEncryptCertPath();
			if (!Strings.isNullOrEmpty(path)) {
				encryptCert = initCert(path);
				return encryptCert.getSerialNumber().toString();
			} else {
				logger.error("acpsdk.encryptCert.path is empty");
				return null;
			}
		} else {
			return encryptCert.getSerialNumber().toString();
		}
	}

	/**
	 * 将签名私钥证书文件读取为证书存储对象
	 *
	 * @param pfxkeyfile
	 *            证书文件名
	 * @param keypwd
	 *            证书密码
	 * @param type
	 *            证书类型
	 * @return 证书对象
	 * @throws IOException
	 */
	private KeyStore getKeyInfo(String pfxkeyfile, String keypwd,
			String type) throws IOException {
		FileInputStream fis = null;
		try {
			KeyStore ks = KeyStore.getInstance(type, "BC");
			fis = new FileInputStream(pfxkeyfile);
			char[] nPassword = null;
			nPassword = null == keypwd || "".equals(keypwd.trim()) ? null: keypwd.toCharArray();
			if (null != ks) {
				ks.load(fis, nPassword);
			}
			return ks;
		} catch (Exception e) {
			logger.error("getKeyInfo Error", e);
			return null;
		} finally {
			if(null!=fis){
				fis.close();
			}
		}
	}

	/**
	 * 通过签名私钥证书路径，密码获取私钥证书certId
	 * @param certPath
	 * @param certPwd
	 * @return
	 */
	public String getCertIdByKeyStoreMap(String certPath, String certPwd) {
		if (!keyStoreMap.containsKey(certPath)) {
			// 缓存中未查询到,则加载RSA证书
			loadSignCert(certPath, certPwd);
		}
		return getCertIdIdByStore(keyStoreMap.get(certPath));
	}

	/**
	 * 通过keystore获取私钥证书的certId值
	 * @param keyStore
	 * @return
	 */
	private String getCertIdIdByStore(KeyStore keyStore) {
		Enumeration<String> aliasenum = null;
		try {
			aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
			}
			X509Certificate cert = (X509Certificate) keyStore
					.getCertificate(keyAlias);
			return cert.getSerialNumber().toString();
		} catch (KeyStoreException e) {
			logger.error("getCertIdIdByStore Error", e);
			return null;
		}
	}

	/**
	 * 使用模和指数生成RSA公钥 注意：此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同
	 *
	 * @param modulus
	 *            模
	 * @param exponent
	 *            指数
	 * @return
	 */
	private PublicKey getPublicKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			logger.error("构造RSA公钥失败：" + e);
			return null;
		}
	}

	/**
	 * 将字符串转换为X509Certificate对象.
	 *
	 * @param x509CertString
	 * @return
	 */
	public X509Certificate genCertificateByStr(String x509CertString) {
		X509Certificate x509Cert = null;
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
			InputStream tIn = new ByteArrayInputStream(
					x509CertString.getBytes("ISO-8859-1"));
			x509Cert = (X509Certificate) cf.generateCertificate(tIn);
		} catch (Exception e) {
			logger.error("gen certificate error", e);
		}
		return x509Cert;
	}

	/**
	 * 从配置文件acp_sdk.properties中获取验签公钥使用的上级证书（中级证书）
	 * @return
	 */
	public X509Certificate getMiddleCert() {
		if (null == middleCert) {
			String path = sdkConfigExtend.getMiddleCertPath();
			if (!Strings.isNullOrEmpty(path)) {
				initMiddleCert();
			} else {
				logger.info(SDKConfigExtend.SDK_ROOTCERT_PATH + " not set in " + SDKConfigExtend.FILE_NAME);
				return null;
			}
		}
		return middleCert;
	}

	/**
	 * 获取证书的CN
	 * @param aCert
	 * @return
	 */
	private static String getIdentitiesFromCertficate(X509Certificate aCert) {
		String tDN = aCert.getSubjectDN().toString();
		String tPart = "";
		if ((tDN != null)) {
			String tSplitStr[] = tDN.substring(tDN.indexOf("CN=")).split("@");
			if (tSplitStr != null && tSplitStr.length > 2
					&& tSplitStr[2] != null)
				tPart = tSplitStr[2];
		}
		return tPart;
	}

	/**
	 * 检查证书链
	 *            根证书
	 * @param cert
	 *            待验证的证书
	 * @return
	 */
	public boolean verifyCertificate(X509Certificate cert) {

		X509Certificate middleCert = getMiddleCert();
		if (null == middleCert || null == cert) {
			logger.error("rootCert or cert must Not null");
			return false;
		}
		try {
			cert.checkValidity();//验证有效期
			cert.verify(middleCert.getPublicKey());
		} catch (Exception e) {
			logger.error("verifyCertificate fail", e);
			return false;
		}

		if(sdkConfigExtend.isIfValidateCNName()){
			// 验证公钥是否属于银联
			if(!UNIONPAY_CNNAME.equals(CertUtilExtend.getIdentitiesFromCertficate(cert))) {
				return false;
			}
		} else {
			// 验证公钥是否属于银联
			if(!UNIONPAY_CNNAME.equals(CertUtilExtend.getIdentitiesFromCertficate(cert))
					&& !"00040000:SIGN".equals(CertUtilExtend.getIdentitiesFromCertficate(cert))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 打jre中印算法提供者列表
	 */
	private void printProviders() {
		Provider[] providers = Security.getProviders();
		for (int i = 0; i < providers.length; i++) {
			logger.error(i + 1 + "." + providers[i].getName());
		}
	}

	/**
	 * 证书文件过滤器
	 *
	 */
	static class CerFilter implements FilenameFilter {
		public boolean isCer(String name) {
			if (name.toLowerCase().endsWith(".cer")) {
				return true;
			} else {
				return false;
			}
		}
		@Override
		public boolean accept(File dir, String name) {
			return isCer(name);
		}
	}

}
