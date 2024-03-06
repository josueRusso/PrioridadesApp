package com.ucne.prioridadesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.prioridadesapp.data.dao.PrioridadDao
import com.ucne.prioridadesapp.data.entity.PrioridadEntity


@Database(entities = [PrioridadEntity::class], version = 1)
abstract class PrioridadDatabase: RoomDatabase(){
    abstract fun prioridadDao(): PrioridadDao
}



/*
@Database(
    entities = [PrioridadEntity::class],
    version = 1
)
abstract class PrioridadDatabase : RoomDatabase() {

    abstract fun prioridadDao(): PrioridadDao

}*/
