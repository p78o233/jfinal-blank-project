package config;/*
 * @author p78o2
 * @date 2020/5/21
 */

import com.jfinal.config.*;
import com.jfinal.template.Engine;
import controller.BackController;
import controller.FrontController;
import controller.HelloController;

public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/blank/hello", HelloController.class);
        routes.add("/blank/admin/test", BackController.class);
        routes.add("/blank/api/test", FrontController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
