package api;

// 这个类表示进行编译运行操作的请求对象
public class CompileRequest {
    // 和 body（json） 中严格对应
    private int id;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
