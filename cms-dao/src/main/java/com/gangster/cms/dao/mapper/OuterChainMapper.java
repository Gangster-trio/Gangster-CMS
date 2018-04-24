package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.OuterChain;
import com.gangster.cms.common.pojo.OuterChainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OuterChainMapper {
    long countByExample(OuterChainExample example);

    int deleteByExample(OuterChainExample example);

    int deleteByPrimaryKey(Integer outerchainId);

    int insert(OuterChain record);

    int insertSelective(OuterChain record);

    List<OuterChain> selectByExample(OuterChainExample example);

    OuterChain selectByPrimaryKey(Integer outerchainId);

    int updateByExampleSelective(@Param("record") OuterChain record, @Param("example") OuterChainExample example);

    int updateByExample(@Param("record") OuterChain record, @Param("example") OuterChainExample example);

    int updateByPrimaryKeySelective(OuterChain record);

    int updateByPrimaryKey(OuterChain record);
}