package com.fdymendo.javeriana.citas.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fdymendo.javeriana.citas.entity.CitasEntity

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseDefault(
    var listCitation: List<CitasEntity>?,
    var cita: CitasEntity?
)