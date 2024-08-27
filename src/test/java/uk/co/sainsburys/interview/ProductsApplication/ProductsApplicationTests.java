package uk.co.sainsburys.interview.ProductsApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import uk.co.sainsburys.interview.ProductsApplication.Model.Product;
import uk.co.sainsburys.interview.ProductsApplication.Model.ProductDetail;
import uk.co.sainsburys.interview.ProductsApplication.Model.ProductPrice;
import uk.co.sainsburys.interview.ProductsApplication.Service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private ProductService productService;

	private final String product_details_url = "https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_v2.json";
	private final String product_price_url = "https://s3.eu-west-1.amazonaws.com/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_price_v2.json";

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetProductDetail() {
		// Arrange
		ProductDetail[] productDetails = new ProductDetail[]{
				new ProductDetail("6447344", "BASIC", "Sainsbury's Skin on ASC Scottish Salmon Fillets x2 240g", "https://www.sainsburys.co.uk/gol-ui/product/sainsburys-responsibly-sourced-scottish-salmon-fillet-x2-240g"),
				new ProductDetail("3052068", "BASIC", "Lurpak Slightly Salted Spreadable Blend of Butter & Rapeseed Oil 500g", "https://www.sainsburys.co.uk/gol-ui/product/lurpak-slightly-salted-spreadable-500g"),
				new ProductDetail("7511786", "BASIC2", "Cathedral City Mature Cheddar Cheese 350g", "https://www.sainsburys.co.uk/gol-ui/product/cathedral-city-mature-350g")
		};

		ProductPrice[] productPrices = new ProductPrice[]{
				new ProductPrice("6447344", 15.63, "kg", 1),
				new ProductPrice("3052068", 7.5, "kg", 1),
				new ProductPrice("7511786", 8.57, "kg", 1)
		};

		when(restTemplate.getForObject(eq(product_details_url), eq(ProductDetail[].class))).thenReturn(productDetails);
		when(restTemplate.getForObject(eq(product_price_url), eq(ProductPrice[].class))).thenReturn(productPrices);

		// Act
		List<Product> result = productService.getProductDetail("BASIC");

		// Assert
		assertEquals(7, result.size());  // Ensure that there are 2 products with the "BASIC" type
		assertEquals("Sainsbury's Skin on ASC Scottish Salmon Fillets x2 240g", result.get(0).getName());
		//assertEquals(15.63, result.get(0).getU);
		assertEquals("Lurpak Slightly Salted Spreadable Blend of Butter & Rapeseed Oil 500g", result.get(1).getName());
		//assertEquals(7.5, result.get(1).getUnit_price());
	}

	@Test
	public void testGetDistinctProductTypes() {
		// Arrange
		ProductDetail[] productDetails = new ProductDetail[]{
				new ProductDetail("6447344", "BASIC", "Sainsbury's Skin on ASC Scottish Salmon Fillets x2 240g", "https://www.sainsburys.co.uk/gol-ui/product/sainsburys-responsibly-sourced-scottish-salmon-fillet-x2-240g"),
				new ProductDetail("3052068", "BASIC", "Lurpak Slightly Salted Spreadable Blend of Butter & Rapeseed Oil 500g", "https://www.sainsburys.co.uk/gol-ui/product/lurpak-slightly-salted-spreadable-500g"),
				new ProductDetail("7511786", "BASIC2", "Cathedral City Mature Cheddar Cheese 350g", "https://www.sainsburys.co.uk/gol-ui/product/cathedral-city-mature-350g")
		};

		when(restTemplate.getForObject(eq(product_details_url), eq(ProductDetail[].class))).thenReturn(productDetails);

		// Act
		List<String> result = productService.getDistinctProductTypes();

		// Assert
		assertEquals(2, result.size());  // There are 2 distinct product types: "BASIC" and "BASIC2"
		assertEquals("BASIC", result.get(0));
		assertEquals("BASIC2", result.get(1));
	}
}
