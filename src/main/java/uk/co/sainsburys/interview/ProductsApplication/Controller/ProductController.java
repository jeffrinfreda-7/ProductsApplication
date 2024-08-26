package uk.co.sainsburys.interview.ProductsApplication.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sainsburys.interview.ProductsApplication.Model.Product;
import uk.co.sainsburys.interview.ProductsApplication.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProductDetail/{productType}")
    public List<Product> getProductDetail(@PathVariable String productType){
        return productService.getProductDetail(productType);
    }


    @GetMapping("/distinctProduct")
    public List<String> getDistinctProductTypes() {
        return productService.getDistinctProductTypes();
    }





}
