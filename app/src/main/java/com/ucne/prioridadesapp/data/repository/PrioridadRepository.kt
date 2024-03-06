package com.ucne.prioridadesapp.data.repository

import com.ucne.prioridadesapp.data.dao.PrioridadDao
import com.ucne.prioridadesapp.data.entity.PrioridadEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PrioridadRepository @Inject constructor(
    private val prioridadDao: PrioridadDao
){
    suspend fun upsert(prioridadEntity: PrioridadEntity){
        prioridadDao.upsert(prioridadEntity)
    }

    suspend fun delete(prioridadEntity: PrioridadEntity){
        prioridadDao.delete(prioridadEntity)
    }

    fun getPrioridad(): Flow<List<PrioridadEntity>>{
         return prioridadDao.getAll()
    }
}


/*
class PrioridadRepository @Inject constructor(
    private val prioridadDao: PrioridadDao
) {
    suspend fun upsert(prioridadEntity: PrioridadEntity) {
        prioridadDao.upsert(prioridadEntity)
    }

    suspend fun delete(prioridadEntity: PrioridadEntity) {
        prioridadDao.delete(prioridadEntity)
    }

    fun getPrioridad(): Flow<List<PrioridadEntity>> {
        return prioridadDao.getAll()
    }
}*/
