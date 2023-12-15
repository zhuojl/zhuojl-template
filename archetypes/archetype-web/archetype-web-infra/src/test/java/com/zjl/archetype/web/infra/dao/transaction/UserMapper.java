package com.zjl.archetype.web.infra.dao.transaction;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjl.component.mybatisplus.config.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MyBaseMapper<User> {

}
