package controller;/*
 * @author p78o2
 * @date 2020/5/21
 */

import com.jfinal.core.Controller;

public class FrontController extends Controller {
    public void index() {
        renderText("前端接口");
    }
}
