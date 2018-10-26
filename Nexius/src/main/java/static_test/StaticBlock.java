/**
 * FileName: StaticBlock
 * Author:   Administrator
 * Date:     2018/10/19 18:00
 * Description:
 */
package static_test;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;

import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Iterator;

public class StaticBlock
{
    static {
        int i = 1;
        String s = "";
        Integer integer;
        Multimap map = ArrayListMultimap.create();


        map.put(1,1);
        map.put(1,2);
        map.put(1,3);
        map.put(1,4);

        map.put(2,1);
        map.put(2,2);
        map.put(2,3);
        map.put(2,4);

        Collection collection = map.get(1);


        Iterator iterator = collection.iterator();
        while (iterator.hasNext())
        {
            Object next = iterator.next();
            System.out.println(next);
        }

    }

    public static void main(String[] args) {

        StaticBlock staticBlock = new StaticBlock();
    }
}
