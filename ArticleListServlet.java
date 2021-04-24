package org.example.util.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.dao.ArticleDAO;
import org.example.util.model.Article;
import org.example.util.model.JSONResponse;
import org.example.util.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        JSONResponse json = new JSONResponse();
        try{
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            //TODO
            List<Article> query = ArticleDAO.query(user.getId());
            json.setSuccess(true);
            json.setData(query);
        }catch (Exception e){
            e.printStackTrace();
            //TODO
            json.setCode("ERR");
            json.setMessage("系统出错了，请联系系统管理员");
        }
        ObjectMapper m = new ObjectMapper();
        String s = m.writeValueAsString(json);
        resp.getWriter().println(s);
    }
}
