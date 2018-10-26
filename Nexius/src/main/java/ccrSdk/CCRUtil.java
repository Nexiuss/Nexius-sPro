/**
 * FileName: CCRUtil
 * Author:   Administrator
 * Date:     2018/8/16 14:55
 * Description:
 */
package ccrSdk;

import com.sun.javaws.Main;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class CCRUtil
{
    public final static String ccrPath = CCRUtil.class.getResource("CCR_SDKx64.dll").getPath().replaceAll("\\\\","/").substring(1);
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(ccrPath, CLibrary.class);

        int GetCcrInfo(Pointer str, Pointer out, Pointer mfr);

        int OpenCcr(Pointer str);

        int CloseCcr();
    }


    public static void main(String[] args) {
       /* CCRUtil ccrUtil =   new CCRUtil();

        String ccrPath = CCRUtil.class.getResource("CCR_SDKx64.dll").getPath();*/
        Pointer pMac = new Memory(9);
        Pointer pMaxChannel = new Memory(4);
        Pointer pstr = new Memory(30);
        Pointer pmfr = new Memory(4);
        pstr.setString(0, "D:\\Program Files\\AS300\\ccr.db");
        CLibrary.INSTANCE.OpenCcr(pstr);
        CLibrary.INSTANCE.GetCcrInfo(pMac, pMaxChannel, pmfr);
        CLibrary.INSTANCE.CloseCcr();
        System.out.printf(pMac.getString(0));
        System.out.printf("\r\n");
        System.out.println(pMaxChannel.getInt(0));
        System.out.printf("\r\n");
        System.out.println(pmfr.getInt(0));

        System.out.printf("\r\n");
    }
}
