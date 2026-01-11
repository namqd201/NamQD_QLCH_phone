package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class ReportMenu implements Menu {
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= THỐNG KÊ DOANH THU =========");
            System.out.println("1. Thống kê doanh thu theo ngày");
            System.out.println("2. Thống kê doanh thu theo tháng");
            System.out.println("3. Thống kê doanh thu theo năm");
            System.out.println("4. Top sản phẩm bán chạy");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("TODO: Thống kê doanh thu theo ngày");
                    break;
                case "2":
                    System.out.println("TODO: Thống kê doanh thu theo tháng");
                    break;
                case "3":
                    System.out.println("TODO: Thống kê doanh thu theo năm");
                    break;
                case "4":
                    System.out.println("TODO: Top sản phẩm bán chạy");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
