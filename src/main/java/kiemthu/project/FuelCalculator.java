package kiemthu.project;

import java.util.Scanner;

public class FuelCalculator {
    public static final double SURCHARGE = 1000;

    public double calculateTotal(FuelType type, float volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Số lít phải từ 0 đến 100.");
        }
        double unitPrice = type.getPrice();
        if (volume <= 50) {
            return volume * unitPrice;
        } else {
            return (50 * unitPrice) + (volume - 50) * (unitPrice + SURCHARGE);
        }
    }

    public static void main(String[] args) {
        FuelCalculator calculator = new FuelCalculator();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== CHƯƠNG TRÌNH TÍNH TIỀN XĂNG ===");
            
            System.out.print("Chọn loại xăng (1 cho RON95, 2 cho E5): ");
            int choice = scanner.nextInt();
            FuelType type = (choice == 1) ? FuelType.RON95 : FuelType.E5;

            System.out.print("Nhập số lít xăng cần đổ (0 - 100): ");
            float volume = scanner.nextFloat();

            double result = calculator.calculateTotal(type, volume);
            System.out.printf("Tổng số tiền bạn cần trả là: %,.0f VNĐ\n", result);
            
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}