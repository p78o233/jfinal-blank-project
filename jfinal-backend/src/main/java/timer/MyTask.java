package timer;/*
 * @author p78o2
 * @date 2020/6/1
 */
//定时任务执行类
public class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("定时任务执行，每分钟执行一次");
    }
}
