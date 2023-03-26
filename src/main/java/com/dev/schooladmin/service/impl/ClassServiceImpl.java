package com.dev.schooladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.schooladmin.dao.ClassDao;
import com.dev.schooladmin.entity.Class;
import com.dev.schooladmin.service.ClassService;
import org.springframework.stereotype.Service;

/**
 * (Class)表服务实现类
 *
 * @author fauchard
 * @since 2023-03-26 20:11:16
 */
@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, Class> implements ClassService {

}

