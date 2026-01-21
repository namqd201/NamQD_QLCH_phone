package dao.impl;

import dao.IInvoiceDAO;
import model.Invoice;
import model.InvoiceDetail;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements IInvoiceDAO {
    private final DBUtil dbUtil;

    public InvoiceDAOImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public int addInvoice(Invoice invoice) {
        String sql = "insert into invoice(customer_id, created_at, total_amount) values(?,?,?)";
        try(Connection conn = dbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setInt(1, invoice.getCustomerId());
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBigDecimal(3, invoice.getTotalAmount());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // Lấy danh sách các key tự động tạo
            if (rs.next()) {
                return rs.getInt(1);// Trả về giá trị ID ở cột đầu tiên
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    //thêm sản phẩm ở chi tiết hóa đơn
    public boolean addInvoiceDetail(InvoiceDetail detail)
            throws SQLException {

        String sql = "INSERT INTO invoice_details (invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getInvoiceId());
            ps.setInt(2, detail.getProductId());
            ps.setInt(3, detail.getQuantity());
            ps.setBigDecimal(4, detail.getUnitPrice());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Boolean updateInvoice(Invoice invoice) {
        String sql = "update invoice set customer_id=?, total_amount=? where id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoice.getCustomerId());
            ps.setBigDecimal(2, invoice.getTotalAmount());
            ps.setInt(3, invoice.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //update sản phẩm ở chi tiết hóa đơn
    public Boolean updateInvoiceDetail(InvoiceDetail detail)
            throws SQLException {

        String sql = "UPDATE invoice_details SET product_id = ?, quantity = ?, unit_price = ?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getProductId());
            ps.setInt(2, detail.getQuantity());
            ps.setBigDecimal(3, detail.getUnitPrice());
            ps.setInt(4, detail.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // xóa hóa đơn
    @Override
    public Boolean deleteInvoice(Invoice invoice) {
        String sql = "DELETE FROM invoice WHERE id = ?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, invoice.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //xóa chi tiết hóa đơn
    

    @Override
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int invoiceId) {
        String sql = "SELECT d.id, d.product_id, p.name AS product_name, d.quantity, d.unit_price " +
                "FROM invoice_details d JOIN product p ON d.product_id = p.id WHERE d.invoice_id = ?";

        List<InvoiceDetail> details = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                InvoiceDetail d = new InvoiceDetail();
                d.setId(rs.getInt("id"));
                d.setInvoiceId(invoiceId);
                d.setProductId(rs.getInt("product_id"));
                d.setProductName(rs.getString("product_name"));
                d.setQuantity(rs.getInt("quantity"));
                d.setUnitPrice(rs.getBigDecimal("unit_price"));
                details.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return details;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return List.of();
    }

    @Override
    public List<Invoice> findInvoicesByCustomerName(String customerName) {
        return List.of();
    }

    @Override
    public List<Invoice> findInvoiceByDay(int day) {
        return List.of();
    }

    @Override
    public List<Invoice> findInvoiceByMonth(int month) {
        return List.of();
    }

    @Override
    public List<Invoice> findInvoiceByYear(int year) {
        return List.of();
    }
}
