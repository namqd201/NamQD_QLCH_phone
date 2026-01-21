package presentation;

import business.impl.InvoiceServiceImpl;
import model.Invoice;
import model.InvoiceDetail;
import presentation.impl.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceMenu implements Menu {

    private final InvoiceServiceImpl invoiceService;
    private final InvoiceSearchMenu invoiceSearchMenu;

    public InvoiceMenu(InvoiceServiceImpl invoiceService, InvoiceSearchMenu invoiceSearchMenu) {
        this.invoiceService = invoiceService;
        this.invoiceSearchMenu = invoiceSearchMenu;
    }

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
                    showAllInvoices();
                    break;
                case "2":
                    createInvoice(sc);
                    break;
                case "3":
                    viewInvoiceDetail(sc);
                    break;
                case "4":
                    deleteInvoice(sc);
                    break;
                case "5":
                    invoiceSearchMenu.show();
                case "6":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    private void showAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();

        if (invoices.isEmpty()) {
            System.out.println("Chưa có hóa đơn nào!");
            return;
        }

        System.out.println("----- DANH SÁCH HÓA ĐƠN -----");
        System.out.printf("%-5s %-5s %-20s %-20s %-15s%n",
                "ID", "Mã khách hàng", "Tên Khách hàng", "Ngày tạo", "Tổng tiền");

        for (Invoice i : invoices) {
            System.out.printf("%-5d %-5d %-20s %-20s %-15s%n",
                    i.getId(),
                    i.getCustomerId(),
                    i.getCustomerName(),
                    i.getCreatedAt(),
                    i.getTotalAmount());
        }
    }

    //view detail
    private void viewInvoiceDetail(Scanner sc) {
        int invoiceId;

        while (true) {
            try {
                System.out.print("Nhập ID hóa đơn: ");
                invoiceId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số!");
            }
        }

        Invoice invoice = invoiceService.getInvoice(invoiceId);
        if (invoice == null) {
            System.out.println("Không tìm thấy hóa đơn!");
            return;
        }

        List<InvoiceDetail> details = invoiceService.getInvoiceDetails(invoiceId);

        System.out.println("----- CHI TIẾT HÓA ĐƠN -----");
        System.out.println("Hóa đơn ID: " + invoice.getId());
        System.out.println("Khách hàng ID: " + invoice.getCustomerId());
        System.out.println("Ngày tạo: " + invoice.getCreatedAt());
        System.out.println("Tổng tiền: " + invoice.getTotalAmount());

        System.out.println("\nSẢN PHẨM:");
        System.out.printf("%-10s %-10s %-10s%n",
                "ProductID", "Số lượng", "Đơn giá");

        for (InvoiceDetail d : details) {
            System.out.printf("%-10d %-10d %-10s%n",
                    d.getProductId(),
                    d.getQuantity(),
                    d.getUnitPrice());
        }
    }

    private void createInvoice(Scanner sc) {

        int customerId;
        while (true) {
            try {
                System.out.print("Nhập ID khách hàng: ");
                customerId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID khách hàng phải là số!");
            }
        }

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customerId);

        List<InvoiceDetail> details = new ArrayList<>();

        System.out.println("\n=== NHẬP CHI TIẾT HÓA ĐƠN ===");
        System.out.println("Nhập 0 để kết thúc");

        while (true) {
            int productId;
            int quantity;

            // nhập productId
            while (true) {
                try {
                    System.out.print("ID sản phẩm: ");
                    productId = Integer.parseInt(sc.nextLine());

                    if (productId == 0) {
                        break;
                    }

                    if (productId < 0) {
                        System.out.println("ID sản phẩm phải > 0!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("ID sản phẩm phải là số!");
                }
            }

            if (productId == 0) break;

            // nhập quantity
            while (true) {
                try {
                    System.out.print("Số lượng: ");
                    quantity = Integer.parseInt(sc.nextLine());

                    if (quantity <= 0) {
                        System.out.println("Số lượng phải > 0!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng phải là số!");
                }
            }

            InvoiceDetail detail = new InvoiceDetail();
            detail.setProductId(productId);
            detail.setQuantity(quantity);
            // không set unitPrice
            details.add(detail);
        }

        if (details.isEmpty()) {
            System.out.println("Hóa đơn phải có ít nhất 1 sản phẩm!");
            return;
        }

        int invoiceId = invoiceService.createInvoice(invoice, details);

        if (invoiceId != -1) {
            System.out.println("Tạo hóa đơn thành công! ID = " + invoiceId);
        } else {
            System.out.println("Tạo hóa đơn thất bại!");
        }
    }

    //xóa hóa đơn
    private void deleteInvoice(Scanner sc) {
        int invoiceId;

        while (true) {
            try {
                System.out.print("Nhập ID hóa đơn cần xóa: ");
                invoiceId = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số!");
            }
        }

        Invoice invoice = invoiceService.getInvoice(invoiceId);
        if (invoice == null) {
            System.out.println("Hóa đơn không tồn tại!");
            return;
        }

        System.out.print("Bạn có chắc muốn xóa hóa đơn này? (y/n): ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Đã hủy xóa.");
            return;
        }

        if (invoiceService.deleteInvoice(invoiceId)) {
            System.out.println("Xóa hóa đơn thành công!");
        } else {
            System.out.println("Xóa hóa đơn thất bại!");
        }
    }

}
