package com.fdymendo.javeriana.citas.repository

import com.fdymendo.javeriana.citas.entity.CitasEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CitasRepository : JpaRepository<CitasEntity, String> {
    fun getByUserIdOrderByDateDesc(userId: String): List<CitasEntity>
    
}