import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.ehcache.prod.Productor;
import org.fe.ek.test.model.util.CodecUtil;
import org.fe.ek.test.common.util.PhoneGen;
import org.fe.ek.test.common.util.RandomInt;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.junit.Test;

/**
 * @program: TestProj
 * @description: SimpleTest
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Slf4j
public class SimpleTest {

    @Test
    public void phone() {
        log.info("phone: {}", PhoneGen.getTel());
    }

    @Test
    public void random() {
        for (int i = 0; i < 20; i ++) {
            log.info("number: {}", RandomInt.getIntWith(1,4));
        }
    }

    @Test
    public void oneReqPack() {
        BoPortraitGenerator boPortraitGenerator = new BoPortraitGenerator();
        Productor productor = new Productor();
        BoPortraitModel model = boPortraitGenerator.portrait();
        log.info("list: {}", model.getList().size());
    }

    @Test
    public void modulus() {
        int a = 1, b= 2, c = 3, d = 33, e = 44, f = 123;
        log.info("{},{},{},{},{},{}", a % 2, b % 2, c % 2, d % 2, e % 2, f % 2);
    }

    @Test
    public void testSignature() {
        String sys = "V2JWA_YHHX";
        String apiKey = "3MHYpeYnkMg0y5TU";
        String shipId = "4303633426409";
        String result = CodecUtil.encodeHexString(apiKey,shipId);
        log.info("result: {}", result);
        log.info("compare: {}", "d974f1834fb55d9c2dc4ebc9cda841ac".equals(result));
    }
}
