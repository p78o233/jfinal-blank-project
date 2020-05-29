package controller;/*
 * @author p78o2
 * @date 2020/5/21
 */
import callback.R;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import domain.dto.TestDto;
import domain.po.Test;
import domain.po.Testc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloController extends Controller{
    public void index() {
        try { Thread.sleep (5000) ;
        } catch (InterruptedException ie){}
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

//    数据库操作
//    查询
    public void queryParam(){
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find("select * from test");
        renderJson(new R(true,200,tests,""));
    }
//    分页查询
    public void queryPage(){
//        第一个参数当前页码 第二个参数分页大小
        Page<Test> testPage = Test.dao.paginate(1,1,"select * "," from test");
        renderJson(new R(true,200,testPage,""));
    }
//    新增
    public void insertTest(){
        String json = getRawData();
        Test test = JSONObject.parseObject(json,Test.class);
//        一定要有getset方法的做法
        Test dto = new Test();
        boolean result = dto.set("name",test.getName()).set("cdNum",test.getCdNum())
                .set("createTime",test.getCreateTime()).set("score",test.getScore()).save();
//        System.out.println("主键"+dto.getInt("id"));
        renderJson(new R(true,200,result,""));
    }
//    修改
    public void updateTest(){
        int id = getParaToInt(0);
        boolean result = Test.dao.findById(id).set("score",0.5).update();
        renderJson(new R(true,200,result,""));
    }
//    删除
    public void deleteTest(){
        int id = getParaToInt(0);
        boolean result = Test.dao.deleteById(id);
        renderJson(new R(true,200,result,""));
    }
//    关联查询
    public void getContactList(){
//        查询test表的list
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find("select * from test");
        for(int i=0;i<tests.size();i++) {
            List<Testc> testcs = new ArrayList<Testc>();
//            查询关联test 的 testc
            testcs = new Test().getTestc(tests.get(i).getInt("id"));
//            查询结果放到一个map<String,Object> 里面
            Map testc = new HashMap();
            testc.put("testcs",testcs);
//            然后再set到test对应字段上面
            tests.get(i).setColumns(testc);
        }
        renderJson(new R(true,200,tests,""));
    }

//    事务管理
    @Before(Tx.class)
    public void tran(){
//        MySql数据库表必须设置为InnoDB引擎时才支持事务，MyISAM并不支持事务。
        try {
            boolean resultTarn = Db.tx(() -> {
                try {
                    int id = getParaToInt(0);
//                boolean result1 = Test.dao.findById(id).set("score", 0.5).update();
//                    注意做更新是的时候不能用链式的更新方式只能用下面的这种
                    int restlt1 = Db.update("update test set score = 0.5 where id = ?", id);
                    int ids = getParaToInt(1);
//                boolean result2 = Test.dao.findById(ids).set("score", 0.5).update();
                    Db.update("update test set score = \"中文字符\" where id = ?", ids);
                    return true;
                } catch (Exception e) {
//                throw e;
                    return false;

                }
            });
            renderJson(new R(true,200,resultTarn,""));
        }catch (Exception e){
            System.out.println(e);
            renderJson(new R(false,500,e,""));
        }

    }
}
