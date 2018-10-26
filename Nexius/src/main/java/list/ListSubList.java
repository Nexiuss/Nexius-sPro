/**
 * FileName: ListSubList
 * Author:   Administrator
 * Date:     2018/10/11 9:53
 * Description:
 */
package list;

import java.util.ArrayList;
import java.util.List;

public class ListSubList
{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        String string = "12345";

        string = string.substring(1,string.length()-1);

        System.out.println(string);
        int i=0;
        for(Integer integer : list)
        {

            i++;
            if(5 == list.get(i))
            {
                System.out.println(i);
                break;
            }
        }

        list = list.subList(1,list.size());

        System.out.println(list.toString());
    }
}
