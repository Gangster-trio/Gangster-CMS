package com.ganster.cms.core.dao.mapper;

import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteMapper {
    long countByExample(SiteExample example);

    int deleteByExample(SiteExample example);

    int deleteByPrimaryKey(Integer siteId);

    int insert(Site record);

    int insertSelective(Site record);

    List<Site> selectByExample(SiteExample example);

    Site selectByPrimaryKey(Integer siteId);

    int updateByExampleSelective(@Param("record") Site record, @Param("example") SiteExample example);

    int updateByExample(@Param("record") Site record, @Param("example") SiteExample example);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
}