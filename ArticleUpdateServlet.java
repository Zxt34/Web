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

@WebServlet("/articleUpdate")
class ArticleUpdateServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        JSONResponse json=new JSONResponse();
        try{
            //1。解析请求数据
            Article  a=JSONUtil.deserialize(req.getInputStream(),Article.class);
            //2.业务处理:根据文章id 修改文章内容
            int n= ArticleDAO.update(a);

            json.setSuccess(true);
            //设置业务数据

        }catch (Exception e){
            e.printStackTrace();
            json.setCode("ERROR");
            json.setMessage("文章详情查询出错");
        }
        String s= JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }
}
