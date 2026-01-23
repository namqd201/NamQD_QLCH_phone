import business.impl.*;
import dao.impl.*;
import presentation.*;
import utils.DBUtil;

public class Main {
    public static void main(String[] args) {
        // DBUtil
        DBUtil dbUtil = new DBUtil();
        
        // DAOs
        ProductDAOImpl productDAO = new ProductDAOImpl(dbUtil);
        CustomerDAOImpl customerDAO = new CustomerDAOImpl(dbUtil);
        RevenueReportDAOImpl revenueReportDAO = new RevenueReportDAOImpl(dbUtil);
        
        // services
        LoginServiceImpl loginService = new LoginServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl(productDAO);
        CustomerSeviceImpl customerService = new CustomerSeviceImpl(customerDAO);
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(dbUtil);
        RevenueReportServiceImpl revenueReportService = new RevenueReportServiceImpl(revenueReportDAO);
        
        // menu views
        ProductMenu productMenu = new ProductMenu(productService);
        CustomerMenu customerMenu = new CustomerMenu(customerService);
        InvoiceSearchMenu invoiceSearchMenu = new InvoiceSearchMenu(invoiceService);
        InvoiceMenu invoiceMenu = new InvoiceMenu(invoiceService, invoiceSearchMenu, productService);
        ReportMenu reportMenu = new ReportMenu(revenueReportService);
        MainMenu mainMenu = new MainMenu();
        DashboardMenu dashboardMenu = new DashboardMenu(productMenu, customerMenu, invoiceMenu, mainMenu, reportMenu);

        mainMenu.setLoginView(new LoginView(loginService, dashboardMenu));

        mainMenu.show();
    }
}
