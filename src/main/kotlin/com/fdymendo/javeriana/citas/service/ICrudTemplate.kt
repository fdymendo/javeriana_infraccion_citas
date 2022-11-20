package com.fdymendo.javeriana.citas.service

import com.fdymendo.javeriana.citas.model.ResponseDefault
import com.fdymendo.javeriana.infracciones.handlers.ApplicationException
import org.springframework.http.ResponseEntity


interface ICrudTemplate<T> {
    fun saveItem(item: T): ResponseEntity<ResponseDefault>
    @Throws(ApplicationException::class)
    fun updateItem(item: T, id: String): ResponseEntity<ResponseDefault>
    fun deleteItem(id: String): ResponseEntity<ResponseDefault>
    fun getItem(id: String): ResponseEntity<ResponseDefault>
    fun allItems(): ResponseEntity<ResponseDefault>
}
