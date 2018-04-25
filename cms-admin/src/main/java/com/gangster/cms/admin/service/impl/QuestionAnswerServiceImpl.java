package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.QuestionAnswerService;
import com.gangster.cms.common.pojo.QuestionAnswer;
import com.gangster.cms.common.pojo.QuestionAnswerExample;
import com.gangster.cms.dao.mapper.QuestionAnswerMapper;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/24
 */
@Service
public class QuestionAnswerServiceImpl extends BaseServiceImpl<QuestionAnswerMapper, QuestionAnswer, QuestionAnswerExample> implements QuestionAnswerService {
}
