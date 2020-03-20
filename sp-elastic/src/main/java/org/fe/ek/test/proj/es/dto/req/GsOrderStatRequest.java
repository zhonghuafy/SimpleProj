package org.fe.ek.test.proj.es.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: SimpleProj
 * @description: GsOrderStatRequest
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
@Data
public class GsOrderStatRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date start;

    private Date end;

    private String gs;

    private Boolean isGroupDate;

}
