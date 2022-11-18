import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.ehcache.prod.Productor;
import org.fe.ek.test.model.util.CodecUtil;
import org.fe.ek.test.common.util.PhoneGen;
import org.fe.ek.test.common.util.RandomInt;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testDuration() {
        Date start = Date.from(LocalDate.of(2019,9,1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.of(2021,2,1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Instant startInstant = start.toInstant();
        Instant endInstant = end.toInstant();
        log.info("instant compare: {}", startInstant.compareTo(endInstant));
        log.info("duration: {}", ChronoUnit.DAYS.between(startInstant,endInstant));
        Period period = Period.between(startInstant.atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        log.info("period: month: {}, days: {}", period.getMonths(), period.getDays());
    }

    @Test
    public void testsublist() {
        List<String> list = Arrays.asList("a","b","c","d","e", "f", "g");
        int index = 0;
        while (index < list.size()) {
            int endIndex = Math.min(index + 2, list.size());
            List<String> subList = list.subList(index, endIndex);
            index = endIndex;// 调用韵呼宝接口
            subList.forEach(a -> a = "ss");
        }
        //"a","b","c","d","e", "f", "g"
        list.forEach(log::info);
    }
}
