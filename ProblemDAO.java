package problem;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO 数据访问对象
// 包含一些增删改查方法
public class ProblemDAO {
    public void insert(Problem problem){
        // 和数据库建立连接
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        try {
            // 拼装 SQL 语句
            String sql = "insert into oj_table values(null,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            // 执行 SQL
            // executeUpdate():针对 insert delete update
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public void delete(int problemId){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "delete from oj_table where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,problemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    // 查找全部题目（用来实现题目列表页）
    // 只需要查找 Problem 的一部分字段即可：id title level
    // 翻页查询，否则数据量比较大影响程序效率
    // SQL 中的 limit offset 基础支持
    public List<Problem> selectAll(){
        List<Problem> problems = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select id,title,level from oj_table";
        try {
            statement = connection.prepareStatement(sql);
            // 查找结果集合(select)用 .executeQuery() 方法
            resultSet = statement.executeQuery();
            // 遍历结果集合
            while(resultSet.next()){
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
//                problem.setDescription(resultSet.getString("description"));
//                problem.setTemplateCode(resultSet.getString("templateCode"));
//                problem.setTestCode(resultSet.getString("testCode"));
                problems.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return problems;
    }

    // 查找指定题目（用来实现题目详情页）
    // 需要把 Problem 每个字段都查询出来
    public Problem selectOne(int problemId){
        Connection connection = DBUtil.getConnection();
        String sql = "select * from oj_table where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,problemId);
            resultSet = statement.executeQuery();
            // 查询结果要么 0 个，要么 1 个，不需要用 while 了
            if(resultSet.next()){
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    // 测试：
//    public static void main(String[] args) {
//        Problem problem = new Problem();
//        problem.setId(1);
//        problem.setTitle("两数之和");
//        problem.setLevel("简单");
//        problem.setDescription("求两数之和");
//        problem.setTemplateCode("public...");
//        problem.setTestCode("public...");
//        ProblemDAO problemDAO = new ProblemDAO();
//        problemDAO.insert(problem);

//        ProblemDAO problemDAO = new ProblemDAO();
//        problemDAO.delete(1);

//        ProblemDAO problemDAO = new ProblemDAO();
//        List<Problem> problems = problemDAO.selectAll();
//        System.out.println(problems);

//        ProblemDAO problemDAO = new ProblemDAO();
//        Problem problem = problemDAO.selectOne(2);
//        System.out.println(problem);
//    }
}
