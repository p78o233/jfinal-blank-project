package interceptor;/*
 * @author p78o2
 * @date 2020/5/27
 */


import callback.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

//先限流，再校验
//拦截器，做限流
//拦截器，拦截所有的网络请求校验token是否有效
public class HeaderInterceptor implements Interceptor {
//    同一时间内能进来多少个请求
    private static int limit = 100;
    private static int nowRequest = 0;
    @Override
    public void intercept(Invocation invocation)  {
        Controller controller = invocation.getController();
//        限流判断
        if(nowRequest<limit) {
           this.safeAdd();
            HttpServletRequest request = controller.getRequest();
            String servletPath = request.getServletPath();
            String[] path = servletPath.split("/");
            System.out.println("访问路径" + servletPath);
//        只拦截后台端口
            if (path[1].equals("admin")) {
                this.safeSub();
                //拦截检查
                System.out.println("拦截检查");
                PrintWriter writer = null;
                HttpServletResponse response = invocation.getController().getResponse();
//            有中文的一定要设置返回的字符编码
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                try {
                    writer = response.getWriter();
                    R r = new R(false, 500, "拦截成功", "");
                    JSONObject callBackResut = JSONObject.parseObject(JSON.toJSONString(r));
                    writer.print(callBackResut);
                } catch (IOException e) {
                    System.out.println(e.toString());
                } finally {
                    if (writer != null)
                        writer.close();
                }

            } else {
//        继续余下的拦截器与目标方法
               invocation.invoke();
                this.safeSub();
            }
        }else{
//            队列已满不做后续处理
            PrintWriter writer = null;
            HttpServletResponse response = invocation.getController().getResponse();
//            有中文的一定要设置返回的字符编码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            try {
                writer = response.getWriter();
                R r = new R(false,501,null,"请求过多请稍后重试");
                JSONObject callBackResut = JSONObject.parseObject(JSON.toJSONString(r));
                writer.print(callBackResut);
            }catch (IOException e){
                System.out.println(e.toString());
            }finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    /* 使用 锁 来实现原子操作*/
    public synchronized void safeAdd() {
        nowRequest++;
    }

    public synchronized void safeSub(){
        nowRequest--;
    }
}
