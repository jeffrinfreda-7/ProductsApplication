package uk.co.sainsburys.interview.ProductsApplication.Model;

public class ProductPrice {

    private String product_uid;
    private double unit_price;

    private String unit_price_measure;

    private int unit_price_measure_amount;

    public String getProduct_uid() {
        return product_uid;
    }

    public void setProduct_uid(String product_uid) {
        this.product_uid = product_uid;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit_price_measure() {
        return unit_price_measure;
    }

    public void setUnit_price_measure(String unit_price_measure) {
        this.unit_price_measure = unit_price_measure;
    }

    public int getUnit_price_measure_amount() {
        return unit_price_measure_amount;
    }

    public void setUnit_price_measure_amount(int unit_price_measure_amount) {
        this.unit_price_measure_amount = unit_price_measure_amount;
    }

    public ProductPrice(String product_uid, double unit_price, String unit_price_measure, int unit_price_measure_amount) {
        this.product_uid = product_uid;
        this.unit_price = unit_price;
        this.unit_price_measure = unit_price_measure;
        this.unit_price_measure_amount = unit_price_measure_amount;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product_uid='" + product_uid + '\'' +
                ", unit_price=" + unit_price +
                ", unit_price_measure='" + unit_price_measure + '\'' +
                ", unit_price_measure_amount=" + unit_price_measure_amount +
                '}';
    }
}
