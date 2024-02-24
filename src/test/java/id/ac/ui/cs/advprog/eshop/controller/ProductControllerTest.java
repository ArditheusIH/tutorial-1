package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProductPage() {
        Model model = mock(Model.class);
        assertEquals("createProduct", productController.createProductPage(model));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        Model model = mock(Model.class);
        assertEquals("redirect:list", productController.createProductPost(product, model));
    }

    @Test
    void testProductListPage() {
        Model model = mock(Model.class);
        assertEquals("productList", productController.productListPage(model));
    }

    @Test
    void testEditProductPage() {
        String productName = "Susu Milo";
        Model model = mock(Model.class);
        assertEquals("EditProduct", productController.editProductPage(productName, model));
    }

    @Test
    void testEditProductPost() {
        String productName = "Susu Milo";
        Product product = new Product();
        Model model = mock(Model.class);
        assertEquals("redirect:../list", productController.editProductPost(productName, model, product));
    }

    @Test
    void testDeleteProduct() {
        String productName = "Susu Milo";
        assertEquals("redirect:../list", productController.deleteProduct(productName));
    }
}