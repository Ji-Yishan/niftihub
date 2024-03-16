package com.example.niftihub.ws;


import com.example.niftihub.config.WebSocketGetHttpSessionConfig;
import com.example.niftihub.ws.pojo.OnlineUsers;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@ServerEndpoint(value = "/privateChat",configurator = WebSocketGetHttpSessionConfig.class)
@Component
@Slf4j
public class PrivateChatEndpoint {
    private HttpSession httpSession;

    //ws连接，需要把用户放到在线用户中，然后读取数据库，将未发送的消息进行发送
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        int userDid = (int)this.httpSession.getAttribute("uid");


    }
    //关闭连接，需要将用户从在线用户中删除
    @OnClose
    private void onClose(Session session){

    }
    //发送消息，如果对方不在线，则只保存在数据库，不发送
    @OnMessage
    private void onMessage(String message){

    }
    //异常
    @OnError
    private void onError(WebSocketSession session, Throwable exception) throws Exception{
        log.info("出现异常");
        if(session.isOpen()){
            session.close();
        }
        //去除用户
        OnlineUsers.remove(session.getId());
    }


}
