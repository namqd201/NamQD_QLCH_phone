package dao;

import model.RevenueReport;

import java.util.List;

public interface IRevenueReportDAO {
    List<RevenueReport> revenueByDay();
    List<RevenueReport> revenueByMonth();
    List<RevenueReport> revenueByYear();
}
