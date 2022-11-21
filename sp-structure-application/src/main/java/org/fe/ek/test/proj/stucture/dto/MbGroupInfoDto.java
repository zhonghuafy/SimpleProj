package org.fe.ek.test.proj.stucture.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: pjms-construct
 * @description: MbGroupInfoDto
 * @author: Wang Zhenhua
 * @create: 2022-11-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-07
 **/
@Data
public class MbGroupInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 集团id
     */
    private Integer dealerGroupId;

    /**
     * 中文名称
     */
    private String nameCn;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 英文简称
     */
    private String dealerGroupEn;

    /**
     * 英文简称2
     */
    private String dealerGroup;

    /**
     * 中文简称
     */
    private String dealerGroupCn;

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
}
