package com.initvingadores.gamebook.repository

import com.initvingadores.gamebook.model.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag, Long>