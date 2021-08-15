package api;

//表示编译结果的响应类
public class CompileResponse {
    private  int errno;
    private  String reason;
    private  String stdout;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int error) {
        this.errno = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }
}
