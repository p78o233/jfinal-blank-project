package controller;/*
 * @author p78o2
 * @date 2020/5/21
 */
import callback.R;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.json.FastJson;
import com.jfinal.upload.UploadFile;
import domain.dto.TestDto;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HelloController extends Controller{
    public void index() {
        renderText("你好");
    }

    //    返回统一格式的json数据
    public void sameJson(){
        renderJson(new R(true,200,"",""));
    }

    //    接收get方法 url参数
    public void getUrlParam(){
        String title = getPara("title");
        renderJson(new R(true,200,title,""));
    }

//    接收post请求rest模式,单个元素形式
    public void postFormParamRest(){
//        参数获取从0开始参数之间用 - 分隔
        String title = getPara(0);
        renderJson(new R(true,200,title,""));
    }


//    接收post application/json
    public void postJsonParamObject(){
        String json = getRawData();
        TestDto dto = JSONObject.parseObject(json,TestDto.class);
        renderJson(new R(true,200,dto,""));
    }

//    获取header数据
    public void getHeaderParam(){
        String title = getHeader("title");
        renderJson(new R(true,200,title,""));
    }

//    文件上传
    public void uploadFild(){
//        文件上传路径是 项目文件webapp/upload
        try {
            UploadFile file = getFile();
            System.out.println("--------file--------");
            File delfile = new File(file.getUploadPath()+"\\"+file.getFileName());
            System.out.println("=========="+delfile.getPath());
            Map<String ,String> map = new HashMap<String, String>();
            map.put("filePath", delfile.getPath());
            map.put("fileSize", delfile.length()/1024+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderJson(new R(true,200,"",""));
    }

//    控制请求方式
    @Before(GET.class)
    public void getRequestMethod(){
        renderJson(new R(true,200,"只能通过get方式请求",""));
    }
    @Before(POST.class)
    public void postRequestMethod(){
        renderJson(new R(true,200,"只能通过post方式请求",""));
    }
}
