package com.dev.schooladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.schooladmin.dao.NoticeDao;
import com.dev.schooladmin.entity.Notice;
import com.dev.schooladmin.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * (Notice)表服务实现类
 *
 * @author fauchard
 * @since 2023-04-08 10:45:03
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements NoticeService {

}

