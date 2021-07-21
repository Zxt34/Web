package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    // 用来实现读取题目列表和读取指定题目详情
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置 resp 中需要的属性
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf-8");

        // 从 req 对象中尝试读取 ID 这个参数
        String id = req.getParameter("id");
        if(id == null || id.equals("")){
            // 查找题目列表
            selectAll(resp);
        } else{
            // 查找指定题目
            selectOne(Integer.parseInt(id),resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 新增一个题目的数据
        // 要读取请求中的 body 数据，并构造成 Problem 对象
        // 插入数据库
        String body = readBody(req);
        Problem problem = gson.fromJson(body,Problem.class);
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("{\"ok\":1}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf-8");
        // 先从 req 中读取要删除的题目 ID
        String id = req.getParameter("id");
        if(id == null || id.equals("")){
            resp.getWriter().write("{\"ok\":0,\"reason\":\"id 不存在\"}");
            return;
        }
        // 调用数据库操作执行删除
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.delete(Integer.parseInt(id));
        // 返回一个删除结果
        resp.getWriter().write("{\"ok\":1}");
    }

    private String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        // 获取 body 长度分配空间(字节)
        int contentLength = req.getContentLength();
        byte[] buf = new byte[contentLength];
        // 根据 req 拿到读取的 body 的 InputStream 对象
        try(InputStream inputStream = req.getInputStream()){
            inputStream.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buf,"utf-8");
    }

    private void selectOne(int problemId, HttpServletResponse resp) throws IOException {
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(problemId);
        String respString = gson.toJson(problem);
        resp.getWriter().write(respString);
    }

    private void selectAll(HttpServletResponse resp) throws IOException {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = new ArrayList<>();
        // 把结果包装成 json 格式
        String respString = gson.toJson(problems);
        // 把结果写回给前端
        resp.getWriter().write(respString);
    }
}
