package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class InvoiceMenu implements Menu {

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= QUẢN LÝ HÓA ĐƠN =========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Tạo hóa đơn mới");
            System.out.println("3. Xem chi tiết hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Tìm kiếm khóa đơn");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("TODO: Hiển thị danh sách hóa đơn");
                    break;
                case "2":
                    System.out.println("TODO: Tạo hóa đơn mới");
                    break;
                case "3":
                    System.out.println("TODO: Xem chi tiết hóa đơn");
                    break;
                case "4":
                    System.out.println("TODO: Xóa hóa đơn");
                    break;
                case "5":
                    System.out.println("TODO: Tìm kiếm khóa đơn");;
                case "6":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
