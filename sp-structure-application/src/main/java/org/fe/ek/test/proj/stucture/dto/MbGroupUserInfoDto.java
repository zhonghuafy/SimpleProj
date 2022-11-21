package org.fe.ek.test.proj.stucture.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: pjms-construct
 * @description: MbGroupUserInfoDto
 * @author: Wang Zhenhua
 * @create: 2022-11-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-07
 **/
@Data
public class MbGroupUserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 账号id
     */
    private Integer accountId;

    /**
     * 集团区域, EAST, WEST, SOUTH, NORTH, Others
     */
    private String groupRegion;

    /**
     * 合作伙伴id
     */
    private Integer partnerId;

    /**
     * 删除标记
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
