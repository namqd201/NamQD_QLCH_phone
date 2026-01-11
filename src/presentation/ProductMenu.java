package presentation;

import dao.impl.ProductDAOImpl;
import model.Product;
import presentation.impl.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductMenu implements Menu {
    private final ProductDAOImpl dao =  new ProductDAOImpl();
    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("========= QUẢN LÝ SẢN PHẨM =========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "2":
                    addProduct(sc);
                    break;
                case "3":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "4":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "5":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "6":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "7":
                    System.out.println("TODO: Hiển thị sản phẩm");
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Lựa chọn không phù hợp! Vui lòng nhập lại: ");
            }
        }
    }
    private void addProduct(Scanner sc) {
        System.out.println("----- THÊM SẢN PHẨM -----");

        String name;
        while (true) {
            System.out.print("Tên sản phẩm: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Tên sản phẩm không được để trống!");
        }

        String brand;
        while (true) {
            System.out.print("Brand: ");
            brand = sc.nextLine().trim();
            if (!brand.isEmpty()) break;
            System.out.println("Brand không được để trống!");
        }

        BigDecimal price;
        while (true) {
            try {
                System.out.print("Giá: ");
                price = new BigDecimal(sc.nextLine());
                if (price.compareTo(BigDecimal.ZERO) > 0) break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ!");
            }
        }

        int stock;
        while (true) {
            try {
                System.out.print("Tồn kho: ");
                stock = Integer.parseInt(sc.nextLine());
                if (stock >= 0) break;
                System.out.println("Tồn kho phải >= 0!");
            } catch (NumberFormatException e) {
                System.out.println("Tồn kho phải là số nguyên!");
            }
        }

        Product product = new Product();
        product.setProductName(name);
        product.setBrand(brand);
        product.setProductPrice(price);
        product.setStock(stock);

        if (dao.addProduct(product)) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.out.println("Thêm sản phẩm thất bại!");
        }
    }
}
