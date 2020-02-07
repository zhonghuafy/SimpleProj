package org.fe.ek.test.proj.ehcache.prod;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.ehcache.cache.*;
import org.fe.ek.test.model.enums.SendIdEnum;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.model.model.OrderInfoModel;
import org.fe.ek.test.common.util.PhoneGen;
import org.fe.ek.test.common.util.RandomInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: TestProj
 * @description: 生产者，模拟产生数据包并存放到缓存
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Component
@Slf4j
public class Productor implements Runnable {

    @Autowired
    private BoPortraitCache cache;

    @Override
    public void run() {
        while (true) {
            try {
                BoPortraitModel data = portrait();
                log.info("[productor] product a request package with 1000 order info");
                cache.putBo(data);

                Counter.increaseProduct();

                Thread.sleep(50);
            }catch (Exception ex) {
                log.error("[productor] error to produce data. {}",ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * 每个数据包包含一个长度为1000的列表
     * @return
     */
    public BoPortraitModel portrait() {
        BoPortraitModel bo = new BoPortraitModel();
        bo.setId(BoPortraitIDCache.nextId());
        List<OrderInfoModel> list = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            list.add(oneOrder());
        }
        bo.setList(list);
        return bo;
    }

    private OrderInfoModel oneOrder() {
        OrderInfoModel order = new OrderInfoModel();
        order.setMailNo(UUID.randomUUID().toString());
        order.setReceiveMobile(PhoneGen.getTel());
        SendIdEnum sendId = SendIdEnum.getById(RandomInt.getIntWith(SendIdEnum.getMin().getId(),SendIdEnum.getMax().getId()));
        order.setSendId(String.valueOf(sendId.getId()));
        return order;
    }
}
