package com.example.niftihub.service.impl;

import com.example.niftihub.dao.CollectionMapper;
import com.example.niftihub.dao.DesignMapper;
import com.example.niftihub.pojo.data.CollectionDO;
import com.example.niftihub.pojo.data.DesignDO;
import com.example.niftihub.pojo.dto.DesignDTO;
import com.example.niftihub.service.inter.DesignService;
import com.example.niftihub.uitl.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 23:39
 **/
@Service
public class DesignServiceImpl implements DesignService {
    @Autowired
    DesignMapper designMapper;
    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public List<DesignDTO> selectMyDesign(int uid) {
        List<DesignDO> designDOS=designMapper.selectMyDesign(uid);
        return transformArry(designDOS);
    }

    @Override
    public DesignDTO selectDesignById(int did) {
        return transform(designMapper.selectDesignById(did));
    }

    @Override
    public int updateDesign(DesignDO designDO) {
        return designMapper.updateDesign(designDO);
    }

    @Override
    public int deleteDesign(int did) {
//        todo 这里应该有个验证来着，没想好
//         todo 原作者卖出去之后不能删除的逻辑还没写
        return designMapper.deleteDesign(did);
    }

    @Override
    public List<DesignDO> selectMyCollection(int uid) {
        List<DesignDO> designDOS=new ArrayList<>();
        List<CollectionDO> collectionDOS=collectionMapper.selectCollection(uid);
        for(CollectionDO c:collectionDOS){
            designDOS.add(designMapper.selectDesignById(c.getDid()));
        }
        return designDOS;
    }

    @Override
    public List<DesignDTO> selectDesignByname(String name) {
        List<DesignDO>designDOS=designMapper.selectDesignByname(name);
        return transformArry(designDOS);
    }

    @Override
    public List<DesignDTO> selectAll() {
        return transformArry(designMapper.selectAll());
    }

    @Override
    public int addDesign(DesignDO designDO) {
        String uuid= UUID.getUUID();
        designDO.setId(uuid);
        return designMapper.addDesign(designDO);
    }
    public DesignDTO transform(DesignDO d){
            DesignDTO dto=new DesignDTO(d.getDid(), d.getName(), d.getPrice(),
                    d.getInfo(), d.getBrand(), d.getPic(), d.getTime(), d.getDescribe(),
                    d.getRight(), d.getAmount());

        return dto;
    }
    public List<DesignDTO> transformArry(List<DesignDO>designDOS){
        List<DesignDTO>designDTOS=new ArrayList<>();
        for(DesignDO d:designDOS){
            designDTOS.add(transform(d));
        }
        return designDTOS;
    }
}
