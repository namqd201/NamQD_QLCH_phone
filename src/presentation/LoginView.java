package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class LoginView implements Menu {
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========= ĐĂNG NHẬP QUẢN TRỊ =========");
        System.out.print("Tài khoản: ");
        String username = sc.nextLine();
        System.out.print("Mật khẩu: ");
        String password = sc.nextLine();

        // TODO: gọi AdminService để check login
        boolean success = true; // tạm thời

        if (success) {
            System.out.println("Đăng nhập thành công!");
            new DashboardMenu().show();
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu!");
        }
    }
}
