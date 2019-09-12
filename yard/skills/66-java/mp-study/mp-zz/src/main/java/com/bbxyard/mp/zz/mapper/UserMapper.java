package com.bbxyard.mp.zz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.bbxyard.mp.zz.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from mp_zz_user ${ew.customSqlSegment}")
    List<User> listAllByCustom(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    List<User> listAllByCustomFromXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
