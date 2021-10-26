package com.example.demo.data

import com.example.demo.models.ProductModel
import com.example.demo.resources.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ShoppingService {
    private val logger: Logger = LoggerFactory.getLogger(ShoppingService::class.java)

    private var cartItems: List<ProductModel> = mutableListOf<ProductModel>()
    private var products: List<ProductModel> = mutableListOf<ProductModel>()

    fun itemsInCart(): Int {
        return cartItems.size;
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

    fun addToCart(product: Product) {
        val model = products.find { it.id == product.id }
        if (model != null) {
            cartItems += model

            logger.info("${model.name} has been added to cart")
        }
    }
}