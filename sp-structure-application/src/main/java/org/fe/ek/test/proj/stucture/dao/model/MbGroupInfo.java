package org.fe.ek.test.proj.stucture.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 奔驰集团信息字典表
 * </p>
 *
 * @author wzh
 * @since 2022-11-07
 */
@TableName("mb_group_info")
public class MbGroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDealerGroupId() {
        return dealerGroupId;
    }

    public void setDealerGroupId(Integer dealerGroupId) {
        this.dealerGroupId = dealerGroupId;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDealerGroupEn() {
        return dealerGroupEn;
    }

    public void setDealerGroupEn(String dealerGroupEn) {
        this.dealerGroupEn = dealerGroupEn;
    }

    public String getDealerGroup() {
        return dealerGroup;
    }

    public void setDealerGroup(String dealerGroup) {
        this.dealerGroup = dealerGroup;
    }

    public String getDealerGroupCn() {
        return dealerGroupCn;
    }

    public void setDealerGroupCn(String dealerGroupCn) {
        this.dealerGroupCn = dealerGroupCn;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MbGroupInfo{" +
        "id=" + id +
        ", dealerGroupId=" + dealerGroupId +
        ", nameCn=" + nameCn +
        ", nameEn=" + nameEn +
        ", dealerGroupEn=" + dealerGroupEn +
        ", dealerGroup=" + dealerGroup +
        ", dealerGroupCn=" + dealerGroupCn +
        ", partnerId=" + partnerId +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
