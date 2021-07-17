package compile;

import util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//表示一个完整的编译运行过程
public class Task {
    //临时文件（用于进程间通信）的文件名约定
    private String WORK_DIR;
    //要编译执行的类名，影响到源代码的文件名
    private String CLASS = "Solution";
    //要编译执行的文件名
    private String CODE;
    //程序标准输出放置的文件
    private String STDOUT;
    //程序标准错误放置的文件
    private String STDERR;
    //程序编译出错的详细信息放置的文件
    private String COMPILE_ERROR;

    public Task() {
        // 先生成唯一的 ID 拼装出目录的名字
        WORK_DIR = "./tmp/" + UUID.randomUUID().toString();
        CODE = WORK_DIR + CLASS + ".java";
        STDOUT = WORK_DIR + "stdout.txt";
        STDERR = WORK_DIR + "stderr.txt";
        COMPILE_ERROR = WORK_DIR + "compile_error.txt";
    }

    public Answer compileAndRun(Question question) throws IOException, InterruptedException {
        Answer answer = new Answer();
        //判断 WORK_DIR 是否存在，如果存在就跳过，不存在就创建目录
        File file = new File(WORK_DIR);
        if(!file.exists()){
            //创建对应的多级目录
            file.mkdirs();
        }
        //1. 需要用到的临时文件（一个目录）：要编译的源代码文件
        //   编译出错要放进一个文件
        //   最终运行的标准输出标准错误要分别放到文件中
        FileUtil.writeFile(CODE,question.getCode());
        //2. 构造编译命令（javac），并执行，预期得到的结果
        //   一个对应的 .class 文件，以及编译出错的文件
        //   -d 表示生成的 .class 文件放置的位置
        //   javac -encoding utf-8 ./tmp/Solution.java -d ./tmp/
        // String compileCmd = "javac -encoding utf-8 " + CODE + " -d " + WORK_DIR;
        //   String.format 类似于 C 中的 sprintf
        String compileCmd = String.format("javac -encoding utf-8 %s -d %s",CODE,WORK_DIR);
        System.out.println("编译命令：" + compileCmd);
        //   创建子进程进行编译，不关心 javac 标准输出，只关心标准错误（包含了出错信息）
        CommandUtil.run(compileCmd,null,COMPILE_ERROR);
        //   判定编译是否有错
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if(!compileError.equals("")){
            answer.setErrno(1);
            answer.setReason(compileError);
            return answer;
        }
        //3. 构造运行指令（java），并执行，预期得到的结果
        //   这个代码的标准输出文件和标准错误文件
        //   为了 Java 命令找到 .class 文件的位置，加一个 -classpath 选项来执行
        String runCmd = String.format("java -classpath %s %s",WORK_DIR,CLASS);
        System.out.println("runCmd：" + runCmd);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        //   STDERR 文件不为空就是运行异常（异常调用栈信息）
        String runError = FileUtil.readFile(STDERR);
        if(!runError.equals("")){
            answer.setErrno(2);
            answer.setReason(runError);
            return answer;
        }
        //4. 最终结果构造成 Answer 对象，并返回
        answer.setErrno(0);
        String runSTDOUT = FileUtil.readFile(STDOUT);
        answer.setStdout(runSTDOUT);
        return answer;
    }
}