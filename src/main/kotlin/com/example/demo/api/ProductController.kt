package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.models.ProductModel
import com.example.demo.resources.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

const val PRODUCT_PATH = "$API_PATH/products"

@RestController
@RequestMapping(value = [PRODUCT_PATH])
class ProductController(
        @Autowired
        private var shoppingService: ShoppingService
) {
    @GetMapping("/{id}")
    fun show(@PathVariable id: String?): HttpEntity<Product> {
        if (id != null) {
            val productModel: ProductModel? = shoppingService.showProduct(id)

            if (productModel != null) {
                val product = Product(productModel.id, productModel.name)
                product.add(WebMvcLinkBuilder.linkTo(RootController::class.java).withRel(IanaLinkRelations.INDEX))
                return ResponseEntity(product, HttpStatus.OK)
            }
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}