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
import domain.po.Wl_Channel_Consumer;
import handler.WebSocketHandler;
import interceptor.ClassInterceptor;
import interceptor.HeaderInterceptor;
import interceptor.MethodInterceptor;
import interceptor.MethodInterceptorSecond;

public class Config extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {

    }

    @Override
    public void configRoute(Routes routes) {
//        路由配置使用路由拆分配置的方式
        routes.add(new BlankRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
//        数据库配置
//        要自己添加maven依赖才能用
        DruidPlugin dp = new DruidPlugin("jdbc:mysql://127.0.0.1:3306/oa?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "root");
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
//        引入sql文件，用于动态sql语句
        arp.addSqlTemplate("templets/hello.sql");
        arp.addSqlTemplate("templets/more.sql");
        plugins.add(arp);
//        添加对应的数据表
        arp.addMapping("user", User.class);
        arp.addMapping("test", Test.class);
        arp.addMapping("testc", Testc.class);
        arp.addMapping("wl_channel_consumer", Wl_Channel_Consumer.class);
//        显示执行的sql
        arp.setShowSql(true);
        plugins.add(arp);

//        定时任务配置
//        他妈的要自己引入maven包，牛逼得不行这个框架，详细见jfinal-backend/pom.xml
//        引入任务，这种表达式叫corn4j 表达式，只有5个参数，详细见http://www.jfinal.com/share/1414
//        Cron4jPlugin cp = new Cron4jPlugin();
//        cp.addTask("* * * * *", new MyTask());
//        plugins.add(cp);

//        用于缓存Hello测试模块的redis服务
//        要自己添加maven依赖才能用，详细见jfinal-backend/pom.xml，redis数据库需要自己启动一个
//        使用redis之前需要引入缓存插件
//        plugins.add(new EhCachePlugin());
//        第一个参数缓存对象名，第二个参数redis服务器ip，第三个参数redis密码
//        RedisPlugin redis = new RedisPlugin("hello", "127.0.0.1","123456");
//        plugins.add(redis);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
//        全局拦截器
        interceptors.add(new HeaderInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {
//        简单的服务器推送websockect例子
        handlers.add(new WebSocketHandler("/websocket"));
//        单聊例子
        handlers.add(new WebSocketHandler("/SingleTest"));
//        群聊例子
        handlers.add(new WebSocketHandler("/GroupTest"));
    }
}
