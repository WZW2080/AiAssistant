package com.acupoint.service;

import acupoint.TestAcupoint;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author w
* @description 针对表【acupoint(穴位表)】的数据库操作Service
* @createDate 2025-03-24 11:12:43
*/
public interface AcupointService extends IService<TestAcupoint> {
    /**
     * 分页查询
     * @param current 当前页
     * @param size 每页显示条数
     * @return 返回分页数据
     */
    Page<TestAcupoint> getAcupointPage(Integer current, Integer size);
}
