package config;

import com.jfinal.config.Routes;
import controller.BackController;
import controller.FrontController;
import controller.HelloController;

/*
 * @author p78o2
 * @date 2020/11/6
 */
//路由拆分类
public class BlankRoutes extends Routes {
    @Override
    public void config() {
        //        添加controller
        add("/blank/hello", HelloController.class);
//        后台接口开头 /admin
        add("/admin/blank/test", BackController.class);
//        前端接口开头 /api
        add("/api/blank/test", FrontController.class);
    }
}
