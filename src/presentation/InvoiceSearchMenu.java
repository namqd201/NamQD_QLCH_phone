package presentation;

import business.impl.InvoiceServiceImpl;
import model.Invoice;
import presentation.impl.Menu;

import java.util.List;
import java.util.Scanner;

public class InvoiceSearchMenu implements Menu {

    private final InvoiceServiceImpl invoiceService;

    public InvoiceSearchMenu(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

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
                    searchByCustomerName(sc);
                    break;
                case "2":
                    searchByDay(sc);
                    break;
                case "3":
                    searchByMonth(sc);
                    break;
                case "4":
                    searchByYear(sc);
                    break;
                case "5":
                    return; // quay lại InvoiceMenu
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    private void searchByCustomerName(Scanner sc) {
        System.out.print("Nhập tên khách hàng: ");
        String name = sc.nextLine();

        List<Invoice> invoices =
                invoiceService.findInvoicesByCustomerName(name);

        if (invoices.isEmpty()) {
            System.out.println("Không tìm thấy hóa đơn nào!");
            return;
        }

        System.out.println("----- KẾT QUẢ TÌM KIẾM -----");
        System.out.printf("%-5s %-5s %-20s %-20s %-15s%n",
                "ID", "ID khách hàng", "Tên khách hàng", "Ngày tạo", "Tổng tiền");

        for (Invoice i : invoices) {
            System.out.printf("%-5d %-5d %-20s %-20s %-15s%n",
                    i.getId(),
                    i.getCustomerId(),
                    i.getCustomerName(),
                    i.getCreatedAt(),
                    i.getTotalAmount());
        }
    }

    private void searchByDay(Scanner sc) {
        int day, month, year;

        while (true) {
            try {
                System.out.print("Nhập ngày (1-31): ");
                day = Integer.parseInt(sc.nextLine());
                if (day > 0 && day <= 31) {
                    break;
                }
                System.out.println("Ngày nhập không hợp lệ. Vui lòng nhập trong khoảng 1-31");

            } catch (NumberFormatException e) {
                System.out.println("Ngày phải là số!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập tháng (1-12): ");
                month = Integer.parseInt(sc.nextLine());
                if (month > 0 && month <= 12) {
                    break;
                }
                System.out.println("Tháng nhập không hợp lệ. Vui lòng nhập trong khoảng 1-12");
            } catch (NumberFormatException e) {
                System.out.println("Thángphải là số!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập năm: ");
                year = Integer.parseInt(sc.nextLine());
                if (year > 2023 && year <= 2026) {
                    break;
                }
                System.out.println("Năm nhập không hợp lệ. Vui lòng nhập trong khoảng 2024-2026");
            } catch (NumberFormatException e) {
                System.out.println("Năm phải là số!");
            }
        }

        List<Invoice> invoices =
                invoiceService.findInvoiceByDay(day, month, year);

        if (invoices.isEmpty()) {
            System.out.println("Không tìm thấy hóa đơn nào!");
            return;
        }

        System.out.println("----- KẾT QUẢ TÌM KIẾM -----");
        System.out.printf("%-5s %-5s %-20s %-20s %-15s%n",
                "ID", "ID khách hàng", "Tên khách hàng", "Ngày tạo", "Tổng tiền");

        for (Invoice i : invoices) {
            System.out.printf("%-5d %-5d %-20s %-20s %-15s%n",
                    i.getId(),
                    i.getCustomerId(),
                    i.getCustomerName(),
                    i.getCreatedAt(),
                    i.getTotalAmount());
        }
    }

    private void searchByMonth(Scanner sc) {
        int month, year;

        while (true) {
            try {
                System.out.print("Nhập tháng (1-12): ");
                month = Integer.parseInt(sc.nextLine());
                if (month > 0 && month <= 12) {
                    break;
                }
                System.out.println("Tháng nhập không hợp lệ. Vui lòng nhập trong khoảng 1-12");
            } catch (NumberFormatException e) {
                System.out.println("Tháng phải là số!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập năm: ");
                year = Integer.parseInt(sc.nextLine());
                if (year > 2023 && year <= 2026) {
                    break;
                }
                System.out.println("Năm nhập không hợp lệ. Vui lòng nhập trong khoảng 2024-2026");
            } catch (NumberFormatException e) {
                System.out.println("Năm phải là số!");
            }
        }

        List<Invoice> invoices =
                invoiceService.findInvoiceByMonth(month, year);

        if (invoices.isEmpty()) {
            System.out.println("Không tìm thấy hóa đơn nào!");
            return;
        }

        System.out.println("----- KẾT QUẢ TÌM KIẾM -----");
        System.out.printf("%-5s %-5s %-20s %-20s %-15s%n",
                "ID", "ID khách hàng", "Tên khách hàng", "Ngày tạo", "Tổng tiền");

        for (Invoice i : invoices) {
            System.out.printf("%-5d %-5d %-20s %-20s %-15s%n",
                    i.getId(),
                    i.getCustomerId(),
                    i.getCustomerName(),
                    i.getCreatedAt(),
                    i.getTotalAmount());
        }
    }

    private void searchByYear(Scanner sc) {
        int year;

        while (true) {
            try {
                System.out.print("Nhập năm: ");
                year = Integer.parseInt(sc.nextLine());
                if (year > 2023 && year <= 2026) {
                    break;
                }
                System.out.println("Năm nhập không hợp lệ. Vui lòng nhập trong khoảng 2024-2026");
            } catch (NumberFormatException e) {
                System.out.println("Năm phải là số!");
            }
        }

        List<Invoice> invoices =
                invoiceService.findInvoiceByYear(year);

        if (invoices.isEmpty()) {
            System.out.println("Không tìm thấy hóa đơn nào!");
            return;
        }

        System.out.println("----- KẾT QUẢ TÌM KIẾM -----");
        System.out.printf("%-5s %-5s %-20s %-20s %-15s%n",
                "ID", "ID khách hàng", "Tên khách hàng", "Ngày tạo", "Tổng tiền");

        for (Invoice i : invoices) {
            System.out.printf("%-5d %-5d %-20s %-20s %-15s%n",
                    i.getId(),
                    i.getCustomerId(),
                    i.getCustomerName(),
                    i.getCreatedAt(),
                    i.getTotalAmount());
        }
    }
}
