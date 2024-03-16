package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.DesignDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 01:33
 **/
@SpringBootTest
public class DesignMapperTest {
    @Autowired
    DesignMapper designMapper;
    @Test
    public void addDesign(){
        DesignDO designDO=new DesignDO(1,"1",1
                ,"1","1","1","2023-3-15","1","1",1,3);
        System.out.println(designMapper.addDesign(designDO));
    }
    @Test
    public void selectMyDesign(){
        List<DesignDO> designDOS=designMapper.selectMyDesign(1);
        for(DesignDO d:designDOS){
            System.out.println(d);
        }
    }
    @Test
    public void selectDesignById(){
        System.out.println(designMapper.selectDesignById(1));
    }
    @Test
    public void updateDesign(){
        DesignDO designDO=new DesignDO(2,"12341234",1
                ,"1","1","1","2023-3-15","1","1",1,3,1);
        System.out.println(designMapper.updateDesign(designDO));
    }
    @Test
    public void deleteDesign(){
        System.out.println(designMapper.deleteDesign(1));
    }
    @Test
    public void selectDesignByname(){
        List<DesignDO> designDOS=designMapper.selectDesignByname("%1%");
        for(DesignDO d:designDOS){
            System.out.println(d);
        }
    }
    @Test
    public void selectAll(){
        List<DesignDO> designDOS=designMapper.selectAll();
        for(DesignDO d:designDOS){
            System.out.println(d);
        }
    }
}
