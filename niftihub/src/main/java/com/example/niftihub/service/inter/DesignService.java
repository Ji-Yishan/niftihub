package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.DesignDO;
import com.example.niftihub.pojo.dto.DesignDTO;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 19:39
 **/
public interface DesignService {
    List<DesignDTO> selectMyDesign(int uid);
    DesignDTO selectDesignById(int did);
    int updateDesign(DesignDO designDO);
    int deleteDesign(int did);
    List<DesignDO> selectMyCollection(int uid);
    List<DesignDTO> selectDesignByname(String name);
    List<DesignDTO> selectAll();
    int addDesign(DesignDO designDO);
}
