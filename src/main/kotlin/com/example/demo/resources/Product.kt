package com.example.demo.resources

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "dm:product")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Product(
        var id: String,
        var name: String?
) : RepresentationModel<Product>()