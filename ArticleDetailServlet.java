package org.example.util.servlet;

import org.example.util.dao.ArticleDAO;
import org.example.util.model.Article;
import org.example.util.model.JSONResponse;
import org.example.util.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        JSONResponse json = new JSONResponse();
        try {
            String sid = req.getParameter("id");
            Article a = ArticleDAO.queryById(Integer.parseInt(sid));
            json.setSuccess(true);
            json.setData(a);
        }catch (Exception e){
            e.printStackTrace();
            json.setCode("ERROR");
            json.setMessage("文章详情查询出错");
        }
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }
}
