package com.example.niftihub.pojo.data.before;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 17:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDO {
    private int cid;
    @JsonIgnore
    private int pid;
    @JsonIgnore
    private int uid;
    private String content;
    private String date;

    public CommentDO(int pid, int uid, String content, String date) {
        this.pid = pid;
        this.uid = uid;
        this.content = content;
        this.date = date;
    }
}
