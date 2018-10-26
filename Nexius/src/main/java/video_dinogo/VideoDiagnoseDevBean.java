package video_dinogo;

import com.alibaba.fastjson.annotation.JSONField;

public class VideoDiagnoseDevBean {
        private String CamId;
        private int Ch;
        private String Devip;
        private String Devnm;
        private int Devport;
        private String Devpwd;
        private int Mask;
        private String Protocal;
        private int Stream;
        private String Url;

    public VideoDiagnoseDevBean() {
    }


    public void setCamId(String CamId) {
            this.CamId = CamId;
        }
    @JSONField(name = "CamId")
    public String getCamId() {
        return CamId;
    }

    public void setCh(int Ch) {
        this.Ch = Ch;
    }
    @JSONField(name = "Ch")
    public int getCh() {
        return Ch;
    }

    public void setDevip(String Devip) {
        this.Devip = Devip;
    }

    @JSONField(name = "Devip")
    public String getDevip() {
        return Devip;
    }

    public void setDevnm(String Devnm) {
        this.Devnm = Devnm;
    }

    @JSONField(name = "Devnm")
    public String getDevnm() {
        return Devnm;
    }

    public void setDevport(int Devport) {
        this.Devport = Devport;
    }

    @JSONField(name = "Devport")
    public int getDevport() {
        return Devport;
    }

    public void setDevpwd(String Devpwd) {
        this.Devpwd = Devpwd;
    }

    @JSONField(name = "Devpwd")
    public String getDevpwd() {
        return Devpwd;
    }

    public void setMask(int Mask) {
        this.Mask = Mask;
    }

    @JSONField(name = "Mask")
    public int getMask() {
        return Mask;
    }

    public void setProtocal(String Protocal) {
        this.Protocal = Protocal;
    }

    @JSONField(name = "Protocal")
    public String getProtocal() {
        return Protocal;
    }

    public void setStream(int Stream) {
        this.Stream = Stream;
    }

    @JSONField(name = "Stream")
    public int getStream() {
        return Stream;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    @JSONField(name = "Url")
    public String getUrl() {
            return Url;
        }

}
