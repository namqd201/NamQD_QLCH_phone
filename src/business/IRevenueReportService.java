package business;

import model.RevenueReport;

import java.util.List;

public interface IRevenueReportService {
    List<RevenueReport> byDay();
    List<RevenueReport> byMonth();
    List<RevenueReport> byYear();
}
