package controller;/*
 * @author p78o2
 * @date 2020/5/21
 */

import callback.R;
import com.jfinal.core.Controller;



public class BackController extends Controller {
    public void index() {
//        测试接口
        renderText("管理平台接口");
    }

//    返回统一格式的json数据
    public void sameJson(){
        renderJson(new R(true,200,"",""));
    }
}
