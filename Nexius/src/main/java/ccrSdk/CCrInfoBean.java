/**
 * FileName: CCrInfoBean
 * Author:   Administrator
 * Date:     2018/8/17 11:09
 * Description: CCR信息
 */
package ccrSdk;

import java.util.Set;

public class CCrInfoBean
{
    private String mac;
    private int maxChannel = 0;
    private Set<ManufactureTypeEnum> ManufactureList;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getMaxChannel() {
        return maxChannel;
    }

    public void setMaxChannel(int maxChannel) {
        this.maxChannel = maxChannel;
    }

    public Set<ManufactureTypeEnum> getManufactureList() {
        return ManufactureList;
    }

    public void setManufactureList(Set<ManufactureTypeEnum> manufactureList) {
        ManufactureList = manufactureList;
    }
}
