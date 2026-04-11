package kiemthu.project;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class FuelCalculatorTest {
    FuelCalculator calculator = new FuelCalculator();
    double delta = 0.001; 

    // NHÓM KIỂM THỬ XĂNG RON95 (TC_R01 -> TC_R11)

    @Test
    @DisplayName("TC_R01: RON95, -0.1L (Dưới min)")
    void test_TC_R01() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(FuelType.RON95, -0.1f));
    }

    @Test
    @DisplayName("TC_R02: RON95, 0.0L (Giá trị min)")
    void test_TC_R02() {
        assertEquals(0.0, calculator.calculateTotal(FuelType.RON95, 0.0f), delta);
    }

    @Test
    @DisplayName("TC_R03: RON95, 0.1L (Giá trị min+)")
    void test_TC_R03() {
        assertEquals(2350.0, calculator.calculateTotal(FuelType.RON95, 0.1f), delta);
    }

    @Test
    @DisplayName("TC_R04: RON95, 25.0L (Giá trị nom)")
    void test_TC_R04() {
        assertEquals(587500.0, calculator.calculateTotal(FuelType.RON95, 25.0f), delta);
    }

    @Test
    @DisplayName("TC_R05: RON95, 49.9L (Cận dưới biên logic)")
    void test_TC_R05() {
        assertEquals(1172650.0, calculator.calculateTotal(FuelType.RON95, 49.9f), delta);
    }

    @Test
    @DisplayName("TC_R06: RON95, 50.0L (Ngay biên logic)")
    void test_TC_R06() {
        assertEquals(1175000.0, calculator.calculateTotal(FuelType.RON95, 50.0f), delta);
    }

    @Test
    @DisplayName("TC_R07: RON95, 50.1L (Bắt đầu tính phụ phí)")
    void test_TC_R07() {
        assertEquals(1177450.0, calculator.calculateTotal(FuelType.RON95, 50.1f), delta);
    }

    @Test
    @DisplayName("TC_R08: RON95, 75.0L (Nom đoạn >50)")
    void test_TC_R08() {
        assertEquals(1787500.0, calculator.calculateTotal(FuelType.RON95, 75.0f), delta);
    }

    @Test
    @DisplayName("TC_R09: RON95, 99.9L (Giá trị max-)")
    void test_TC_R09() {
        assertEquals(2397550.0, calculator.calculateTotal(FuelType.RON95, 99.9f), delta);
    }

    @Test
    @DisplayName("TC_R10: RON95, 100.0L (Giá trị max)")
    void test_TC_R10() {
        assertEquals(2400000.0, calculator.calculateTotal(FuelType.RON95, 100.0f), delta);
    }

    @Test
    @DisplayName("TC_R11: RON95, 100.1L (Vượt max)")
    void test_TC_R11() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(FuelType.RON95, 100.1f));
    }

    // NHÓM KIỂM THỬ XĂNG E5 (TC_E01 -> TC_E11)
 
    @Test
    @DisplayName("TC_E01: E5, -0.1L (Dưới min)")
    void test_TC_E01() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(FuelType.E5, -0.1f));
    }

    @Test
    @DisplayName("TC_E02: E5, 0.0L (Giá trị min)")
    void test_TC_E02() {
        assertEquals(0.0, calculator.calculateTotal(FuelType.E5, 0.0f), delta);
    }

    @Test
    @DisplayName("TC_E03: E5, 0.1L (Giá trị min+)")
    void test_TC_E03() {
        assertEquals(2250.0, calculator.calculateTotal(FuelType.E5, 0.1f), delta);
    }

    @Test
    @DisplayName("TC_E04: E5, 25.0L (Giá trị nom)")
    void test_TC_E04() {
        assertEquals(562500.0, calculator.calculateTotal(FuelType.E5, 25.0f), delta);
    }

    @Test
    @DisplayName("TC_E05: E5, 49.9L (Cận dưới biên logic)")
    void test_TC_E05() {
        assertEquals(1122750.0, calculator.calculateTotal(FuelType.E5, 49.9f), delta);
    }

    @Test
    @DisplayName("TC_E06: E5, 50.0L (Ngay biên logic)")
    void test_TC_E06() {
        assertEquals(1125000.0, calculator.calculateTotal(FuelType.E5, 50.0f), delta);
    }

    @Test
    @DisplayName("TC_E07: E5, 50.1L (Bắt đầu tính phụ phí)")
    void test_TC_E07() {
        assertEquals(1127350.0, calculator.calculateTotal(FuelType.E5, 50.1f), delta);
    }

    @Test
    @DisplayName("TC_E08: E5, 75.0L (Nom đoạn >50)")
    void test_TC_E08() {
        assertEquals(1712500.0, calculator.calculateTotal(FuelType.E5, 75.0f), delta);
    }

    @Test
    @DisplayName("TC_E09: E5, 99.9L (Giá trị max-)")
    void test_TC_E09() {
        assertEquals(2297650.0, calculator.calculateTotal(FuelType.E5, 99.9f), delta);
    }

    @Test
    @DisplayName("TC_E10: E5, 100.0L (Giá trị max)")
    void test_TC_E10() {
        assertEquals(2300000.0, calculator.calculateTotal(FuelType.E5, 100.0f), delta);
    }

    @Test
    @DisplayName("TC_E11: E5, 100.1L (Vượt max)")
    void test_TC_E11() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateTotal(FuelType.E5, 100.1f));
    }

    // Kiem thu bang quyet dinh

    @Test
    @DisplayName("TC_DT01 (R1): RON95, 30L (Dưới 50L, không phụ phí)")
    void test_TC_DT01() {
        assertEquals(705000.0, calculator.calculateTotal(FuelType.RON95, 30.0f), delta);
    }

    @Test
    @DisplayName("TC_DT02 (R2): RON95, 60L (Trên 50L, có phụ phí)")
    void test_TC_DT02() {
        assertEquals(1420000.0, calculator.calculateTotal(FuelType.RON95, 60.0f), delta);
    }

    @Test
    @DisplayName("TC_DT03 (R3): E5, 30L (Dưới 50L, không phụ phí)")
    void test_TC_DT03() {
        assertEquals(675000.0, calculator.calculateTotal(FuelType.E5, 30.0f), delta);
    }

    @Test
    @DisplayName("TC_DT04 (R4): E5, 60L (Trên 50L, có phụ phí)")
    void test_TC_DT04() {
        assertEquals(1360000.0, calculator.calculateTotal(FuelType.E5, 60.0f), delta);
    }

    @Test
    @DisplayName("TC_DT05 (R5): RON95, 105L (Vượt quá giới hạn 100L)")
    void test_TC_DT05() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTotal(FuelType.RON95, 105.0f);
        });
    }
    @Test
    public void testCalculateTotal_ExceptionCoverage() {
        FuelCalculator calculator = new FuelCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTotal(FuelType.E5, -5f);
        });
        assertEquals("Số lít phải từ 0 đến 100.", exception.getMessage());
    }
    @Test
    public void testCalculateTotal_VolumeBelowOrEqual50() {
        FuelCalculator calculator = new FuelCalculator();

        double result = calculator.calculateTotal(FuelType.E5, 30f);
        assertEquals(675000.0, result, 0.001);
    }
    @Test
    public void testCalculateTotal_VolumeAbove50_WithSurcharge() {
        FuelCalculator calculator = new FuelCalculator();

        double result = calculator.calculateTotal(FuelType.RON95, 60f);
        assertEquals(1420000.0, result, 0.001);
    }
}
