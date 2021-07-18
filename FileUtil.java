package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//封装 Java 的文件操作
public class FileUtil {
    //从指定文件中一次把所有的内容读出来
    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            while(true){
                int ch = fileInputStream.read();
                if(ch == -1){
                    break;
                }
                //每次读一个字节
                //int 只是多表示一种 -1 的情况
                //并且预期写入的一个字符而不是四个字节
                stringBuilder.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    //把 content 中的内容一次写入到 filePath 对应的文件中
    public static void writeFile(String filePath,String content){
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            //写文件操作
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
