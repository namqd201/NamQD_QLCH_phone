package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class InvoiceSearchMenu implements Menu {
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("======= TÌM KIẾM HÓA ĐƠN =======");
            System.out.println("1. Tìm theo tên khách hàng");
            System.out.println("2. Tìm theo ngày");
            System.out.println("3. Tìm theo tháng");
            System.out.println("4. Tìm theo năm");
            System.out.println("5. Quay lại menu hóa đơn");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Nhập tên khách hàng: ");
                    String customerName = sc.nextLine();
                    System.out.println("TODO: Tìm hóa đơn theo tên khách hàng: " + customerName);
                    break;

                case "2":
                    System.out.print("Nhập ngày (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.println("TODO: Tìm hóa đơn theo ngày: " + date);
                    break;

                case "3":
                    System.out.print("Nhập tháng (1-12): ");
                    String month = sc.nextLine();
                    System.out.println("TODO: Tìm hóa đơn theo tháng: " + month);
                    break;

                case "4":
                    System.out.print("Nhập năm (yyyy): ");
                    String year = sc.nextLine();
                    System.out.println("TODO: Tìm hóa đơn theo năm: " + year);
                    break;

                case "5":
                    return; // quay lại InvoiceMenu

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }
    }
}
