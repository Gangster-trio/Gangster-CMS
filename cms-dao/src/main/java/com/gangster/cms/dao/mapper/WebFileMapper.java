package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebFileMapper {
    long countByExample(WebFileExample example);

    int deleteByExample(WebFileExample example);

    int insert(WebFile record);

    int insertSelective(WebFile record);

    List<WebFile> selectByExample(WebFileExample example);

    int updateByExampleSelective(@Param("record") WebFile record, @Param("example") WebFileExample example);

    int updateByExample(@Param("record") WebFile record, @Param("example") WebFileExample example);
}