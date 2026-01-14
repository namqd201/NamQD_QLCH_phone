package dao.impl;

import dao.ILoginDAO;
import utils.DBUtil;

import java.sql.*;

public class LoginDAOImpl implements ILoginDAO {
    @Override
    public Boolean login(String username, String password) {
        String sql = "select * from admin where username=? and password=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
