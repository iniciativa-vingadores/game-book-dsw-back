package com.initvingadores.gamebook.repository

import com.initvingadores.gamebook.model.Genre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : JpaRepository<Genre, Long>