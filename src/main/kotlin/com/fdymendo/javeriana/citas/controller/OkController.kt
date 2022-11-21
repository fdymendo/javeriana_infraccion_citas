package com.fdymendo.javeriana.citas.controller

import com.fdymendo.javeriana.citas.utils.GenericMethods
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/agendamiento")
class OKController {
    @GetMapping
    fun saveInfraction() = GenericMethods.responseOk()

}