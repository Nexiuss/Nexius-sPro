/**
 * FileName: test
 * Author:   Administrator
 * Date:     2018/7/20 13:46
 * Description:
 */

public class test
{
    public static void main(String[] args) {
        int x =0;
        for(int i =1; ;i++)
        {

            x = x+3;
            if(x >= 10)
            {
                System.out.println(i);
                break;
            }
            x = x-2;
        }
    }
}
