public class ResultBean {
    private String message;
    private int num;

    public ResultBean() {
    }

    public ResultBean(String message, int num) {
        this.message = message;
        this.num = num;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }
}
