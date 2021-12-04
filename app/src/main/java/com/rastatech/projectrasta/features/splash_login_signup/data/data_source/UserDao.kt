package com.rastatech.projectrasta.features.splash_login_signup.data.data_source

import androidx.room.*
import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE id =:id")
    suspend fun getUserById(id: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(uer:UserEntity)

}