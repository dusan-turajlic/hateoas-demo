package com.example.demo.data

import com.example.demo.models.ProductModel

class ShoppingService {
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
                "Coffee Cup",
                "Ironic T-Shirt",
                "Laptop stickers",
                "Mechanical Keyboard",
                "Intro To Vim Book"
        ).forEach {
            products += ProductModel(name = it)
        }
    }

    fun showProduct(id: String): ProductModel? {
        return products.find { it.id == id }
    }
}