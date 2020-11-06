
/*
 * @author p78o2
 * @date 2020/11/6
 */

import com.jfinal.server.undertow.UndertowServer;
import config.Config;

public class App {
    public static void main(String[] args) {
        UndertowServer.start(Config.class, 8203, true);
    }
}
