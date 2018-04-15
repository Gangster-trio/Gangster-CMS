package com.ganster.cms.core.mapper;

import com.ganster.cms.core.pojo.Skin;
import com.ganster.cms.core.pojo.SkinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkinMapper {
    long countByExample(SkinExample example);

    int deleteByExample(SkinExample example);

    int deleteByPrimaryKey(String skinName);

    int insert(Skin record);

    int insertSelective(Skin record);

    List<Skin> selectByExample(SkinExample example);

    Skin selectByPrimaryKey(String skinName);

    int updateByExampleSelective(@Param("record") Skin record, @Param("example") SkinExample example);

    int updateByExample(@Param("record") Skin record, @Param("example") SkinExample example);

    int updateByPrimaryKeySelective(Skin record);

    int updateByPrimaryKey(Skin record);
}