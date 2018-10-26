/**
 * FileName: ManufactureTypeEnum
 * Author:   Administrator
 * Date:     2018/8/16 17:59
 * Description: 厂商枚举
 */
package ccrSdk;

/**
 * 1- 默认(主动注册设备) 2: 大华 3：海康 4：vga 5：爱普 6：onvif 7:三洋 8:中控 9:星网锐捷 10:axis 11:xm 12:rtsp13:cmsclient 14:honeywell 15:devicereg 16:hikplateform(hik平台) 17:宇视 18:大华rpc
 *(UNIVIEW 宇视)
 */
public enum ManufactureTypeEnum
{
    DEFAULT, RTSP ,Onvif,IPC2_0 , DAHUA, HIKVISION, XM, CMSCLIENT, AXIS, Register, HONEYWELL, UNIVIEW, DHRPC;

    public static ManufactureTypeEnum getManufactureTypeById(int id)
    {
        ManufactureTypeEnum typeEnum = null;
        switch (id)
        {
            case 1:typeEnum=IPC2_0; break;
            case 2:typeEnum=DAHUA; break;
            case 3:typeEnum=HIKVISION; break;
            case 4:break;
            case 5:break;
            case 6: typeEnum= Onvif; break;
            case 7:break;
            case 8:break;
            case 9:break;
            case 10: typeEnum = AXIS; break;
            case 11: typeEnum = XM; break;
            case 12: typeEnum = RTSP;break;
            case 13: typeEnum = CMSCLIENT;break;
            case 14: typeEnum = HONEYWELL;break;
            case 15: typeEnum = Register; break;
            case 16: break;
            case 17: typeEnum = UNIVIEW; break;
        }

        return typeEnum;
    }
}
