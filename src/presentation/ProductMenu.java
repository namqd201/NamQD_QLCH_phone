package presentation;

import presentation.impl.Menu;

import java.util.Scanner;

public class ProductMenu implements Menu {
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
                    System.out.println("TODO: Hiển thị sản phẩm");
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
}
