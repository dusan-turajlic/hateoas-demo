package com.example.demo.data

import com.example.demo.model.ProductModel
import com.example.demo.resource.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ShoppingService {
    private val logger: Logger = LoggerFactory.getLogger(ShoppingService::class.java)

    private var cartItems: MutableList<ProductModel> = mutableListOf()
    private var products: MutableList<ProductModel> = mutableListOf()
    private var orders: MutableList<ProductModel> = mutableListOf()

    fun hasItemsInCart(): Boolean {
        return cartItems.isNotEmpty();
    }

    fun hasPendingOrders(): Boolean {
        return orders.isNotEmpty()
    }

    fun getProducts(): List<ProductModel> {
        if (products.size == 0) {
            generateHardCodedProducts()
        }

        return products
    }

    private fun generateHardCodedProducts() {
        mutableListOf(
                "Live Laugh Code Coffee Cup",
                "Ironic T-Shirt",
                "Laptop stickers",
                "Mechanical Keyboard",
                "Book: How to exit vim, the right way"
        ).forEach {
            products += ProductModel(name = it)
        }
    }

    fun purchaseSuccess() {
        orders.addAll(cartItems)
        clearCart()
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun showProduct(id: String): ProductModel? {
        return products.find { it.id == id }
    }

    fun addToCart(product: Product) {
        val model = products.find { it.id == product.id }
        if (model != null) {
            cartItems += model

            logger.info("${model.name} has been added to cart")
        }
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find item with id: ${product.id}")
        }
    }

    fun getOrderedItems(): MutableList<ProductModel> {
        return orders
    }

    fun getItemsInCart(): MutableList<ProductModel> {
       return cartItems
    }
}