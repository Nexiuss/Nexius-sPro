/**
 * FileName: ReplaceTest
 * Author:   Administrator
 * Date:     2018/8/20 10:54
 * Description:
 */
package replace;

public class ReplaceTest
{


    public static void main(String[] args) {
        String path = "D:/Program Files/AS300/WEBCLIENT/webclient/jdk1.8.0_151/jre";

        int i = 0;
        while (i < 5)
        {
            i++;
            path = path.substring(0, path.lastIndexOf("/"));
        }
        System.out.println(path);
    }
}
