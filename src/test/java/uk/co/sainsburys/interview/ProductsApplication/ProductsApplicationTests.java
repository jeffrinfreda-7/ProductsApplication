package uk.co.sainsburys.interview.ProductsApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
				new ProductDetail("1", "Electronics", "Phone", "https://example.com/phone"),
				new ProductDetail("2", "Electronics", "Laptop", "https://example.com/laptop"),
				new ProductDetail("3", "Books", "Novel", "https://example.com/novel")
		};

		ProductPrice[] productPrices = new ProductPrice[]{
				new ProductPrice("1", 599.99, "GBP", 10),
				new ProductPrice("2", 1299.99, "GBP", 10),
				new ProductPrice("3", 9.99, "GBP", 10)
		};

		when(restTemplate.getForObject(eq(product_details_url), eq(ProductDetail[].class))).thenReturn(productDetails);
		when(restTemplate.getForObject(eq(product_price_url), eq(ProductPrice[].class))).thenReturn(productPrices);

		// Act
		List<Product> result = productService.getProductDetail("Electronics");

		// Assert
		assertEquals(2, result.size());
		assertEquals("Phone", result.get(0).getName());
		//assertEquals(599.99, result.get(0).getUnit_price());
		assertEquals("Laptop", result.get(1).getName());
		//assertEquals(1299.99, result.get(1).getUnit_price());
	}

	@Test
	public void testGetDistinctProductTypes() {
		// Arrange
		ProductDetail[] productDetails = new ProductDetail[]{
				new ProductDetail("1", "Electronics", "Phone", "https://example.com/phone"),
				new ProductDetail("2", "Electronics", "Laptop", "https://example.com/laptop"),
				new ProductDetail("3", "Books", "Novel", "https://example.com/novel")
		};

		when(restTemplate.getForObject(eq(product_details_url), eq(ProductDetail[].class))).thenReturn(productDetails);

		// Act
		List<String> result = productService.getDistinctProductTypes();

		// Assert
		assertEquals(2, result.size());
		assertEquals("Electronics", result.get(0));
		assertEquals("Books", result.get(1));
	}

}
