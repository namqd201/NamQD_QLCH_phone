package presentation;

import business.impl.CustomerSeviceImpl;
import model.Customer;
import presentation.impl.Menu;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu implements Menu {
    private final CustomerSeviceImpl customerSevice;

    public CustomerMenu(CustomerSeviceImpl customerSevice) {
        this.customerSevice = customerSevice;
    }

    @Override
    public void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========= QUẢN LÝ KHÁCH HÀNG =========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    findAllCustomers();
                    break;
                case "2":
                    addCustomer(sc);
                    break;
                case "3":
                    updateCustomer(sc);
                    break;
                case "4":
                    deleteCustomer(sc);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    private void addCustomer(Scanner sc) {
        System.out.println("----- THÊM KHÁCH HÀNG -----");

        String name;
        while (true) {
            System.out.print("Tên khách hàng: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Tên khách hàng không được để trống!");
        }

        String phone;
        while (true) {
            System.out.print("Phone number: ");
            phone = sc.nextLine().trim();
            if (!phone.isEmpty()) break;
            System.out.println("Phone number không được để trống!");
        }

        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!email.isEmpty()) break;
            System.out.println("Email không được để trống!");
        }

        String address;
        while (true) {
            System.out.print("Địa chỉ: ");
            address = sc.nextLine();
            if (!address.isEmpty()) break;
            System.out.println("Address không được để trống!");
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);

        if (customerSevice.addCustomer(customer)) {
            System.out.println("Thêm khách hàng thành công!");
        } else {
            System.out.println("Thêm khách hàng thất bại!");
        }
    }

    private void updateCustomer(Scanner sc) {
        System.out.println("----- CẬP NHẬT KHÁCH HÀNG -----");

        Customer oldCustomer;
        while (true) {
            try {
                System.out.print("Nhập id khách hàng muốn cập nhật: ");
                int id = Integer.parseInt(sc.nextLine().trim());
                oldCustomer = customerSevice.getCustomerById(id);
                if (oldCustomer == null) {
                    System.out.println("Khách hàng không tồn tại!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Id phải là số, xin nhập lại!");
            }

        }
        System.out.println("Bạn đang cập nhật thông tin của khách hàng: " + oldCustomer.getName());

        String name;
        while (true) {
            System.out.print("Tên khách hàng: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()){
                break;
            } else {
                System.out.println("Tên khách hàng không được để trống!");
            }

        }

        String phone;
        while (true) {
            System.out.print("Phone number: ");
            phone = sc.nextLine().trim();
            if (!phone.isEmpty()) break;
            System.out.println("Phone number không được để trống!");
        }

        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!email.isEmpty()) break;
            System.out.println("Email không được để trống!");
        }

        String address;
        while (true) {
            System.out.print("Đại chỉ: ");
            address = sc.nextLine();
            if (!address.isEmpty()) break;
            System.out.println("Address không được để trống!");
        }
        Customer customer = new Customer();
        customer.setId(oldCustomer.getId());
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);

        if (customerSevice.updateCustomer(customer)) {
            System.out.println("Cập nhật khách hàng thành công!");
        } else {
            System.out.println("Cập nhật khách hàng thất bại!");
        }
    }

    private void deleteCustomer(Scanner sc) {
        System.out.println("----- XÓA KHÁCH HÀNG -----");

        int id;
        Customer oldCustomer;
        while (true) {
            try {
                System.out.print("Nhập id khách hàng muốn xóa: ");
                id = Integer.parseInt(sc.nextLine().trim());
                oldCustomer = customerSevice.getCustomerById(id);
                if (oldCustomer == null) {
                    System.out.println("Khách hàng không tồn tại!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Id phải là số, xin nhập lại!");
            }
        }

        while (true) {
            System.out.print(
                    "Bạn có chắc chắn muốn xóa khách hàng ["
                            + oldCustomer.getName()
                            + "]? (Y/N): "
            );

            String confirm = sc.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {
                if (customerSevice.deleteCustomer(id)) {
                    System.out.println("Xóa khách hàng thành công!");
                } else {
                    System.out.println("Xóa khách hàng thất bại!");
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

    private void findAllCustomers() {
        List<Customer> customers = customerSevice.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Danh sách khách hàng trống!");
        } else {
            System.out.println("ID | Tên | Phone | Email | Address");
            for (Customer c : customers) {
                System.out.printf(
                        "%d | %s | %s | %s | %s%n",
                        c.getId(),
                        c.getName(),
                        c.getPhone(),
                        c.getEmail(),
                        c.getAddress()
                );
            }
        }
    }
}
