package compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//通过这个类来创建进行，并执行 javac，java 等命令
public class CommandUtil {
    //run 方法就用于进行创建进程并执行命令
    //cmd 表示要执行的命令，比如 javac
    //stdoutFile 指定标准输出写到哪个文件中
    //stderrFile 指定标准错误写到哪个文件中
    public static int run(String cmd,String stdoutFile,String stderrFile) throws IOException, InterruptedException {
        //Runtime 完成进程的创建，不需要手动创建实例，使用现有的即可
        //Runtime 的实例有且只有一个：单例模式
        //这个 Process 对象就是用来用来表示新创建出的进程
        Process process = Runtime.getRuntime().exec(cmd);
        //需要获取新进程的输出结果
        if(stdoutFile != null){
            //getInputStream 得到的是标准输出
            InputStream stdoutFrom = process.getInputStream();
            //通过这个对象去读取新进程的标准输出内容
            FileOutputStream stdoutTo = new FileOutputStream(stdoutFile);
            //从新进程依次读取每个字节写入到 stdoutTo 中
            while(true){
                int ch = stdoutFrom.read();
                if(ch == -1)
                    break;
                stdoutTo.write(ch);
            }
            //文件读写完毕，关闭
            stdoutFrom.close();
            stdoutTo.close();
        }
        //再针对标准错误进行重定向
        if(stderrFile != null){
            //getErrorStream 得到的是标准错误
            InputStream stderrFrom = process.getErrorStream();
            FileOutputStream stderrTo = new FileOutputStream(stderrFile);
            while(true){
                int ch = stderrFrom.read();
                if(ch == -1)
                    break;
                stderrTo.write(ch);
            }
            stderrFrom.close();
            stderrTo.close();
        }
        //waitFor()：父进程阻塞等到（低效）子进程结束才返回并获取到子进程的状态码
        int exitCode = process.waitFor();
        //正确 0，不正确非0
        return exitCode;
    }
}
