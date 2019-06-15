package com.initvingadores.gamebook.controller

import com.initvingadores.gamebook.dto.genre.DetailGenreDTO
import com.initvingadores.gamebook.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/genres")
class GenreController {

    @Autowired
    lateinit var genreService: GenreService

    @GetMapping
    fun listGenres (
            @RequestParam(required = false, defaultValue = "20") size: Int,
            @RequestParam(required = false, defaultValue = "0") page: Long,
            @RequestParam(required = false) query: String?
    ) : ResponseEntity<List<DetailGenreDTO>> {

        return ResponseEntity(genreService.list(size, page, query), HttpStatus.OK)
    }
}