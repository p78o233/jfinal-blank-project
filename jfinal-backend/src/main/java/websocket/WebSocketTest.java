package websocket;/*
 * @author p78o2
 * @date 2020/6/2
 */


import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/{param}")
public class WebSocketTest extends Controller {
    public WebSocketTest() {
        System.out.println(" WebSocket init~~~");
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen");
    }

    @OnClose

    public void onClose(Session session) {
        System.out.println("onClose");
    }

//  @OnError
//  public void onError(Session session) {
//      System.out.println("onError");
//  }

    @OnMessage
    public void onMessage(String requestJson, Session session)
            throws IOException {
        requestJson = "一一小知回复：" + requestJson;
        System.out.println(requestJson);
        session.getBasicRemote().sendText(requestJson);
    }
}
