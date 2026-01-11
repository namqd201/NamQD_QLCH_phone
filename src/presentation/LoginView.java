package presentation;

import dao.impl.LoginDAOImpl;
import presentation.impl.Menu;

import java.util.Scanner;

public class LoginView implements Menu {
    private final LoginDAOImpl dao = new LoginDAOImpl();
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= ĐĂNG NHẬP ADMIN =========");
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            Boolean success = dao.login(username, password);

            if (success) {
                System.out.println("Đăng nhập thành công!");
                new DashboardMenu().show();
                return;
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }
        }
    }
}
