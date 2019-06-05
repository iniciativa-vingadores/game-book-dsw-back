package com.initvingadores.gamebook.model

import javax.persistence.*

//@Entity
//@Table
data class User (
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column
    val id: Int,

//    @Column( nullable = false, length = 300)
    val name: String

//  ...
    )