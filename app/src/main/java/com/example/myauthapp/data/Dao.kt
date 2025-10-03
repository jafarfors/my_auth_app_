package com.example.myauthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert()
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY createdAt DESC LIMIT 1")
    fun getLatestUser(): Flow<UserEntity?>

    @Query("DELETE FROM users")
    suspend fun clearUsers()
}