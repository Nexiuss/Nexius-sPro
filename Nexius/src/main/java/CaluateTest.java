/**
 * FileName: CaluateTest
 * Author:   Administrator
 * Date:     2018/8/17 16:45
 * Description:
 */

public class CaluateTest
{
    public static void main(String[] args) {
        int i = 81447;

        while (i > 0)
        {
            int hasAuth = i%2;
            System.out.println(hasAuth);
            i = i>>1;
        }
    }
}
