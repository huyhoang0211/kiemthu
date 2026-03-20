package kiemthu.project;

public enum FuelType {
    RON95(23500),
    E5(22500);

    private final double price;

    FuelType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}