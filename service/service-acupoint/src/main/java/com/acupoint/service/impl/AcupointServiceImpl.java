package com.acupoint.service.impl;

import acupoint.TestAcupoint;
import com.acupoint.mapper.AcupointMapper;
import com.acupoint.service.AcupointService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author w
* @description 针对表【acupoint(穴位表)】的数据库操作Service实现
* @createDate 2025-03-24 11:12:43
*/
@Service
public class AcupointServiceImpl extends ServiceImpl<AcupointMapper, TestAcupoint>
    implements AcupointService {


    @Override
    public Page<TestAcupoint> getAcupointPage(Integer current, Integer size) {
        // 创建分页对象
        Page<TestAcupoint> page = new Page<>(current, size);
        return baseMapper.selectPage(page, null);
    }
}




