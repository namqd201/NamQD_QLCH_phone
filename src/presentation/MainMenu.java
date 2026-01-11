package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("========= HỆ THỐNG QUẢN LÝ CỬA HÀNG =========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    new LoginView().show();
                    break;
                case "2":
                    System.out.println("Tạm biệt!");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }
}
