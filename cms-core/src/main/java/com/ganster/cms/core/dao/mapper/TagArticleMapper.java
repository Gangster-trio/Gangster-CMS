package com.ganster.cms.core.dao.mapper;

import com.ganster.cms.core.pojo.TagArticle;
import com.ganster.cms.core.pojo.TagArticleExample;
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