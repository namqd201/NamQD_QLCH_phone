package presentation;

import business.impl.RevenueReportServiceImpl;
import model.RevenueReport;
import presentation.impl.Menu;

import java.util.List;
import java.util.Scanner;

public class ReportMenu implements Menu {
    private final RevenueReportServiceImpl revenueReportServiceImpl;

    public ReportMenu(RevenueReportServiceImpl revenueReportServiceImpl) {
        this.revenueReportServiceImpl = revenueReportServiceImpl;
    }

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= THỐNG KÊ DOANH THU =========");
            System.out.println("1. Thống kê doanh thu theo ngày");
            System.out.println("2. Thống kê doanh thu theo tháng");
            System.out.println("3. Thống kê doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            List<RevenueReport> list;
            switch (choice) {
                case "1":
                    list = revenueReportServiceImpl.byDay();
                    break;
                case "2":
                    list = revenueReportServiceImpl.byMonth();
                    break;
                case "3":
                    list = revenueReportServiceImpl.byYear();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
            }

            System.out.println("Thời gian | Doanh thu");
            for (RevenueReport r : list) {
                System.out.println(r.getLabel() + " | " + r.getRevenue());
            }
        }
    }
}
