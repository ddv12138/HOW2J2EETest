package Servlets.WebSocket;

import FileUploadUtil.FIleUploadStatus;
import FileUploadUtil.FileUploadSteteCollection;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/fileUploadState", configurator = GetHttpSessionConfigurator.class)
public class FileUploadWebSocket {

    private boolean isDone;
    private HttpSession httpSession;

    @OnOpen
    public void Open(Session session, EndpointConfig config) {
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        System.out.println("webSocket连接成功");
    }

    @OnMessage
    public void Message(String filename, Session session) {
        try {
            while (!isDone) {
                String sessionid = this.httpSession.getId();
                if (FileUploadSteteCollection.size() > 0) {
                    FIleUploadStatus fus = FileUploadSteteCollection.get(sessionid, filename);
                    if (null != fus) {
                        System.out.println("websocket发送");
                        session.getBasicRemote().sendText(JSON.toJSONString(fus));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        this.isDone = true;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
