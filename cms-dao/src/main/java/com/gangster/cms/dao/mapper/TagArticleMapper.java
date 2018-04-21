package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.TagArticle;
import com.gangster.cms.common.pojo.TagArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagArticleMapper {
    long countByExample(TagArticleExample example);

    int deleteByExample(TagArticleExample example);

    int insert(TagArticle record);

    int insertSelective(TagArticle record);

    List<TagArticle> selectByExample(TagArticleExample example);

    int updateByExampleSelective(@Param("record") TagArticle record, @Param("example") TagArticleExample example);

    int updateByExample(@Param("record") TagArticle record, @Param("example") TagArticleExample example);
}