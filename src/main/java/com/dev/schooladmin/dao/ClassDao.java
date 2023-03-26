package com.dev.schooladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.schooladmin.entity.Class;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Class)表数据库访问层
 *
 * @author fauchard
 * @since 2023-03-26 20:11:16
 */
@Mapper
public interface ClassDao extends BaseMapper<Class> {

}

