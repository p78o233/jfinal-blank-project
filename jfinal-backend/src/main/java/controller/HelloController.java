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
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.upload.UploadFile;
import domain.dto.TestDto;
import domain.po.Test;
import domain.po.Testc;
import interceptor.MethodInterceptor;
import interceptor.MethodInterceptorSecond;

import java.io.File;
import java.util.*;
//conbtroller类级别拦截器，类内每个方法都会被拦截
//@Before(ClassInterceptor.class)
public class HelloController extends Controller {
    public void index() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        renderText("你好");
    }

    //    返回统一格式的json数据
    public void sameJson() {
        renderJson(new R(true, 200, "", ""));
    }

    //    接收get方法 url参数
    public void getUrlParam() {
        String title = getPara("title");
        renderJson(new R(true, 200, title, ""));
    }

    //    接收post请求rest模式,单个元素形式
    public void postFormParamRest() {
//        参数获取从0开始参数之间用 - 分隔
        String title = getPara(0);
        renderJson(new R(true, 200, title, ""));
    }

    //    获取form表单数据
//    无文件上传方式，只有普通字段  "Content-Type", "application/x-www-form-urlencoded"
    public void getFormData() {
        TestDto test = getBean(TestDto.class, "");
        renderJson(new R(true, 200, test, ""));
    }

    //    获取from表单，有字段参数，且有文件
