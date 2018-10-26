package video_dinogo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * FileName: video_dinogo.video_dinogo
 * Author:   Administrator
 * Date:     2018/10/22 17:43
 * Description:
 */

public class video_dinogo
{
    public static void main(String[] args) {

        String DevList = "{ \"DevList\" : [ { \"CamId\" : \"67000010000003F2\", \"Ch\" : 0, \"Devip\" : \"172.18.32.167\", \"Devnm\" : \"admin\", \"Devport\" : 19000, \"Devpwd\" : \"123456aa\", \"Mask\" : 511, \"Protocal\" : \"CMSCLIENT\", \"Stream\" : 0, \"Url\" : \"\" }, { \"CamId\" : \"67000010000003F3\", \"Ch\" : 0, \"Devip\" : \"172.18.32.167\", \"Devnm\" : \"admin\", \"Devport\" : 19000, \"Devpwd\" : \"123456aa\", \"Mask\" : 511, \"Protocal\" : \"CMSCLIENT\", \"Stream\" : 0, \"Url\" : \"\" }, { \"CamId\" : \"6700001203000409\", \"Ch\" : 0, \"Devip\" : \"172.18.32.167\", \"Devnm\" : \"admin\", \"Devport\" : 19000, \"Devpwd\" : \"123456aa\", \"Mask\" : 511, \"Protocal\" : \"CMSCLIENT\", \"Stream\" : 0, \"Url\" : \"\" } ], \"result\" : { \"message\" : \"OK\", \"num\" : 200 } }\n";
        VideoDiagnoseRootBean videoDiagnoseRootBean = JSON.parseObject(JSON.parseObject(DevList).getJSONObject("remark").toString(), new TypeReference<VideoDiagnoseRootBean>() {});

        System.out.println(videoDiagnoseRootBean);
    }
}
