package com.example.mytestapp.di

import android.content.Context
import androidx.room.Room
import com.example.mytestapp.data.*
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
    fun provideItemsDao(itemDatabase: ItemDataBase): ItemDatabaseDao
            = itemDatabase.itemDao()

    @Singleton
    @Provides
    fun provideDessertDao(itemDatabase: ItemDataBase): DessertDataBaseDao
            = itemDatabase.dessertDao()

    @Singleton
    @Provides
    fun provideDrinksDao(itemDatabase: ItemDataBase): DrinksDataBaseDao
            = itemDatabase.drinksDao()

    @Singleton
    @Provides
    fun provideTableDao(itemDatabase: ItemDataBase): TableDataBaseDao
            = itemDatabase.tableDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ItemDataBase
            = Room.databaseBuilder(
        context,
        ItemDataBase::class.java,
        "items_db")
        .fallbackToDestructiveMigration()
        .build()
}