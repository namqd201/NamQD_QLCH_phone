package dao.impl;

import dao.IRevenueReportDAO;
import model.RevenueReport;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueReportDAOImpl implements IRevenueReportDAO {
    private final DBUtil dbUtil;

    public RevenueReportDAOImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public List<RevenueReport> revenueByDay() {
        String sql = "SELECT DATE(created_at) AS label, " +
                "SUM(total_amount) AS revenue " +
                "FROM invoice " +
                "GROUP BY DATE(created_at) " +
                "ORDER BY label";

        List<RevenueReport> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RevenueReport r = new RevenueReport();
                r.setLabel(rs.getString("label"));
                r.setRevenue(rs.getBigDecimal("revenue"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<RevenueReport> revenueByMonth() {
        String sql = "SELECT TO_CHAR(created_at, 'MM-YYYY') AS label, " +
                "SUM(total_amount) AS revenue " +
                "FROM invoice " +
                "GROUP BY label " +
                "ORDER BY label ";

        List<RevenueReport> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RevenueReport r = new RevenueReport();
                r.setLabel(rs.getString("label"));
                r.setRevenue(rs.getBigDecimal("revenue"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<RevenueReport> revenueByYear() {
        String sql = " SELECT EXTRACT(YEAR FROM created_at) AS label, " +
                "SUM(total_amount) AS revenue " +
                "FROM invoice " +
                "GROUP BY label " +
                "ORDER BY label";

        List<RevenueReport> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RevenueReport r = new RevenueReport();
                r.setLabel(rs.getString("label"));
                r.setRevenue(rs.getBigDecimal("revenue"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
