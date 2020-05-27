package domain.po;/*
 * @author p78o2
 * @date 2020/5/26
 */

import com.jfinal.plugin.activerecord.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Test extends Model<Test> implements Serializable {
    public static final Test dao = new Test().dao();

    public List<Testc> getTestc(int  testId) {
        return Testc.dao.find("select * from testc where testId = ? ", testId);
    }

    private int id;
    private int cdNum;
    private String name;
    private Date createTime;
    private float score;
//    用于关联testc
    private List<Testc> testcs;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", cdNum=" + cdNum +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", score=" + score +
                ", testcs=" + testcs +
                '}';
    }

    public Test() {
    }

    public static Test getDao() {
        return dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCdNum() {
        return cdNum;
    }

    public void setCdNum(int cdNum) {
        this.cdNum = cdNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public List<Testc> getTestcs() {
        return testcs;
    }

    public void setTestcs(List<Testc> testcs) {
        this.testcs = testcs;
    }

    public Test(int id, int cdNum, String name, Date createTime, float score, List<Testc> testcs) {
        this.id = id;
        this.cdNum = cdNum;
        this.name = name;
        this.createTime = createTime;
        this.score = score;
        this.testcs = testcs;
    }
}
