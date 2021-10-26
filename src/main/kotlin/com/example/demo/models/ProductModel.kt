package com.example.demo.models

import java.util.*

class ProductModel(var name: String) {
    var id: String = UUID.randomUUID().toString();
}
