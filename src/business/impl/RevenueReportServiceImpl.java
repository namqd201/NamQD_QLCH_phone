package business.impl;

import business.IRevenueReportService;
import dao.impl.RevenueReportDAOImpl;
import model.RevenueReport;

import java.util.List;

public class RevenueReportServiceImpl implements IRevenueReportService {
    private final RevenueReportDAOImpl revenueReport;

    public RevenueReportServiceImpl(RevenueReportDAOImpl revenueReport) {
        this.revenueReport = revenueReport;
    }

    @Override
    public List<RevenueReport> byDay() {
        return revenueReport.revenueByDay();
    }

    @Override
    public List<RevenueReport> byMonth() {
        return revenueReport.revenueByMonth();
    }

    @Override
    public List<RevenueReport> byYear() {
        return revenueReport.revenueByYear();
    }
}
