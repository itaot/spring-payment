package me.itaot.payment.cpcn.cpcn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import me.itaot.payment.tools.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class SignatureFactory {

    private static String cpcnCertPath ;

    private static Logger log = LoggerFactory.getLogger(SignatureFactory.class);

    private static Properties properties = new Properties();
    private static HashMap<String, Signer> signerMap = new HashMap();
    private static HashMap<String, Verifier> verifierMap = new HashMap();
    private static String defaultSigner;
    private static String defaultVerifier;
    private static boolean hasInit = false;

    private static void init() {
        log.info("中金支付加签\\验签模块初始化开始...");
        long start = System.currentTimeMillis();
        initProperties();
        try {
            initCertificates();
        } catch (Exception e) {
            log.error("证书初始化异常{}",e);
        }
        long end = System.currentTimeMillis();
        log.info("中金支付加签\\延签模块初始化结束，共耗时" + (end - start) + "ms");
        hasInit = true;
    }

    private static void initProperties()  {

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:cpcn.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cpcnCertPath = file.getParent();
        try {
            properties.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("文件不存在..." + e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件读取异常..." + e);
        }
    }

    private static void initCertificates() throws Exception {

        String myKeystoreFilename = properties
                .getProperty("my.keystore.filename");
        String myKeystorePassword = properties
                .getProperty("my.keystore.password");

        String algorithm = properties.getProperty("signatureAlgorithm");

        log.info("*************** Cpcn Cert Init Start ***************");
        log.info("机构签名口令:" + myKeystorePassword);

        if (!StringUtil.isEmpty(algorithm)) {
            algorithm = SecurityUtil.convertSignatureAlgorithm(algorithm);
            if (algorithm == null)
                throw new Exception("签名算法不合法");
        } else {
            algorithm = "SHA1withRSA";
        }
        log.info("机构签名算法:" + algorithm);

        if (myKeystoreFilename == null) {
            throw new Exception("Missing the property: my.keystore.filename");
        }
        String myKeystoreFilepath = cpcnCertPath + File.separatorChar
                + myKeystoreFilename;
        log.info("证机构签名证书:" + myKeystoreFilepath);

        Signer signer = new PfxSigner(myKeystoreFilepath, myKeystorePassword,
                algorithm);
        SignatureFactory.addSigner("signer", signer);

        String certificateFilename = properties
                .getProperty("payment.certificate.filename");
        if (certificateFilename == null) {
            throw new Exception(
                    "Missing the property: payment.certificate.filename");
        }
        String certificateFilepath = cpcnCertPath + File.separatorChar
                + properties.getProperty("payment.certificate.filename");
        log.info("中金公钥证书:" + certificateFilepath);

        Verifier verifier = new CertificateVerifier(certificateFilepath,
                algorithm);
        SignatureFactory.addVerifier("verifier", verifier);
        log.info("*************** Cpcn Cert Init End ***************");
    }

    private static void addSigner(String signerID, Signer signer) {
        signerMap.put(signerID, signer);
        defaultSigner = signerID;
    }

    private static void addVerifier(String verifierID, Verifier verifier) {
        verifierMap.put(verifierID, verifier);
        defaultVerifier = verifierID;
    }

    public static Signer getSigner() {
        if (!hasInit) {
            init();
        }
        return ((Signer) signerMap.get(defaultSigner));
    }

    public static Signer getSigner(String signerID) {
        if (!hasInit) {
            init();
        }
        return ((Signer) signerMap.get(signerID));
    }

    public static Verifier getVerifier() {
        if (!hasInit) {
            init();
        }
        return ((Verifier) verifierMap.get(defaultVerifier));
    }

    public static Verifier getVerifier(String verifierID) {
        if (!hasInit) {
            init();
        }
        return ((Verifier) verifierMap.get(verifierID));
    }

    public static void clearVerifier() {
        verifierMap.clear();
    }


}
