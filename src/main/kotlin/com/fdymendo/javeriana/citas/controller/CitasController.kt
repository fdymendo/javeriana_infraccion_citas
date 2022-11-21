package com.fdymendo.javeriana.citas.controller

import com.fdymendo.javeriana.citas.entity.CitasEntity
import com.fdymendo.javeriana.citas.service.ICitasService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/agendamiento/v1")
class CitasController(val iCitasService: ICitasService) {

    @PostMapping
    fun saveCitation(
        @RequestHeader cc: String,
        @RequestHeader td: String,
        @RequestBody cites: CitasEntity
    ) =
        iCitasService.saveItemWIthUser(cites = cites, cc = cc, td = td)

    @GetMapping
    fun getAllCitation(
        @RequestParam cc: String,
        @RequestParam td: String,
    ) = iCitasService.getByUserId(cc = cc, td = td)
}