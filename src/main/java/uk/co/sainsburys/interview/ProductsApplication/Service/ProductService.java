package uk.co.sainsburys.interview.ProductsApplication.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.sainsburys.interview.ProductsApplication.Model.Product;
import uk.co.sainsburys.interview.ProductsApplication.Model.ProductDetail;
import uk.co.sainsburys.interview.ProductsApplication.Model.ProductPrice;
import uk.co.sainsburys.interview.ProductsApplication.Model.UnitPrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final String product_details_url = "https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_v2.json";
    private final String product_price_url = "https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_price_v2.json";


    public List<Product> getProductDetail(String productType){


        RestTemplate restTemplate = new RestTemplate();

        ProductDetail [] productDetail = restTemplate.getForObject(product_details_url,ProductDetail[].class);
        ProductPrice[] productPrice = restTemplate.getForObject(product_price_url,ProductPrice[].class);

        //Create a price map
        Map<String,ProductPrice> productPriceMap = Arrays.stream(productPrice).
                collect(Collectors.toMap(ProductPrice::getProduct_uid, price->price));

        //merge the json
       return Arrays.stream(productDetail)
               .filter(a-> a.getProduct_type().equalsIgnoreCase(productType)).map(a -> {
            ProductPrice price = productPriceMap.get(a.getProduct_uid());
            if(price != null) {
                return new Product(a.getProduct_uid(), a.getProduct_type(), a.getName(), a.getFull_url()
                        , price.getUnit_price(), price.getUnit_price_measure(), price.getUnit_price_measure_amount());
            }
                            return null;
                        })
               .filter(product -> product!=null).collect(Collectors.toList());

    }



}
