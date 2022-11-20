package com.fdymendo.javeriana.citas.service.impl

import com.fdymendo.javeriana.citas.dto.UserDTO
import com.fdymendo.javeriana.citas.dto.toUserRequest
import com.fdymendo.javeriana.citas.entity.CitasEntity
import com.fdymendo.javeriana.citas.model.ResponseDefault
import com.fdymendo.javeriana.citas.model.user.ResponseUser
import com.fdymendo.javeriana.citas.model.user.UserResponse
import com.fdymendo.javeriana.citas.repository.CitasRepository
import com.fdymendo.javeriana.citas.service.ACrudServiceTemplate
import com.fdymendo.javeriana.citas.service.ICitasService
import com.fdymendo.javeriana.citas.utils.GenericMethods
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class CitasServiceImpl(
    private val citasRepository: CitasRepository,
    @Value("\${api.internal.services.users.ip}")
    private val ip: String,
    @Value("\${api.internal.services.users.path.create}")
    private val pathGet: String,
    @Value("\${api.internal.services.users.path.getUser}")
    private val pathCreate: String
) :
    ACrudServiceTemplate<CitasRepository, CitasEntity>(citasRepository),
    ICitasService {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CitasServiceImpl::class.java)
    }

    override fun getByUserId(cc: String, td: String): ResponseEntity<ResponseDefault> {
        logger.info("Search infracction cc: $cc and td: $td")
        val person = getPerson(cc = cc, td = td)
        val cites = citasRepository.getByUserIdOrderByDateDesc(person.id!!)
        return GenericMethods.responseOk(ResponseDefault(cites, null))
    }

    override fun saveItemWIthUser(cites: CitasEntity, cc: String, td: String): ResponseEntity<ResponseDefault> {
        logger.info("Search infracction cc: $cc and td: $td")
        val person = getPerson(cc = cc, td = td)
        cites.createDate = Date()
        cites.userId = person.id!!
        this.citasRepository.save(cites)
        return GenericMethods.responseOk(ResponseDefault(null, cites))
    }

    override fun saveItem(item: CitasEntity): ResponseEntity<ResponseDefault> {
        item.createDate = Date()
        this.citasRepository.save(item)
        return GenericMethods.responseOk(ResponseDefault(null, item))
    }

    override fun updateItem(item: CitasEntity, id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun deleteItem(id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun getItem(id: String): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    override fun allItems(): ResponseEntity<ResponseDefault> {
        TODO("Not yet implemented")
    }

    private fun getPerson(cc: String, td: String) = getPerson(
        UserDTO(
            id = null,
            name = "",
            surname = "",
            email = "",
            typeDocument = td,
            document = cc,
        )
    )

    private fun getPerson(userDTO: UserDTO): UserResponse {
        val urlGet = "${ip}${pathGet}?cc=${userDTO.document}&td=${userDTO.typeDocument}"
        val urlPost = "${ip}${pathCreate}?cc=${userDTO.document}&td=${userDTO.typeDocument}"

        return try {
            val response = RestTemplate().exchange(urlGet, HttpMethod.GET, null, ResponseUser::class.java)
            response.body?.user!!
        } catch (e: Exception) {
            val request = HttpEntity(userDTO.toUserRequest())
            val response = RestTemplate().exchange(urlPost, HttpMethod.POST, request, ResponseUser::class.java)
            response.body?.user!!
        }
    }
}