import business.impl.InvoiceServiceImpl;
import business.impl.LoginServiceImpl;
import presentation.*;

public class Main {
    public static void main(String[] args) {
        LoginServiceImpl loginService = new LoginServiceImpl();
        LoginView loginView = new LoginView(loginService);
        loginView.show();
    }
}
