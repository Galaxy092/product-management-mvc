package co.istad.controller;

import co.istad.dto.CreateProductDto;
import co.istad.dto.UpdateProductDto;
import co.istad.model.Product;
import co.istad.service.ProductService;
import co.istad.service.ProductServiceImp;
import co.istad.view.ProductView;

import java.time.LocalDate;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController() {
        productService = new ProductServiceImp();
    }

    public void index() {
        List<Product> products = productService.select();
        ProductView.printProductList(products);
    }

    public void createProduct(CreateProductDto createProductDto) {
        Product createProduct = new Product(createProductDto.getId(), createProductDto.getName(), createProductDto.getQty(), createProductDto.getPrice(), LocalDate.now());
        productService.insert(createProduct);
    }

    public Product updateProduct(String id, UpdateProductDto updateProductDto) {
        return productService.updateById(id, updateProductDto);
    }

    public Product deleteById(Long id) {
        return productService.deleteById(id);
    }
}
