package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class DashboardMenu implements Menu {
    private final ProductMenu productMenu;
    private final CustomerMenu customerMenu;
    private final InvoiceMenu invoiceMenu;
    private final MainMenu mainMenu;
    private final ReportMenu reportMenu;

    public DashboardMenu(ProductMenu productMenu, CustomerMenu customerMenu, InvoiceMenu invoiceMenu, MainMenu mainMenu, ReportMenu reportMenu) {
        this.productMenu = productMenu;
        this.customerMenu = customerMenu;
        this.invoiceMenu = invoiceMenu;
        this.mainMenu = mainMenu;
        this.reportMenu = reportMenu;
    }

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
                    productMenu.show();
                    break;
                case "2":
                    customerMenu.show();
                    break;
                case "3":
                    invoiceMenu.show();
                    break;
                case "4":
                    reportMenu.show();
                    break;
                case "5":
                    mainMenu.show();
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
