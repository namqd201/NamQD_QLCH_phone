package business;

import model.Invoice;
import model.InvoiceDetail;

import java.util.List;

public interface IInvoiceService {

    // Thêm hóa đơn + chi tiết + trừ kho
    int createInvoice(Invoice invoice, List<InvoiceDetail> details);

    // Xóa hóa đơn + hoàn kho
    Boolean deleteInvoice(int invoiceId);

    // Lấy hóa đơn
    Invoice getInvoice(int invoiceId);

    List<Invoice> getAllInvoices();

    List<Invoice> findInvoicesByCustomerName(String name);

    List<Invoice> findInvoiceByDay(int day, int month, int year);

    List<Invoice> findInvoiceByMonth(int month, int year);

    List<Invoice> findInvoiceByYear(int year);

    List<InvoiceDetail> getInvoiceDetails(int invoiceId);
}
