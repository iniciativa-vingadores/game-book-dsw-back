package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.genre.DetailGenreDTO
import com.initvingadores.gamebook.repository.GenreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl : GenreService {

    @Autowired
    lateinit var genreRepository: GenreRepository

    override fun list(size: Int, page: Long, query: String?): List<DetailGenreDTO> {
        TODO("not implemented")
    }

    override fun detail(idGenre: Long): DetailGenreDTO {
        TODO("not implemented")
    }
}