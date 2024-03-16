package com.example.niftihub.ws.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GroupMessage {
    private String toUid;
    private String message;
    private String groupId;
}
