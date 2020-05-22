package controller;/*
 * @author p78o2
 * @date 2020/5/21
 */
import com.jfinal.core.Controller;
public class HelloController extends Controller{
    public void index() {
        renderText("你好");
    }
}
