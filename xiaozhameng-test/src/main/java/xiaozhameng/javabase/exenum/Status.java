package xiaozhameng.javabase.exenum;

/**
 * 测试枚举类
 */
public enum Status {
    SUCCESS("000000","ok"),
    FAILED("444444","失败");

    private String code;
    private String message;

    Status(){}
    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
