package com.initvingadores.gamebook.service

import com.initvingadores.gamebook.dto.genre.DetailGenreDTO
import com.initvingadores.gamebook.model.Genre
import com.initvingadores.gamebook.model.toDetailGenreDTO
import com.initvingadores.gamebook.repository.GenreRepository
import com.initvingadores.gamebook.system.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl : GenreService {

    @Autowired
    lateinit var genreRepository: GenreRepository

    override fun list(size: Int, page: Int, name: String?): List<DetailGenreDTO> {
        val currentPage = genreRepository.findAll()

        var filteredList = currentPage.filter { document ->
            name?.let { it == document.name } ?: true
        }

        val totalPages = (filteredList.size / size)
        filteredList = when (page) {
            totalPages -> filteredList.subList(page * size, (page * size) + filteredList.size % size)
            else -> filteredList.subList(page * size, ((page * size) + (size)))
        }

        return filteredList.map { it.toDetailGenreDTO() }
    }

    override fun detail(idGenre: Long): DetailGenreDTO =
            getGenreById(idGenre).toDetailGenreDTO()

    private fun getGenreById(idGenre: Long): Genre {
        return genreRepository.findById(idGenre)
                .orElseThrow { throw NotFoundException("Gênero não encontrado.") }
    }
}