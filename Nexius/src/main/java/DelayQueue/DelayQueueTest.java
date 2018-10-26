/**
 * FileName: DelayQueueTest
 * Author:   Administrator
 * Date:     2018/7/23 11:41
 * Description: 延时队列
 */
package DelayQueue;

import java.util.concurrent.DelayQueue;

public class DelayQueueTest {
    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
        // 添加延时消息,m1 延时5s
        Message m1 = new Message(2, "7s -m1", 7000);
        // 添加延时消息,m2 延时3s
        Message m2 = new Message(3, "10s -m2", 10000);
        Message m3 = new Message(4, "3s -m3", 3000);
        Message m4= new Message(1, "3s -m3", 3000);
        queue.offer(m2);
        queue.offer(m1);
        queue.offer(m3);
        queue.offer(m4);
        // 启动消费线程
        new Thread(new Consumer(queue)).start();
    }
}
