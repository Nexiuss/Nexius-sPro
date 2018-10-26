import java.util.Date;

/**
 * FileName: SwitchTest
 * Author:   Administrator
 * Date:     2018/6/29 17:59
 * Description:
 */

public class SwitchTest
{
    public static void main(String[] args) {
        long datetime = new Date().getTime();
        int i = 0;
        while (true)
        {

            i++;
            if(i == 1000)
            {
                System.out.println(new Date().getTime() - datetime);
                break;
            }
        }

        datetime = new Date().getTime();

        int j = 0;
        while (true)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
            if(j == 1000)
            {
                System.out.println(new Date().getTime() - datetime);
                break;
            }
        }
    }
}
