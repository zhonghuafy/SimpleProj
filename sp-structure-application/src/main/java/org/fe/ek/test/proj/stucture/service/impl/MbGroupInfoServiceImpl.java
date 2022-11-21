package org.fe.ek.test.proj.stucture.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fe.ek.test.proj.stucture.dao.mapper.MbGroupInfoMapper;
import org.fe.ek.test.proj.stucture.dao.model.MbGroupInfo;
import org.fe.ek.test.proj.stucture.dto.MbGroupInfoDto;
import org.fe.ek.test.proj.stucture.service.IMbGroupInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 奔驰集团信息字典表 服务实现类
 * </p>
 *
 * @author wzh
 * @since 2022-11-07
 */
@Service
public class MbGroupInfoServiceImpl extends ServiceImpl<MbGroupInfoMapper, MbGroupInfo> implements IMbGroupInfoService {

    @Override
    public List<MbGroupInfoDto> getList() {
        List<MbGroupInfo> list = list();
        return convert(list);
    }

    private List<MbGroupInfoDto> convert(List<MbGroupInfo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().map(this::convert).collect(Collectors.toList());
    }

    private MbGroupInfoDto convert(MbGroupInfo entity) {
        MbGroupInfoDto dto = new MbGroupInfoDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
