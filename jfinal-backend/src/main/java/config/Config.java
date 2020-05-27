package config;/*
 * @author p78o2
 * @date 2020/5/21
 */

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import controller.BackController;
import controller.FrontController;
import controller.HelloController;
import domain.po.Test;
import domain.po.Testc;
import domain.po.User;
import interceptor.HeaderInterceptor;

public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {

    }

    @Override
    public void configRoute(Routes routes) {
//        添加controller
        routes.add("/blank/hello", HelloController.class);
//        后台接口开头 /admin
        routes.add("/admin/blank/test", BackController.class);
//        前端接口开头 /api
        routes.add("/api/blank/test", FrontController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = new DruidPlugin("jdbc:mysql://127.0.0.1:3306/oa?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "root");
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
//        添加对应的数据表
        arp.addMapping("user", User.class);
        arp.addMapping("test", Test.class);
        arp.addMapping("testc", Testc.class);
//        显示执行的sql
        arp.setShowSql(true);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
//        拦截器
        interceptors.add(new HeaderInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
