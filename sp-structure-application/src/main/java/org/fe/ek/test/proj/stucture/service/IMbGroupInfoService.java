package org.fe.ek.test.proj.stucture.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fe.ek.test.proj.stucture.dao.model.MbGroupInfo;
import org.fe.ek.test.proj.stucture.dto.MbGroupInfoDto;

import java.util.List;

/**
 * <p>
 * 奔驰集团信息字典表 服务类
 * </p>
 *
 * @author wzh
 * @since 2022-11-07
 */
public interface IMbGroupInfoService extends IService<MbGroupInfo> {

    /**
     * get list
     * @return
     */
    List<MbGroupInfoDto> getList();
}
