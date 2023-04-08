package com.dev.schooladmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.schooladmin.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Notice)表数据库访问层
 *
 * @author fauchard
 * @since 2023-04-08 10:45:02
 */
@Mapper
public interface NoticeDao extends BaseMapper<Notice> {

}

