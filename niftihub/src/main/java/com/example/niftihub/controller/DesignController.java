package com.example.niftihub.controller;

import com.example.niftihub.common.Result;
import com.example.niftihub.pojo.data.DesignDO;
import com.example.niftihub.pojo.dto.DesignDTO;
import com.example.niftihub.service.inter.DesignService;
import com.example.niftihub.uitl.FileUploadUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-17 01:02
 **/
@RestController
public class DesignController {
    @Autowired
    DesignService designService;
    @Autowired
    Result result;
    @GetMapping("/{uid}/design/all")
    public List<DesignDTO> getAllDesign(@PathVariable int uid){
        return designService.selectMyDesign(uid);
    }

    @GetMapping("/{uid}/design/{did}")
    public DesignDTO getDesignById(@PathVariable int uid,@PathVariable int did){
        DesignDTO dto=designService.selectDesignById(did);
        if(dto.getUid()==uid){
            return dto;
        }
//        todo 应该有个exception
        return new DesignDTO();
    }

    @PutMapping("/{uid}/design/{did}")
    public String updateDesign(@RequestParam MultipartFile pic,@RequestParam String name,@PathVariable int did,
                               @RequestParam String info,@RequestParam String brand,@RequestParam int price,
                               @RequestParam String describe,@RequestParam String right,@RequestParam int amount,
                               HttpServletRequest request,@PathVariable int uid) throws JsonProcessingException {
        Date date = new Date();
        long time1 = date.getTime();
        String time=time1+"";
        String picture= FileUploadUtils.fileUtil(pic,request);
        DesignDO dto=new DesignDO(did,name,price,info,brand,picture,time,describe,right,amount,uid);
        designService.updateDesign(dto);
        return result.gainPatchSuccess();
    }
    @DeleteMapping("/{uid}/design/{did}")
    public String deleteDesign(@PathVariable int uid,@PathVariable int did) throws JsonProcessingException {
//        todo 用uid的验证是否本人删除？
        if(uid==designService.selectDesignById(did).getUid()){
            designService.deleteDesign(did);
            return result.gainDeleteSuccess();
        }
//        todo 应该有个exception
        return null;
    }

    @GetMapping("/{uid}/collection/all")
    public List<DesignDO> getAllCollection(@PathVariable int uid){
        return designService.selectMyCollection(uid);
    }
    @GetMapping("/collection/{name}")
    public List<DesignDTO> getCollectionByName(@PathVariable String name){
        return designService.selectDesignByname(name);
    }
}
