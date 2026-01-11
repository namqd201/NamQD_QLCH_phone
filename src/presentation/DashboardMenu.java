package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class DashboardMenu implements Menu {
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("========= MENU CHÍNH =========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    new ProductMenu().show();
                    break;
                case "2":
                    new CustomerMenu().show();
                    break;
                case "3":
                    new InvoiceMenu().show();
                    break;
                case "4":
                    new ReportMenu().show();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
