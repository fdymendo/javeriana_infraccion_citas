package com.fdymendo.javeriana.citas.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "citation")
data class CitasEntity(
    @Id var id: String? = UUID.randomUUID().toString(),
    val date: String,
    val description: String,
    var userId: String?,
    var createDate: Date?,
)