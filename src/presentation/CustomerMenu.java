package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class CustomerMenu implements Menu {
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= QUẢN LÝ KHÁCH HÀNG =========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Tìm kiếm khách hàng theo tên");
            System.out.println("6. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("TODO: Hiển thị danh sách khách hàng");
                    break;
                case "2":
                    System.out.println("TODO: Thêm khách hàng mới");
                    break;
                case "3":
                    System.out.println("TODO: Cập nhật khách hàng");
                    break;
                case "4":
                    System.out.println("TODO: Xóa khách hàng theo ID");
                    break;
                case "5":
                    System.out.println("TODO: Tìm kiếm khách hàng theo tên");
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }
    }
}
