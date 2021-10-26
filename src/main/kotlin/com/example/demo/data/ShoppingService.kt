package com.example.demo.data

import com.example.demo.model.ProductModel
import com.example.demo.resource.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ShoppingService {
    private val logger: Logger = LoggerFactory.getLogger(ShoppingService::class.java)

    private var payed = false;

    private var products: MutableList<ProductModel> = mutableListOf()
    private var orders: MutableList<ProductModel> = mutableListOf()

    fun hasPendingOrders(): Boolean {
        return orders.isNotEmpty()
    }

    fun orderIsStillPendingPayment(): Boolean {
        return payed
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

    fun showProduct(id: String): ProductModel? {
        return products.find { it.id == id }
    }

    fun addToOrder(product: Product) {
        val model = products.find { it.id == product.id }
        if (model != null) {
            orders += model

            logger.info("${model.name} has been added to cart")
        }
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find item with id: ${product.id}")
        }
    }

    fun clearOrder() {
        payed = false
        orders.clear()
    }

    fun getOrderedItems(): MutableList<ProductModel> {
        return orders
    }

    fun paymentSuccess() {
        payed = true
    }
}