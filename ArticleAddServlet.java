package org.example.util.servlet;

import org.example.util.dao.ArticleDAO;
import org.example.util.model.Article;
import org.example.util.model.JSONResponse;
import org.example.util.model.User;
import org.example.util.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        JSONResponse json = new JSONResponse();
        try{
            //1.解析请求数据
            Article a = JSONUtil.deserialize(req.getInputStream(),Article.class);
            HttpSession session = req.getSession(false);
            User user = (User)session.getAttribute("user");
            //2.业务处理
            int n = ArticleDAO.insert(a,user.getId());
            json.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            json.setCode("ERR");
            json.setMessage("后端程序出错了，请联系管理员");
        }
        //3.返回响应数据
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }
}
