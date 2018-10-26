/**
 * FileName: Consumer
 * Author:   Administrator
 * Date:     2018/7/23 11:44
 * Description: 队列消费者
 */
package DelayQueue;

import java.util.concurrent.DelayQueue;

public class Consumer implements Runnable {

    // 延时队列
    private DelayQueue<Message> queue;

    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }


    public void run() {
        while (true) {
            try {
                Message take = queue.take();
                System.out.println("消费消息：" + take.getId() + ":" + take.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
