import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * FileName: LocalAddress
 * Author:   Administrator
 * Date:     2018/7/7 15:42
 * Description:
 */

public class LocalAddress
{
    public static void main(String[] args) {
     /*   InetAddress address = null;//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAddress = address.getHostAddress();
        System.out.println(hostAddress);*/

        String str = "http://172.17.60.112:18080/virtualFilePath/upgrade/FV-IPC-85xxD_G_G_G_2.19.4.0.R15317.20180619(alpha2)_all.upf";
        int index = str.indexOf("/upgrade");

        String addr = "http://172.17.60.112:18080";

        String substr = addr + str.substring(str.indexOf("/upgrade"));
        System.out.println(substr);
    }
}
