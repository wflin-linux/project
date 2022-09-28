package code;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 连接数据库的类
 */

public class Jdbc {
    // 使用资源绑定器绑定属性配置文件
    ResourceBundle bundle = ResourceBundle.getBundle("mysql");
    String driver = bundle.getString("driver");
    String url = bundle.getString("url");
    String user = bundle.getString("user");
    String password = bundle.getString("password");

    Connection conn = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;
    PreparedStatement ps4 = null;
    PreparedStatement ps5 = null;
    ResultSet rs = null;

    public Jdbc() throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName(driver);
        //2.获取连接
        conn = DriverManager.getConnection(url, user, password);
        // 获取预编译的数据库操作对象
        // 插入数据语句
        String sql = "insert into user(name, password, score) values(?, ?, 0)";
        ps1 = conn.prepareStatement(sql);

        // 更新数据语句
        String sql2 = "update user set score = ? where name = ?";
        ps2 = conn.prepareStatement(sql2);

        // 查询用户名和密码的数据语句
        String sql3 = "select * from user where name = ? and password = ?";
        ps3 = conn.prepareStatement(sql3);

        // 查询指定用户的分数的数据语句
        String sql4 = "select score from user where name = ?";
        ps4 = conn.prepareStatement(sql4);

        // 查询用户排名信息的数据语句
        String sql5 = "select name,score from user order by score desc";
        ps5 = conn.prepareStatement(sql5);
    }

    // 插入用户信息
    public int insert(String name, String password) {
        // 记录数据库中执行DML语句的条数
        int count = 0;
        try {
            int psw = Integer.parseInt(password);
            ps1.setString(1, name);
            ps1.setInt(2, psw);
            count = ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }

    // 更新用户信息
    public int update(String name, int score) {
        // 记录数据库中执行DML语句的条数
        int count = 0;
        try {
            ps2.setInt(1, score);
            ps2.setString(2, name);
            count = ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return count;
        }

    }

    // 判断是否有该用户
    public boolean compare(String name, String password) {
        // 打标识
        boolean loginSuccess = false;
        try {
            int psw = Integer.parseInt(password);
            ps3.setString(1, name);
            ps3.setInt(2, psw);
            rs = ps3.executeQuery();
            if (rs.next()) {
                loginSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return loginSuccess;
        }
    }

    // 获取指定用户的分数
    public int[] score(String name) {
        // 记录数据库中执行DML语句的条数以及分数
        int[] count = {0, 0};
        try {
            ps4.setString(1, name);
            rs = ps4.executeQuery();
            if (rs.next()) {
                // 查询成功设置为1，否在为0
                count[0] = 1;
                // 获取指定用户分数
                count[1] = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }

    // 获取排名榜前5名的用户名
    public String[] name() {
        // 用户名
        String[] name = new String[5];
        int count = 0;
        try {
            rs = ps5.executeQuery();
            while (count != 5 && rs.next()) {
                name[count] = rs.getString("name");
                count++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return name;
        }
    }

    // 获取排名榜前5名的分数
    public int[] score() {
        // 分数
        int[] score = new int[5];
        int count = 0;
        try {
            rs = ps5.executeQuery();
            while (count != 5 && rs.next()) {
                score[count] = rs.getInt("score");
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return score;
        }
    }

    // 释放资源
    public void releaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps1 != null) {
            try {
                ps1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps2 != null) {
            try {
                ps2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps3 != null) {
            try {
                ps3.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps4 != null) {
            try {
                ps4.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps5 != null) {
            try {
                ps5.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
