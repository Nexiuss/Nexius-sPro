package video_dinogo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class VideoDiagnoseRootBean {
    private List<VideoDiagnoseDevBean> DevList;
    private ResultBean result;

    public VideoDiagnoseRootBean() {
    }

    public VideoDiagnoseRootBean(List<VideoDiagnoseDevBean> devList, ResultBean result) {
        DevList = devList;
        this.result = result;
    }
    @JSONField(name = "DevList")
    public List<VideoDiagnoseDevBean> getDevList() {
        return DevList;
    }

    public void setDevList(List<VideoDiagnoseDevBean> devList) {
        DevList = devList;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
