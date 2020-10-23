package websocket;/*
 * @author p78o2
 * @date 2020/6/4
 */

import com.jfinal.core.Controller;

import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

//测试推送图片
@ServerEndpoint("/imagePush")
public class ImageTest extends Controller {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<ImageTest> webSocketSet = new CopyOnWriteArraySet<ImageTest>();
}
