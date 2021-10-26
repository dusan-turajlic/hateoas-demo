package com.example.demo.resource

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Payment(
        var name: String
) : RepresentationModel<Payment>()