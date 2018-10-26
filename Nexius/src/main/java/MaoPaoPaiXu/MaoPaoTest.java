/**
 * FileName: MaoPaoTest
 * Author:   Administrator
 * Date:     2018/9/10 9:31
 * Description:
 */
package MaoPaoPaiXu;

import java.util.HashSet;
import java.util.Set;

public class MaoPaoTest
{
    public static int getChannelNo(Set<Integer> chanelnoList)
    {
        Integer[] array = new Integer[chanelnoList.size()];
        array = chanelnoList.toArray(array);

        int t = 0;
        for (int i = 0; i < array.length - 1; i++){
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
        int res = -1;
        for (int i = 0; i < array.length; i++)
        {
           if(array[i] != i+1)
           {
               res = i+1;
               break;
           }
        }
        if(res == -1)
        {
            res = array[array.length-1] + 1;
        }

        chanelnoList.add(res);
        return res;
    }
    public static void main(String[] args) {

        Set <Integer> chanelnoList = new HashSet<>();
        chanelnoList.add(6);


        int channelNo1 = getChannelNo(chanelnoList);
        int channelNo2 = getChannelNo(chanelnoList);
        System.out.println(channelNo1);
        System.out.println(channelNo2);

    }
}
