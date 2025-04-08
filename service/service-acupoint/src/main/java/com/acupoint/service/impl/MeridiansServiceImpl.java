package com.acupoint.service.impl;

import acupoint.TestAcupoint;
import com.acupoint.mapper.AcupointMapper;
import com.acupoint.mapper.MeridiansMapper;
import com.acupoint.service.AcupointService;
import com.acupoint.service.MeridiansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import meridians.Meridians;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: WangZhiWei
 * @Time: 2025/4/7 15:29
 */
@Service
public class MeridiansServiceImpl  extends ServiceImpl<MeridiansMapper, Meridians>
        implements MeridiansService {
}
