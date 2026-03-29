package Gioi_QuanLySanPham_Va_TinhToanGiaTriTrongCuaHang;

import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {
    /**
     * Implementation of the abstract method for total value calculation.
     */
    @Override
    public double calculateTotalValue(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
