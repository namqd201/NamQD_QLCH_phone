package dao;

import model.Invoice;
import model.InvoiceDetail;

import java.util.List;

public interface IInvoiceDAO {
    int addInvoice(Invoice invoice);
    Boolean updateInvoice(Invoice invoice);
    Boolean deleteInvoice(Invoice invoice);
    List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int id);
    List<Invoice> getAllInvoices();
    List<Invoice> findInvoicesByCustomerName(String customerName);
    List<Invoice> findInvoiceByDay(int day);
    List<Invoice> findInvoiceByMonth(int month);
    List<Invoice> findInvoiceByYear(int year);
}
