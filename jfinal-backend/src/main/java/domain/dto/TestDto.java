package domain.dto;/*
 * @author p78o2
 * @date 2020/5/26
 */


import java.io.Serializable;
import java.util.Date;

public class TestDto implements Serializable {
    private int num;
    private String name;
    private Date createTime;

    @Override
    public String toString() {
        return "TestDto{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public TestDto() {
    }

    public TestDto(int num, String name, Date createTime) {
        this.num = num;
        this.name = name;
        this.createTime = createTime;
    }
}
