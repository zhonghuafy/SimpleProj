package org.fe.ek.test.proj.service.local;

import org.fe.ek.test.common.util.PhoneGen;
import org.fe.ek.test.common.util.RandomInt;
import org.fe.ek.test.model.cache.BoPortraitIDCache;
import org.fe.ek.test.model.enums.SendIdEnum;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.model.model.OrderInfoModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public static final int PACKAGE_ITEM_SIZE = 1000;

    /**
     * 每个数据包包含一个长度为1000的列表
     * @return
     */
    public BoPortraitModel portrait() {
        BoPortraitModel bo = new BoPortraitModel();
        bo.setId(BoPortraitIDCache.nextId());
        List<OrderInfoModel> list = new ArrayList<>(PACKAGE_ITEM_SIZE);
        for (int i = 0; i < PACKAGE_ITEM_SIZE; i++) {
            list.add(oneOrder());
        }
        bo.setList(list);
        return bo;
    }

    /**
     * 每个数据包包含一个长度为1000的列表
     * mailno will read from txt file
     * @return
     */
    public BoPortraitModel portraitReal() {
        BoPortraitModel bo = new BoPortraitModel();
        bo.setId(BoPortraitIDCache.nextId());
        bo.setList(orderListReal());
        return bo;
    }

    private OrderInfoModel oneOrder() {
        return initOrder(UUID.randomUUID().toString());
    }

    private List<OrderInfoModel> orderListReal() {
        List<String> mailnoList = RealMailnos.generateMailnoBatch();
        if (CollectionUtils.isEmpty(mailnoList)) {
            return null;
        }
        List<OrderInfoModel> list = new ArrayList<>(PACKAGE_ITEM_SIZE);
        for (int i = 0; i < PACKAGE_ITEM_SIZE; i++) {
            list.add(initOrder(mailnoList.get(i)));
        }
        return list;
    }

    private OrderInfoModel initOrder(String mailno) {
        OrderInfoModel order = new OrderInfoModel();
        order.setMailNo(mailno);
        order.setReceiveMobile(PhoneGen.getTel());
        SendIdEnum sendId = SendIdEnum.getById(RandomInt.getIntWith(SendIdEnum.getMin().getId(),SendIdEnum.getMax().getId()));
        order.setSendId(String.valueOf(sendId.getId()));
        return order;
    }
}
