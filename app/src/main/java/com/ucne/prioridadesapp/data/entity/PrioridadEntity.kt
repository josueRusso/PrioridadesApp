package com.ucne.prioridadesapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("prioridad")
data class PrioridadEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrioridad: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val plazo: Int = 0,
    val esNulo: Boolean = false
)
