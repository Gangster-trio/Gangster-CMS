package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.QuestionPageService;
import com.gangster.cms.common.pojo.QuestionPage;
import com.gangster.cms.common.pojo.QuestionPageExample;
import com.gangster.cms.dao.mapper.QuestionPageMapper;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/24
 */
@Service
public class QuestPageServiceImpl extends BaseServiceImpl<QuestionPageMapper,QuestionPage,QuestionPageExample> implements QuestionPageService {
}
