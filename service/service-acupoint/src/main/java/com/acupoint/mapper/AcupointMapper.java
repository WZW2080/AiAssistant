package com.acupoint.mapper;

import acupoint.TestAcupoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author w
* @description 针对表【acupoint(穴位表)】的数据库操作Mapper
* @createDate 2025-03-24 11:12:43
* @Entity generator.domain.Acupoint
*/
public interface AcupointMapper extends BaseMapper<TestAcupoint> {

    /**
     * 根据经脉id或者名字查询该经脉上所有穴位
     * @param id 经脉id
     * @param name 经脉名字
     * @return 返回所属经脉的所有穴位
     */
    List<TestAcupoint> acupointByMeridians(@Param("id") Integer id,
                                           @Param("name") String name);
}




