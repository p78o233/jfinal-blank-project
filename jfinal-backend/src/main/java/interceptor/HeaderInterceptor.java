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

public class HeaderInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        HttpServletRequest request = invocation.getController().getRequest();
        String servletPath = request.getServletPath();
        String [] path = servletPath.split("/");
        System.out.println("访问路径"+servletPath);
//        只拦截后台端口
        if(path[1].equals("admin")){
            //拦截检查
            System.out.println("拦截检查");
            PrintWriter writer = null;
            HttpServletResponse response = invocation.getController().getResponse();
//            有中文的一定要设置返回的字符编码
            response.setCharacterEncoding("UTF-8");
            try {
                writer = response.getWriter();
                R r = new R(false,500,"拦截成功","");
                JSONObject callBackResut = JSONObject.parseObject(JSON.toJSONString(r));
                writer.print(callBackResut);
            }catch (IOException e){
                System.out.println(e.toString());
            }finally {
                if (writer != null)
                    writer.close();
            }

        }else {
//        继续余下的拦截器与目标方法
            invocation.invoke();
        }
    }
}
