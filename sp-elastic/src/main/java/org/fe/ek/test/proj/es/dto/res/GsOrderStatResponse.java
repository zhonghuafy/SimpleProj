package org.fe.ek.test.proj.es.dto.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: SimpleProj
 * @description: GsOrderStatResponse
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
@Data
public class GsOrderStatResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *网点编号
     */
    private String gs;

    /**
     * 合作商编号
     */
    private List<String> partnerIdList;

    private Long docCount;
}
