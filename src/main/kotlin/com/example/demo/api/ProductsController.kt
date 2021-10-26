package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.models.ProductModel
import com.example.demo.resources.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController("/products")
class ProductsController(
        @Autowired
        private var shoppingService: ShoppingService
) {
    @GetMapping("/{id}")
    fun show( @PathVariable(value = "id") id: String): HttpEntity<Product> {
        val product: ProductModel? = shoppingService.showProduct(id)

        if (product != null) {
            return ResponseEntity(Product(product.id, product.name), HttpStatus.OK)
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}