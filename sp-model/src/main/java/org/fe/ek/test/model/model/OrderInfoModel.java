package org.fe.ek.test.model.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: TestProj
 * @description: OrderInfoModel
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Data
public class OrderInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mailNo;

    private String receiveMobile;

    private String sendId;
}
