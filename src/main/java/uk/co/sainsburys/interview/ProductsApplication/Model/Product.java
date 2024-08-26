package uk.co.sainsburys.interview.ProductsApplication.Model;


public class Product {

    private String productUid;
    private String productType;
    private String name;

    private String fullUrl;

    private double price;

    private String measure;

    private int measureAmount;

    public Product(String productUid, String productType, String name, String fullUrl, double price, String measure, int measureAmount) {
        this.productUid = productUid;
        this.productType = productType;
        this.name = name;
        this.fullUrl = fullUrl;
        this.price = price;
        this.measure = measure;
        this.measureAmount = measureAmount;
    }

    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getMeasureAmount() {
        return measureAmount;
    }

    public void setMeasureAmount(int measureAmount) {
        this.measureAmount = measureAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productUid='" + productUid + '\'' +
                ", productType='" + productType + '\'' +
                ", name='" + name + '\'' +
                ", fullUrl='" + fullUrl + '\'' +
                ", price=" + price +
                ", measure='" + measure + '\'' +
                ", measureAmount=" + measureAmount +
                '}';
    }
}
