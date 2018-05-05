package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.CmsMailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsMailMapper {
    long countByExample(CmsMailExample example);

    int deleteByExample(CmsMailExample example);

    int deleteByPrimaryKey(Integer mailId);

    int insert(CmsMail record);

    int insertSelective(CmsMail record);

    List<CmsMail> selectByExample(CmsMailExample example);

    CmsMail selectByPrimaryKey(Integer mailId);

    int updateByExampleSelective(@Param("record") CmsMail record, @Param("example") CmsMailExample example);

    int updateByExample(@Param("record") CmsMail record, @Param("example") CmsMailExample example);

    int updateByPrimaryKeySelective(CmsMail record);

    int updateByPrimaryKey(CmsMail record);
}