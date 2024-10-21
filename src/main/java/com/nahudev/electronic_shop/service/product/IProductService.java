package com.nahudev.electronic_shop.service.product;

import com.nahudev.electronic_shop.model.Product;
import com.nahudev.electronic_shop.request.AddProductRequest;
import com.nahudev.electronic_shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    public Product addProduct(AddProductRequest product);

    public Product updateProduct(ProductUpdateRequest request, Long productId);

    public void deleteProduct(Long productId);

    public Product getProductById(Long productId);

    public List<Product> getAllProducts();

    public List<Product> getProductsByCategoryId(String category);

    public List<Product> getProductsByBrand(String brand);

    public List<Product> getProductsByCategoryAndBrand(String category, String brand);

    public Product getProductByName(String name);

    public List<Product> getProductsByBrandAndName(String brand, String name);

    public Long countProductsByBrandAndName(String brand, String name);

}
