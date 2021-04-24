package org.example.util.dao;

import org.example.util.model.Article;
import org.example.util.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    public static List<Article> query(int userId) throws SQLException {
        //创建连接Connection对象
        Connection c = DBUtil.getConnection();
        System.out.println(c);
        //创建操作命令对象Statement
        String sql = "select id,title from article where user_id = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        //替换占位符的值（索引，要替换的值）
        ps.setInt(1,userId);
        //执行sql
        ResultSet rs = ps.executeQuery();
        List<Article> query = new ArrayList<>();
        //处理结果集（查询）
        while(rs.next()){
            int id = rs.getInt("id");
            String title = rs.getString("title");
            Article a = new Article();
            a.setId(id);
            a.setTitle(title);
            query.add(a);
        }
        //释放资源 TODO 异常就释放不了资源了
        DBUtil.close(c, ps, rs);
        return query;
    }

//    public static void main(String[] args) throws SQLException {
//        System.out.println(query(1));
//    }
}
