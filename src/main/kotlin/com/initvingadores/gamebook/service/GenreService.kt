package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.genre.DetailGenreDTO

interface GenreService {

    fun list (size: Int, page: Int, name: String?) : List<DetailGenreDTO>

    fun detail (idGenre: Long) : DetailGenreDTO
}