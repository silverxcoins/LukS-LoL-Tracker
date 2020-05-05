package ru.mobile.lukslol.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.service.db.Database
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDb(context: Context) = Room.databaseBuilder(context, Database::class.java, "luksDatabase").build();
}