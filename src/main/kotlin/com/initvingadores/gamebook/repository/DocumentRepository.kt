package com.initvingadores.gamebook.repository

import com.initvingadores.gamebook.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : JpaRepository<Document, Long>