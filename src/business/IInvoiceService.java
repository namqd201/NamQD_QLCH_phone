package business;

import model.Invoice;
import model.InvoiceDetail;

import java.util.List;

public interface IInvoiceService {

    // Thêm hóa đơn + chi tiết + trừ kho
    Boolean createInvoice(Invoice invoice, List<InvoiceDetail> details);

    // Cập nhật hóa đơn + chi tiết + hoàn kho + trừ lại
    Boolean updateInvoice(Invoice invoice, List<InvoiceDetail> newDetails);

    // Xóa hóa đơn + hoàn kho
    Boolean deleteInvoice(int invoiceId);

    // Lấy hóa đơn
    Invoice getInvoice(int invoiceId);

    List<Invoice> getAllInvoices();

    List<Invoice> findInvoicesByCustomerName(String name);

    List<Invoice> findInvoiceByDay(int day);

    List<Invoice> findInvoiceByMonth(int month);

    List<Invoice> findInvoiceByYear(int year);

    List<InvoiceDetail> getInvoiceDetails(int invoiceId);
}
