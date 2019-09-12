package com.bbxyard.mp.zz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.zz.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from mp_zz_user ${ew.customSqlSegment}")
    List<User> listAllByCustom(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 基于xml实现
     * @param wrapper
     * @return
     */
    List<User> listAllByCustomFromXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 基于xml实现 分页
     * @param wrapper
     * @return
     */
    IPage<User> listPageByCustom(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
