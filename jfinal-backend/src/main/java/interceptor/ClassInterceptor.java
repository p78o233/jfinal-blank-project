package interceptor;
/*
 * @author p78o2
 * @date 2020/11/6
 */

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletRequest;

public class ClassInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpServletRequest request = controller.getRequest();
        if(!request.getMethod().equals("OPTIONS")) {
            System.out.println("class拦截器进入");
            String methodName = invocation.getMethodName();
            System.out.println("调用的方法名" + methodName);
            invocation.invoke();
            System.out.println("class拦截器结束");
        }
    }
}
