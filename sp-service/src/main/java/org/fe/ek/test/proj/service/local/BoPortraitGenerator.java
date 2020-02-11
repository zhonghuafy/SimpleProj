package org.fe.ek.test.proj.service.local;

import org.fe.ek.test.common.util.PhoneGen;
import org.fe.ek.test.common.util.RandomInt;
import org.fe.ek.test.model.cache.BoPortraitIDCache;
import org.fe.ek.test.model.enums.SendIdEnum;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.model.model.OrderInfoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: SimpleProj
 * @description: generate BoPortrait data package
 * @author: Wang Zhenhua
 * @create: 2020-02-11
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-11
 **/
@Service
public class BoPortraitGenerator {

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
