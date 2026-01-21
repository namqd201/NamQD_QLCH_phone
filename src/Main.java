import business.impl.*;
import dao.impl.*;
import presentation.*;
import utils.DBUtil;

public class Main {
    public static void main(String[] args) {
        // Initialize DBUtil
        DBUtil dbUtil = new DBUtil();
        
        // Initialize DAOs
        ProductDAOImpl productDAO = new ProductDAOImpl(dbUtil);
        CustomerDAOImpl customerDAO = new CustomerDAOImpl(dbUtil);
        
        // Initialize services
        LoginServiceImpl loginService = new LoginServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl(productDAO);
        CustomerSeviceImpl customerService = new CustomerSeviceImpl(customerDAO);
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(dbUtil);
        
        // Initialize menu views
        ProductMenu productMenu = new ProductMenu(productService);
        CustomerMenu customerMenu = new CustomerMenu(customerService);
        InvoiceSearchMenu invoiceSearchMenu = new InvoiceSearchMenu(invoiceService);
        InvoiceMenu invoiceMenu = new InvoiceMenu(invoiceService, invoiceSearchMenu);
        ReportMenu reportMenu = new ReportMenu();
        MainMenu mainMenu = new MainMenu();
        DashboardMenu dashboardMenu = new DashboardMenu(productMenu, customerMenu, invoiceMenu, mainMenu);
        
        // Initialize login view and main menu
        mainMenu.setLoginView(new LoginView(loginService, dashboardMenu));
        
        // Start the application
        mainMenu.show();
    }
}
