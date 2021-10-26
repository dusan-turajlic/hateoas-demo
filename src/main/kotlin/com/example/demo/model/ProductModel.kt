package com.example.demo.model

import java.util.*

class ProductModel(var name: String) {
    var id: String = UUID.randomUUID().toString();
}
