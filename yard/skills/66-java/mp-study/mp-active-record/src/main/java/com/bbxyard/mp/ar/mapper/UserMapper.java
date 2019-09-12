package com.bbxyard.mp.ar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbxyard.mp.ar.model.User;

/**
 * <p>
 * 这个得有，就算不去用它否则默认不注入
 * </p>
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 */
public interface UserMapper extends BaseMapper<User> {
}
