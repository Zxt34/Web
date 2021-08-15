package util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class HttpUtil {
    public static String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        //1.先获取到body的长度，并分配对应的内存空间
        //单位是字节
        int contentLength=req.getContentLength();
        byte[] buf=new byte[contentLength];
        //2.根据req 对象，拿到读取body的Inputstream对象
        try(InputStream inputStream=req.getInputStream()) {
            inputStream.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.把读到的buf 转换成String 对象
        return new String(buf,"utf-8");
    }
}
