package com.bjsxt.dao.impl;

import com.bjsxt.dao.LoginDao;
import com.bjsxt.pojo.User;

import java.sql.*;
import java.util.Random;

public class LoginDaoImpl implements LoginDao {

    @Override
    public User checkLoginDao(String uname, String pwd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbb?useSSL=false","root","123456");

            ps = conn.prepareStatement("select * from t_user where uname=? and pwd=?");
            ps.setObject(1, uname);
            ps.setObject(2, pwd);

            rs = ps.executeQuery();
            while(rs.next()){

                u = new User();

                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));

            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    public User checkUidDao(String uid) {
        return null;
    }
}
