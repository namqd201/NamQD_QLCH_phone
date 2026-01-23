package dao.impl;

import dao.IInvoiceDAO;
import model.Invoice;
import model.InvoiceDetail;
import utils.DBUtil;

import java.math.BigDecimal;
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

            ResultSet rs = ps.getGeneratedKeys(); // Lấy danh sách key tự động tạo
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

    public BigDecimal calculateTotalAmount(int invoiceId) throws SQLException {

        String sql = "SELECT COALESCE(SUM(quantity * unit_price), 0) " +
                "FROM invoice_details " +
                "WHERE invoice_id = ?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
        return BigDecimal.ZERO;
    }

    public boolean updateInvoiceTotalAmount(int invoiceId, BigDecimal totalAmount)
            throws SQLException {

        String sql = "UPDATE invoice SET total_amount = ? WHERE id = ?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, totalAmount);
            ps.setInt(2, invoiceId);
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
    public boolean deleteInvoiceDetailsByInvoiceId(
            Connection conn, int invoiceId) throws SQLException {

        String sql = "DELETE FROM invoice_details WHERE invoice_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Invoice getInvoice(int id) {
        String sql = "select * from invoice where id = ?";
        try(Connection conn = dbUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                return invoice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(int invoiceId) {
        String sql = "SELECT d.id, d.product_id, d.quantity, d.unit_price " +
                "FROM invoice_details d WHERE d.invoice_id = ?";

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
                d.setQuantity(rs.getInt("quantity"));
                d.setUnitPrice(rs.getBigDecimal("unit_price"));
                details.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return details;
    }

    //lấy tất cả hóa đơn
    @Override
    public List<Invoice> getAllInvoices() {
        String sql = " SELECT i.id, i.customer_id, c.name, i.created_at, i.total_amount " +
                "FROM invoice i " +
                "JOIN customer c ON i.customer_id = c.id " +
                "ORDER BY i.created_at DESC";

        List<Invoice> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCustomerName(rs.getString("name"));
                invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                list.add(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // tìm hóa đơn theo tên khách hàng
    @Override
    public List<Invoice> findInvoicesByCustomerName(String customerName) {
        String sql = "SELECT i.id, i.customer_id, c.name, i.created_at, i.total_amount " +
                "FROM invoice i " +
                "JOIN customer c ON i.customer_id = c.id " +
                "WHERE LOWER(c.name) LIKE LOWER(?) " +
                "ORDER BY i.created_at DESC";

        List<Invoice> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + customerName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getInt("id"));
                    invoice.setCustomerId(rs.getInt("customer_id"));
                    invoice.setCustomerName(rs.getString("name"));
                    invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                    list.add(invoice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //list hóa đơn theo ngày
    @Override
    public List<Invoice> findInvoiceByDay(int day, int month, int year) {
        String sql = " SELECT i.id, i.customer_id, c.name, i.created_at, i.total_amount " +
                "FROM invoice i " +
                "JOIN customer c ON i.customer_id = c.id " +
                "WHERE EXTRACT(DAY FROM i.created_at) = ? " +
                "  AND EXTRACT(MONTH FROM i.created_at) = ? " +
                "  AND EXTRACT(YEAR FROM i.created_at) = ? " +
                "ORDER BY i.created_at DESC";

        List<Invoice> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, day);
            ps.setInt(2, month);
            ps.setInt(3, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getInt("id"));
                    invoice.setCustomerId(rs.getInt("customer_id"));
                    invoice.setCustomerName(rs.getString("name"));
                    invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                    list.add(invoice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //list hóa đơn theo tháng
    @Override
    public List<Invoice> findInvoiceByMonth(int month, int year) {
        String sql = " SELECT i.id, i.customer_id, c.name, i.created_at, i.total_amount " +
                "FROM invoice i " +
                "JOIN customer c ON i.customer_id = c.id " +
                "WHERE EXTRACT(MONTH FROM i.created_at) = ? " +
                "  AND EXTRACT(YEAR FROM i.created_at) = ? " +
                "ORDER BY i.created_at DESC";

        List<Invoice> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getInt("id"));
                    invoice.setCustomerId(rs.getInt("customer_id"));
                    invoice.setCustomerName(rs.getString("name"));
                    invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                    list.add(invoice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // list hóa đơn theo năm
    @Override
    public List<Invoice> findInvoiceByYear(int year) {
        String sql = " SELECT i.id, i.customer_id, c.name, i.created_at, i.total_amount " +
                "FROM invoice i " +
                "JOIN customer c ON i.customer_id = c.id " +
                "WHERE EXTRACT(YEAR FROM i.created_at) = ? " +
                "ORDER BY i.created_at DESC";

        List<Invoice> list = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getInt("id"));
                    invoice.setCustomerId(rs.getInt("customer_id"));
                    invoice.setCustomerName(rs.getString("name"));
                    invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    invoice.setTotalAmount(rs.getBigDecimal("total_amount"));
                    list.add(invoice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //cập nhật số lượng sản phẩm sau khi thêm hóa đơn
    public boolean decreaseStock(
            Connection conn, int productId, int quantity) throws SQLException {

        String sql = "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?"; // stock>=0 => check stock không được âm

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;
        }
    }

    // cộng lại số lượng trong kho sau khi hoàn trả
    public boolean increaseStock(
            Connection conn, int productId, int quantity) throws SQLException {

        String sql = """
        UPDATE product
        SET stock = stock + ?
        WHERE id = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);

            return ps.executeUpdate() > 0;
        }
    }
}
