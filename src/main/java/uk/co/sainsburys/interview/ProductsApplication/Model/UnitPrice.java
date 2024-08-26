package uk.co.sainsburys.interview.ProductsApplication.Model;

public class UnitPrice {

    private double price;

    private String measure;

    private int measureAmount;

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

    public UnitPrice() {
        this.price = price;
        this.measure = measure;
        this.measureAmount = measureAmount;
    }

    @Override
    public String toString() {
        return "UnitPrice{" +
                "price=" + price +
                ", measure='" + measure + '\'' +
                ", measureAmount=" + measureAmount +
                '}';
    }
}
