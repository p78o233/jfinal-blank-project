package websocket;/*
 * @author p78o2
 * @date 2020/6/3
 */

import com.alibaba.fastjson.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//群聊demo
//两个个参数，第一个自己的id，第二个房间号
@ServerEndpoint("/GroupTest/{fromUserId}/{roomId}")
public class GroupTest {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<GroupTest> webSocketSet = new CopyOnWriteArraySet<GroupTest>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String fromUserId;
    private String roomId;
    public GroupTest() {
        System.out.println(" WebSocket init~~~");
    }
    @OnOpen
    public void onOpen(@PathParam("fromUserId")String fromUserId,  @PathParam("roomId")String roomId, Session session) {
        System.out.println("onOpen");
        this.session = session;
        this.fromUserId = fromUserId;
        this.roomId = roomId;
        webSocketSet.add(this);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose");
//        元素移出集合
        for(GroupTest item : webSocketSet){
            if(this.fromUserId.equals(item.fromUserId)){
                webSocketSet.remove(item);
            }
        }
    }

//  @OnError
//  public void onError(Session session) {
//      System.out.println("onError");
//  }

    @OnMessage
    public void onMessage(String requestJson, Session session)
            throws IOException {

        for(GroupTest item : webSocketSet){
            if(this.roomId.equals(item.roomId)){
                JSONObject sendMessage = new JSONObject();
                sendMessage.put("message",requestJson);
                if(this.fromUserId.equals(item.fromUserId)){
                    sendMessage.put("self",true);
                }else{
                    sendMessage.put("self",false);
                }
                item.session.getBasicRemote().sendText(sendMessage.toJSONString());
            }
        }
    }
}
