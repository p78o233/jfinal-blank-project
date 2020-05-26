package domain.po;/*
 * @author p78o2
 * @date 2020/5/26
 */

import com.jfinal.plugin.activerecord.Model;

public class Testc extends Model<Testc> {
    public static final Testc dao = new Testc().dao();

    private int id;
    private int testId;

    public Test getTest() {
        return Test.dao.findById(get("testId"));
    }

    @Override
    public String toString() {
        return "Testc{" +
                "id=" + id +
                ", testId=" + testId +
                '}';
    }

    public Testc() {
    }


    public Testc(int id, int testId) {
        this.id = id;
        this.testId = testId;
    }

    public static Testc getDao() {
        return dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
