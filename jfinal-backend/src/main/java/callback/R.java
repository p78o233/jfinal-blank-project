package callback;/*
 * @author p78o2
 * @date 2020/5/25
 */

public class R {
    private boolean ret;
    private int state;
    private Object data;
    private String message;

    @Override
    public String toString() {
        return "R{" +
                "ret=" + ret +
                ", state=" + state +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public R() {
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public R(boolean ret, int state, Object data, String message) {
        this.ret = ret;
        this.state = state;
        this.data = data;
        this.message = message;
    }
}
