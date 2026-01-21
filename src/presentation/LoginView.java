package presentation;

import business.impl.LoginServiceImpl;
import dao.impl.LoginDAOImpl;
import presentation.impl.Menu;

import java.util.Scanner;

public class LoginView implements Menu {
    private final LoginServiceImpl service;
    private DashboardMenu dashboardMenu;

    public LoginView(LoginServiceImpl service) {
        this.service = service;
    }


    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= ĐĂNG NHẬP ADMIN =========");
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            Boolean success = service.login(username, password);

            if (success) {
                System.out.println("Đăng nhập thành công!");
                dashboardMenu.show();
                return;
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }
        }
    }
}
