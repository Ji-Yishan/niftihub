package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.CollectionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-17 00:36
 **/
@Mapper
public interface CollectionMapper {
    List<CollectionDO> selectCollection(int uid);
    int insertCollection(CollectionDO collectionDO);
    int deleteCollection(CollectionDO collectionDO);

}
