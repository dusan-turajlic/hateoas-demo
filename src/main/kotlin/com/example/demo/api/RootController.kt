package com.example.demo.api

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.mediatype.hal.HalModelBuilder
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class RootController {
    @GetMapping()
    fun index(): HttpEntity<RepresentationModel<IndexResource>> {
        val model = HalModelBuilder.halModel()
                .link(WebMvcLinkBuilder.linkTo(RootController::class.java).withSelfRel())

        return ResponseEntity(model.build(), HttpStatus.OK)
    }
}

@Relation(IanaLinkRelations.INDEX_VALUE)
@JsonInclude(JsonInclude.Include.NON_NULL)
class IndexResource : RepresentationModel<IndexResource>()