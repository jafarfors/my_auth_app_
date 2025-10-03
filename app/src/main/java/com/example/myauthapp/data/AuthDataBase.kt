package com.example.myauthapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AuthDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun provideAuthDataBse(app: Application) : AuthDatabase{
    return Room.databaseBuilder(
        app,
        AuthDatabase::class.java,
        "users.db"
    ).build()
}

fun provideUserDao(database: AuthDatabase): UserDao {
    return database.userDao()
}
