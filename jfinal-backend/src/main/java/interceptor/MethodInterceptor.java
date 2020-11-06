package interceptor;
/*
 * @author p78o2
 * @date 2020/11/6
 */

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletRequest;

public class MethodInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        HttpServletRequest request = controller.getRequest();
        if(!request.getMethod().equals("OPTIONS")) {
            System.out.println("method1拦截器进入");
            invocation.invoke();
        }
    }
}
