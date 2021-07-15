package compile;

//编译运行的结果
public class Answer {
    //最终反馈在页面上的信息
    //运行结果是否正确
    //0 表示OK；1 表示编译出错；2 表示运行抛异常
    private int errno;
    //出错的原因是什么：
    //1：包含编译错误的信息
    //2：包含了异常的调用栈信息
    private String reason;
    //程序的标准输出
    private String stdout;
    //程序的标准错误
    private String stderr;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
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

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "errno=" + errno +
                ", reason='" + reason + '\'' +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}
