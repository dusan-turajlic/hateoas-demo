package com.example.demo.models

import java.util.*

class ProductModel(var name: String) {
    public var id: String = UUID.randomUUID().toString();
}
