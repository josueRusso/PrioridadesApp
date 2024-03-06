package com.ucne.prioridadesapp.di

import android.content.Context
import androidx.room.Room
import com.ucne.prioridadesapp.data.database.PrioridadDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesPrioridadDatabase(@ApplicationContext appContext: Context): PrioridadDatabase =
        Room.databaseBuilder(appContext,PrioridadDatabase::class.java, "Prioridad.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePrioridadDao(db: PrioridadDatabase) = db.prioridadDao()
}
