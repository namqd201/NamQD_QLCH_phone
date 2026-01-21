package business.impl;

import business.IInvoiceService;
import dao.impl.InvoiceDAOImpl;
import model.Invoice;
import model.InvoiceDetail;
import utils.DBUtil;

import java.sql.Connection;
import java.util.List;

public class InvoiceServiceImpl implements IInvoiceService {

    //Dùng injection
    private final DBUtil dbUtil;
    private final InvoiceDAOImpl invoiceDAO;

    public InvoiceServiceImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
        this.invoiceDAO = new InvoiceDAOImpl(dbUtil);
    }

    @Override
    public Boolean createInvoice(Invoice invoice, List<InvoiceDetail> details) {
        try(Connection conn = dbUtil.getConnection()){
            conn.setAutoCommit(false);

            //thêm hóa đơn
            int invoiceId = invoiceDAO.addInvoice(invoice);
            if (invoiceId == -1) {
                conn.rollback(); // nếu thêm thất bại, rollback
                return false;
            }

            // thêm chi tiết hóa đơn + trừ kho
            for (InvoiceDetail d : details) {
                d.setInvoiceId(invoiceId);

                boolean stockOk = invoiceDAO.decreaseStock(
                        conn, d.getProductId(), d.getQuantity());

                if (!stockOk) {
                    conn.rollback();
                    System.out.println("Không đủ tồn kho cho productId = " + d.getProductId());
                    return false;
                }
                invoiceDAO.addInvoiceDetail(d);
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateInvoice(Invoice invoice, List<InvoiceDetail> newDetails) {
        try (Connection conn = dbUtil.getConnection()) {
            conn.setAutoCommit(false);

            // Lấy detail cũ
            List<InvoiceDetail> oldDetails =
                    invoiceDAO.getInvoiceDetailsByInvoiceId(invoice.getId());

            // Hoàn kho
            for (InvoiceDetail d : oldDetails) {
                invoiceDAO.increaseStock(conn, d.getProductId(), d.getQuantity());
            }

            // Xóa detail cũ
            invoiceDAO.deleteInvoiceDetailsByInvoiceId(conn, invoice.getId());

            // Update invoice
            if (!invoiceDAO.updateInvoice(invoice)) {
                conn.rollback();
                return false;
            }

            // Insert detail mới + trừ kho
            for (InvoiceDetail d : newDetails) {
                d.setInvoiceId(invoice.getId());

                boolean stockOk = invoiceDAO.decreaseStock(
                        conn, d.getProductId(), d.getQuantity());

                if (!stockOk) {
                    conn.rollback();
                    System.out.println("Không đủ tồn kho khi update");
                    return false;
                }

                invoiceDAO.addInvoiceDetail(d);
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteInvoice(int invoiceId) {
        try (Connection conn = dbUtil.getConnection()) {
            conn.setAutoCommit(false);

            // Lấy detail
            List<InvoiceDetail> details =
                    invoiceDAO.getInvoiceDetailsByInvoiceId(invoiceId);

            if (details.isEmpty()) {
                conn.rollback();
                System.out.println("Hóa đơn không có chi tiết");
                return false;
            }

            // Hoàn kho
            for (InvoiceDetail d : details) {
                invoiceDAO.increaseStock(conn, d.getProductId(), d.getQuantity());
            }

            // Xóa invoice_detail
            invoiceDAO.deleteInvoiceDetailsByInvoiceId(conn, invoiceId);

            // Xóa invoice
            Invoice invoice = new Invoice();
            invoice.setId(invoiceId);

            boolean ok = invoiceDAO.deleteInvoice(invoice);
            if (!ok) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceDAO.getInvoice(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    @Override
    public List<Invoice> findInvoicesByCustomerName(String name) {
        return invoiceDAO.findInvoicesByCustomerName(name);
    }

    @Override
    public List<Invoice> findInvoiceByDay(int day) {
        return invoiceDAO.findInvoiceByDay(day);
    }

    @Override
    public List<Invoice> findInvoiceByMonth(int month) {
        return invoiceDAO.findInvoiceByMonth(month);
    }

    @Override
    public List<Invoice> findInvoiceByYear(int year) {
        return invoiceDAO.findInvoiceByYear(year);
    }

    @Override
    public List<InvoiceDetail> getInvoiceDetails(int invoiceId) {
        return invoiceDAO.getInvoiceDetailsByInvoiceId(invoiceId);
    }
}