//    "content-type", "multipart/form-data;
    public void getFileAndData() {
        try {
            UploadFile file = getFile();
//            使用from-data方式接收数据时要先使用getfile()方法
            TestDto test = getBean(TestDto.class, "");
            System.out.println("--------file--------" + test.toString());
            if (file != null) {
                File delfile = new File(file.getUploadPath() + "\\" + file.getFileName());
                System.out.println("==========" + delfile.getPath());
                Map<String, String> map = new HashMap<String, String>();
                map.put("filePath", delfile.getPath());
                map.put("fileSize", delfile.length() / 1024 + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderJson(new R(true, 200, "", ""));
    }


    //    接收post application/json
    public void postJsonParamObject() {
        String json = getRawData();
        TestDto dto = JSONObject.parseObject(json, TestDto.class);
        renderJson(new R(true, 200, dto, ""));
    }

    //    获取header数据
    public void getHeaderParam() {
        String title = getHeader("title");
        renderJson(new R(true, 200, title, ""));
    }

    //    单个文件上传
    public void uploadFile() {
//        文件上传路径是 项目文件webapp/upload
        try {
            UploadFile file = getFile();
            System.out.println("--------file--------");
            File delfile = new File(file.getUploadPath() + "\\" + file.getFileName());
            System.out.println("==========" + delfile.getPath());
            Map<String, String> map = new HashMap<String, String>();
            map.put("filePath", delfile.getPath());
            map.put("fileSize", delfile.length() / 1024 + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderJson(new R(true, 200, "", ""));
    }

    //    多个文件上传
    public void uploadFiles() {
        List<UploadFile> files = getFiles();
        try {
            for (int i = 0; i < files.size(); i++) {
                File delfile = new File(files.get(i).getUploadPath() + "\\" + files.get(i).getFileName());
                System.out.println("==========" + delfile.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderJson(new R(true, 200, "", ""));
    }

    //    控制请求方式
    @Before(GET.class)
    public void getRequestMethod() {
        renderJson(new R(true, 200, "只能通过get方式请求", ""));
    }

    @Before(POST.class)
    public void postRequestMethod() {
        renderJson(new R(true, 200, "只能通过post方式请求", ""));
    }

    //    数据库操作
//    查询
    public void queryParam() {
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find("select * from test");
        renderJson(new R(true, 200, tests, ""));
    }

    //    分页查询
    public void queryPage() {
//        第一个参数当前页码 第二个参数分页大小
//        Page<Test> testPage = Test.dao.paginate(1, 1, "select * ", " from test");
//        带参数，排序分页查询,查询条件用?做占位符
        Page<Test> testPage = Test.dao.paginate(1, 10, "select * ", " from test where name = ? order by id desc", "123");
        renderJson(new R(true, 200, testPage, ""));
    }

    //    新增
    public void insertTest() {
        String json = getRawData();
        Test test = JSONObject.parseObject(json, Test.class);
//        一定要有getset方法的做法
        Test dto = new Test();
        boolean result = dto.set("name", test.getName()).set("cdNum", test.getCdNum())
                .set("createTime", test.getCreateTime()).set("score", test.getScore()).save();
//        System.out.println("主键"+dto.getInt("id"));
        renderJson(new R(true, 200, result, ""));
    }

    //    sql批量插入
    public void sqlBacthInsertFun() {
        List<Test> tests = new ArrayList<>();
        Test r1 = new Test();
        r1.set("name", "p78o2");
        r1.set("cdNum", 123);
        r1.set("createTime", new Date());
        r1.set("score", 0.5f);
        tests.add(r1);
        Test r2 = new Test();
        r2.set("name", "p78o3");
        r2.set("cdNum", 987);
        r2.set("createTime", new Date());
        r2.set("score", 0.3f);
        tests.add(r2);
//        以下设置对象是不行的，插入的只会是null，一定要用上面的方法
        Test t3 = new Test();
        t3.setCdNum(654);
        t3.setName("p78o4");
        t3.setCreateTime(new Date());
        t3.setScore(0.7f);
        tests.add(t3);
        String sql = "insert into test (name,cdNum,createTime,score) values (?,?,?,?)";
        int[] result = Db.batch(sql, "name,cdNum,createTime,score", tests, 500);
        renderJson(new R(true, 200, result, ""));
    }

    //    修改
    public void updateTest() {
        int id = getParaToInt(0);
//        单条件更新
        long start = System.currentTimeMillis();
//        多运行几次之后其实差别不大，还是按照jfinal的最简单写法编写，就是第一种
//        161ms
        boolean result = Test.dao.findById(id).set("score", 0.5).update();
//        152ms
//        int result = Db.update("update test set score = 0.5 where id = ? limit 1",id);
//        85ms
//        int result = Db.update("update test set score = 0.5 where id = ?",id);
//        多条件更新
//        int result = Db.update("update test set score = ? where name = ? and score = ?",0.5f,"123","6");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        renderJson(new R(true, 200, result, ""));
    }

    //    删除
    public void deleteTest() {
        int id = getParaToInt(0);
        boolean result = Test.dao.deleteById(id);
        renderJson(new R(true, 200, result, ""));
    }

    //    关联查询
    public void getContactList() {
//        查询test表的list
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find("select * from test");
        for (int i = 0; i < tests.size(); i++) {
            List<Testc> testcs = new ArrayList<Testc>();
//            查询关联test 的 testc
            testcs = new Test().getTestc(tests.get(i).getInt("id"));
//            查询结果放到一个map<String,Object> 里面
            Map testc = new HashMap();
            testc.put("testcs", testcs);
//            然后再set到test对应字段上面
            tests.get(i).setColumns(testc);
        }
        renderJson(new R(true, 200, tests, ""));
    }

    //    事务管理
    @Before(Tx.class)
    public void tran() {
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
            renderJson(new R(true, 200, resultTarn, ""));
        } catch (Exception e) {
            System.out.println(e);
            renderJson(new R(false, 500, e, ""));
        }

    }

    //    sql文件指令
    public void sqlEnjoy() {
        //查询只需要sql模板名字，和放在哪个文件无关
//        String sql = Db.getSql("findTest");
//        与上面的放在不同的文件夹里面，但是也是直接调用，所以最好加上namespace避免重名
//        String sql = Db.getSql("findTestMore");
//        使用namespace
        String sql = Db.getSql("more.findTestNameSpace");
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find(sql);
        renderJson(new R(true, 200, tests, ""));
    }
//    验证一个namespace是否能多个指令
    public void sqlEnjoyMutil(){
        String sql = Db.getSql("more.findTestMore");
        List<Record> tests = new ArrayList<Record>();
        tests = Db.find(sql);
        renderJson(new R(true, 200, tests, ""));
    }
    //    sql文件指令参数
    public void sqlEnjoyParam() {
        // 构造参数
        Kv cond = Kv.by("id", 13).set("cdNum", 1);
        List<Record> tests = new ArrayList<Record>();
        tests = Db.template("findByParamTest", cond).find();
        renderJson(new R(true, 200, tests, ""));
    }

    //    sql文件指令分页查询
    public void sqlEnjoyPage() {
        Kv cond = Kv.by("score", 0.5);
        Page tests = Db.template("findByParamPage", cond).paginate(1, 10);
        renderJson(new R(true, 200, tests, ""));
    }

    //    sql文件指令like
    public void sqlEnjoyLike() {
        Kv cond = Kv.by("name", "吴");
        List<Record> tests = new ArrayList<Record>();
        tests = Db.template("findByLike", cond).find();
        ;
        renderJson(new R(true, 200, tests, ""));
    }

    //    sql文件指令动态sql
    public void sqlParamNull() {
        Kv cond = Kv.by("id", 13).set("cdNum", 1).set("name", "六");
        List<Record> tests = new ArrayList<Record>();
        tests = Db.template("findTestNull", cond).find();
        ;
        renderJson(new R(true, 200, tests, ""));
    }

    //    redis设置值
    public void setRedisParam() {
//        获取在config配置的reids对象
        Cache helloCache = Redis.use("hello");
//        没有设置过期时间
        helloCache.set("testHello", "hello");
//        有设置过期时间,单位秒
        helloCache.setex("testHelloTime", 10, "helloTime");
        renderJson(new R(true, 200, true, ""));
    }

    //    redis获取值
    public void getRedisParam() {
//        获取在config配置的reids对象
        Cache helloCache = Redis.use("hello");
        JSONObject obj = new JSONObject();
        obj.put("testHello", helloCache.get("testHello"));
        obj.put("testHelloTime", helloCache.get("testHelloTime"));
        renderJson(new R(true, 200, obj, ""));
    }

//    默认缓存
    public void setCache(){
        CacheKit.put("hello", "test", "缓存测试");
        renderJson(new R(true, 200, null, "缓存保存成功"));
    }
    public void getCache(){
        String value = CacheKit.get("hello", "test");
        renderJson(new R(true, 200, value, "缓存取出成功"));
    }



//    方法拦截器，只拦截本方法
//    先写的拦截器先执行
    @Before({MethodInterceptor.class, MethodInterceptorSecond.class})
    public void methodInterceptor(){
        renderJson(new R(true, 200, "拦截器测试", ""));
    }
}
