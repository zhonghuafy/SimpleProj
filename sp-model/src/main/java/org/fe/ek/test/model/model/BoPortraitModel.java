package org.fe.ek.test.model.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: TestProj
 * @description: BoPortrait数据包
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Data
public class BoPortraitModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private List<OrderInfoModel> list;
}
