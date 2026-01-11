package dao.impl;

import dao.ILoginDAO;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAOImpl implements ILoginDAO {
    DBUtil dbUtil = new DBUtil();
    Connection conn = dbUtil.getConnection();
    @Override
    public Boolean login(String username, String password) {
        String sql = "select * from admin where username=? and password=?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
