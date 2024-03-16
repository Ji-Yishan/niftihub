package com.example.niftihub.pojo.data.before;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 18:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDO {
    private int pid;
    private int uid;
    private String content;
    @JsonIgnore
    private String date;
    private String picture;
    private String post_name;

    public PostDO( int uid, String content, String picture, String postName) {
        this.uid = uid;
        this.content = content;
        this.picture = picture;
        this.post_name = postName;
    }
    public PostDO( int pid,int uid, String content, String picture, String postName) {
        this.pid = pid;
        this.uid = uid;
        this.content = content;
        this.picture = picture;
        this.post_name = postName;
    }
}
