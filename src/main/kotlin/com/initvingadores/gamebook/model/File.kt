package com.initvingadores.gamebook.model

import javax.persistence.*

@Entity
@Table
data class File (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column
        val id: Long,

        val name: String,

        @Lob
        val biteArray: Array<Byte>,

        val length: Long,

        val urlDownload: String
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as File

                if (id != other.id) return false
                if (name != other.name) return false
                if (!biteArray.contentEquals(other.biteArray)) return false
                if (length != other.length) return false
                if (urlDownload != other.urlDownload) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + biteArray.contentHashCode()
                result = 31 * result + length.hashCode()
                result = 31 * result + urlDownload.hashCode()
                return result
        }
}