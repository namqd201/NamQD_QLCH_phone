package presentation;

import dao.impl.ProductDAOImpl;
import model.Product;
import presentation.impl.Menu;

import java.math.BigDecimal;
import java.util.List;
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
                    findAllProducts();
                    break;
                case "2":
                    addProduct(sc);
                    break;
                case "3":
                    updateProduct(sc);
                    break;
                case "4":
                    deleteProduct(sc);
                    break;
                case "5":
                    findAllProductByBrand(sc);
                    break;
                case "6":
                    findAllProductByPrice(sc);
                    break;
                case "7":
                    System.out.println("TODO");
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Lựa chọn không phù hợp! Vui lòng nhập lại: ");
            }
        }
    }

    private void findAllProducts() {
        List<Product> products = dao.findAllProducts();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào!");
        } else {
            System.out.println("ID | Tên | Brand | Giá | Tồn kho");
            for (Product p : products) {
                System.out.printf(
                        "%d | %s | %s | %s | %d%n",
                        p.getProductId(),
                        p.getProductName(),
                        p.getBrand(),
                        p.getProductPrice(),
                        p.getStock()
                );
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

    private void updateProduct(Scanner sc) {
        System.out.println("----- CẬP NHẬT SẢN PHẨM -----");

        int id;

        Product oldProduct;
        while (true) {
            try{
                System.out.print("Nhập id sản phẩm muốn cập nhật: ");
                id = Integer.parseInt(sc.nextLine().trim());
                oldProduct = dao.findProductById(id);
                if (oldProduct == null) {
                    System.out.println("Sản phẩm không tồn tại!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số!");
            }

        }

        System.out.println("Đang cập nhật sản phẩm: " + oldProduct.getProductName());

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
        product.setProductId(id);
        product.setProductName(name);
        product.setBrand(brand);
        product.setProductPrice(price);
        product.setStock(stock);

        if (dao.updateProduct(product)) {
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Cập nhật sản phẩm thất bại!");
        }
    }

    private void deleteProduct(Scanner sc) {
        int id;
        Product oldProduct;
        while (true) {
            try{
                System.out.print("Nhập id sản phẩm muốn xóa: ");
                id = Integer.parseInt(sc.nextLine().trim());
                oldProduct = dao.findProductById(id);
                if (oldProduct == null) {
                    System.out.println("Sản phẩm không tồn tại!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ID phải là số!");
            }
        }

        while (true) {
            System.out.print(
                    "Bạn có chắc chắn muốn xóa sản phẩm ["
                            + oldProduct.getProductName()
                            + "]? (Y/N): "
            );

            String confirm = sc.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {
                if (dao.deleteProduct(id)) {
                    System.out.println("Xóa sản phẩm thành công!");
                } else {
                    System.out.println("Xóa sản phẩm thất bại!");
                }
                break;
            }
            else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Đã hủy thao tác xóa.");
                break;
            }
            else {
                System.out.println("Vui lòng nhập Y hoặc N!");
            }
        }
    }

    private void findAllProductByBrand(Scanner sc) {
        String brand;
        while (true) {
            System.out.println("Nhập tên brand: ");
            brand = sc.nextLine().trim();
            if (brand.isEmpty()){
                System.out.println("Không được để trống tn brand!");
            } else {
                break;
            }
        }
        List<Product> products = dao.findAllProductsByBrand(brand);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            System.out.println("ID | Tên | Brand | Giá | Tồn kho");
            for (Product p : products) {
                System.out.printf(
                        "%d | %s | %s | %s | %d%n",
                        p.getProductId(),
                        p.getProductName(),
                        p.getBrand(),
                        p.getProductPrice(),
                        p.getStock()
                );
            }
        }
    }

    private void findAllProductByPrice(Scanner sc) {
        BigDecimal minPrice;
        BigDecimal maxPrice;
        while (true) {
            try {
                System.out.print("Giá thấp nhất: ");
                minPrice = new BigDecimal(sc.nextLine());
                if (minPrice.compareTo(BigDecimal.ZERO) > 0) break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ!");
            }
        }
        while (true) {
            try {
                System.out.print("Giá cao nhất: ");
                maxPrice = new BigDecimal(sc.nextLine());
                if (maxPrice.compareTo(BigDecimal.ZERO) > 0) break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ!");
            }
        }
        List<Product> products = dao.findAllProductsByPrice(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            System.out.println("ID | Tên | Brand | Giá | Tồn kho");
            for (Product p : products) {
                System.out.printf(
                        "%d | %s | %s | %s | %d%n",
                        p.getProductId(),
                        p.getProductName(),
                        p.getBrand(),
                        p.getProductPrice(),
                        p.getStock()
                );
            }
        }
    }
}
