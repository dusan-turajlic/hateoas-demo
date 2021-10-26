package com.example.demo.api

import com.example.demo.data.ShoppingService
import com.example.demo.resources.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val CART_PATH = "$API_PATH/cart"

@RestController
@RequestMapping(CART_PATH)
class CartController(
    @Autowired
    private var shoppingService: ShoppingService
) {
    @PostMapping()
    fun store(@RequestBody product: Product) {
        shoppingService.addToCart(product)
    }
}