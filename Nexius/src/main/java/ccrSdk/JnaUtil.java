package ccrSdk;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class JnaUtil {

    //public static Logger logger = Logger.getLogger(JnaUtil.class);
	static CLibrary INSTANCE;
	
	static {
		String bits = System.getProperty("sun.arch.data.model");
        String ops = System.getProperty("os.name");
        //logger.info("jdk bits=" + bits);
		//logger.info("option sysetm=" + ops);
        if ( ops .startsWith("win") ||  ops .startsWith("Win") )//windows
        {
            if ("32".equals(bits)) {
               // logger.info("use CCR_SDKx32.dll");
                //INSTANCE = (CLibrary) Native.loadLibrary("CCR_SDKx32.dll", CLibrary.class);
            }
            if ("64".equals(bits)) {
               // logger.info("use CCR_SDKx64.dll");
                INSTANCE = (CLibrary) Native.loadLibrary("CCR_SDKx64.dll", CLibrary.class);
            }
        }else
        {
            if ("32".equals(bits))
            {
                //logger.info("use libCCR_SDKx64-x86_32.so");
                INSTANCE = (CLibrary) Native.loadLibrary("libCCR_SDKx64-x86_32.so", CLibrary.class);
                //INSTANCE = null;
            }
            if ("64".equals(bits))
            {
                //logger.info("use libCCR_SDKx64-x86_64.so");
                INSTANCE = (CLibrary) Native.loadLibrary("libCCR_SDKx64-x86_64.so", CLibrary.class);
                //INSTANCE = null;
            }
        }

	}
	
	public static CCrInfoBean getCCR() {

	    CCrInfoBean cCrInfoBean = new CCrInfoBean();
		Pointer pMac = new Memory(20);
		Pointer pMaxChannel = new Memory(20);
		Pointer pstr = new Memory(255);
		Pointer pmfr = new Memory(4);
		String ops = System.getProperty("os.name");
        if ( ops .startsWith("win") ||  ops .startsWith("Win") )
        {
            //logger.info("window as300 root path" +getWinAS300RootPath());
            pstr.setString(0, "D:\\Program Files\\AS300\\ccr.db");
        }else
        {
            //logger.info("linux as300 root path" +"/root/ccr/ccr.db");
            pstr.setString(0, "/root/ccr/ccr.db");
        }

        // TODO yang.xu 后面要删除。>>>>>
        if(INSTANCE == null)
        {
            Set<ManufactureTypeEnum> manufactureTypeEnums = new HashSet();
            manufactureTypeEnums.add(ManufactureTypeEnum.IPC2_0);
            manufactureTypeEnums.add(ManufactureTypeEnum.Onvif);
            manufactureTypeEnums.add(ManufactureTypeEnum.HIKVISION);
            manufactureTypeEnums.add(ManufactureTypeEnum.DAHUA);
            manufactureTypeEnums.add(ManufactureTypeEnum.CMSCLIENT);
            cCrInfoBean.setManufactureList(manufactureTypeEnums);
            cCrInfoBean.setMaxChannel(300);//默认给300路

            return cCrInfoBean;
        }
        // <<<<<<<

        try {
            INSTANCE.OpenCcr(pstr);
            // 读取ccr信息
            INSTANCE.GetCcrInfo(pMac, pMaxChannel,pmfr);
            INSTANCE.CloseCcr();
            if (checkMac(pMac.getString(0)) || true) { // mac地址合法

                cCrInfoBean.setMaxChannel(pMaxChannel.getInt(0));
                cCrInfoBean.setMac(pMac.getString(0));
                int nManufacture = pmfr.getInt(0);
                System.out.println("Read ccr manufacture Success!"+ nManufacture);
                cCrInfoBean.setManufactureList(getManufactureList(nManufacture));
                //System.out.println("Read ccr Success!"+ JSON.toJSONString(cCrInfoBean));
            } else {
                cCrInfoBean.setMaxChannel(pMaxChannel.getInt(0));
                Set<ManufactureTypeEnum> manufactureTypeEnums = new HashSet();
                manufactureTypeEnums.add(ManufactureTypeEnum.IPC2_0);
                cCrInfoBean.setManufactureList(manufactureTypeEnums);
                cCrInfoBean.setMaxChannel(300);//默认给300路
                //System.out.println("Read ccr mac not match!"+ JSON.toJSONString(cCrInfoBean));
            }
        }catch (Exception e)
        {
            Set<ManufactureTypeEnum> manufactureTypeEnums = new HashSet();
            manufactureTypeEnums.add(ManufactureTypeEnum.IPC2_0);
            cCrInfoBean.setManufactureList(manufactureTypeEnums);
            cCrInfoBean.setMaxChannel(300);//默认给300路
            //logger.error("Read ccr error! return a default CCR"+ JSON.toJSONString(cCrInfoBean));
        }


		return cCrInfoBean;
	}
	
	private static boolean checkMac(String macAddr) {
		try {
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			while (enumeration.hasMoreElements()) {
				StringBuffer stringBuffer = new StringBuffer();
				NetworkInterface networkInterface = enumeration.nextElement();
				if (networkInterface != null) {
					byte[] bytes = networkInterface.getHardwareAddress();
					if (bytes != null) {
						for (int i = 0; i < bytes.length; i++) {
							if (i != 0) {
								stringBuffer.append("-");
							}
							int tmp = bytes[i] & 0xff; // 字节转换为整数
							String str = Integer.toHexString(tmp);
							if (str.length() == 1) {
								stringBuffer.append("0" + str);
							} else {
								stringBuffer.append(str);
							}
						}
						String mac = stringBuffer.toString().toUpperCase().replace("-", "");
						if (macAddr.equals(mac)) {

							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	private static Set<ManufactureTypeEnum> getManufactureList(int nManufacture)
	{
        Set<ManufactureTypeEnum> manufactureTypeEnums = new HashSet();
        manufactureTypeEnums.add(ManufactureTypeEnum.IPC2_0);
        if (nManufacture > 0) {
            int i = 0;
            while (nManufacture > 0) {
                i++;
                boolean hasAuth = (nManufacture % 2 > 0);
                nManufacture = nManufacture >> 1;
                if (hasAuth) {
                    ManufactureTypeEnum manufactureTypeEnum = ManufactureTypeEnum.getManufactureTypeById(i);
                    if (manufactureTypeEnum != null) {
                        if (manufactureTypeEnum.equals(ManufactureTypeEnum.DAHUA)) {
                            manufactureTypeEnums.add(ManufactureTypeEnum.DHRPC);
                        }
                        manufactureTypeEnums.add(manufactureTypeEnum);
                    }
                }
            }
        }

		return manufactureTypeEnums;
	}

	/*public static boolean hasManufactureAuth(String manufacture)
    {
        if(manufacture.equals("0"))
        {
            manufacture = "IPC2.0";
        }
        manufacture = manufacture.replaceAll("\\.", "_");
        boolean hasAuth = false;
        for(ManufactureTypeEnum manufactureTypeEnum : Constants.MAUNFACTURE_LIST)
        {
            if(manufactureTypeEnum.toString().equals(manufacture))
            {
                hasAuth = true;
                break;
            }
        }

        return hasAuth;
    }*/

     public static String getWinAS300RootPath()
     {
         String javaPath = System.getProperty("user.dir").replace("\\","/");
         if(javaPath.contains("as300_web_all"))//如果是开发者环境
         {
             return "D:\\Program Files\\AS300";
         }
         int i = 0;
         while (i < 4)
         {
             i++;
             int index = javaPath.lastIndexOf("/");
             javaPath = javaPath.substring(0, index);
         }

         return javaPath.substring(0, javaPath.lastIndexOf("/"));
     }

     public static String getLinuxAS300RootPath()
     {
         String javaPath = System.getProperty("user.dir").replace("\\","/");
         if(javaPath.contains("as300_web_all"))//如果是开发者环境
         {
             return "D:\\Program Files\\AS300";
         }
         int i = 0;
         while (i < 2)
         {
             i++;
             int index = javaPath.lastIndexOf("/");
             javaPath = javaPath.substring(0, index);
         }

         return javaPath.substring(0, javaPath.lastIndexOf("/"));
     }


    public static void main(String[] args) {
        CCrInfoBean cCrInfoBean = JnaUtil.getCCR();
    }
}
