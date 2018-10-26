import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileName: SocketBind
 * Author:   Administrator
 * Date:     2018/8/9 11:45
 * Description:
 */

public class SocketBind
{
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(61616);
        serverSocket.accept();
        System.out.println(11111);
        while (true)
        {
            Thread.sleep(10000);
            System.out.println("--------bondary-----");
        }
    }
}
