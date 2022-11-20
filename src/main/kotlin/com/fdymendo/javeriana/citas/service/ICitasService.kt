package com.fdymendo.javeriana.citas.service

import com.fdymendo.javeriana.citas.entity.CitasEntity
import com.fdymendo.javeriana.citas.model.ResponseDefault
import org.springframework.http.ResponseEntity

interface ICitasService : ICrudTemplate<CitasEntity> {
    fun getByUserId(cc: String, td: String): ResponseEntity<ResponseDefault>
    fun saveItemWIthUser(cites: CitasEntity, cc: String, td: String): ResponseEntity<ResponseDefault>

}