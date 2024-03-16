package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.DesignDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-15 15:19
 **/
@Mapper
public interface DesignMapper {
    List<DesignDO> selectMyDesign(int uid);
    DesignDO selectDesignById(int did);
    int updateDesign(DesignDO designDO);
    int deleteDesign(int did);
    List<DesignDO> selectDesignByname(String name);
    List<DesignDO> selectAll();
    int addDesign(DesignDO designDO);
}
