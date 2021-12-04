package com.rastatech.projectrasta.features.splash_login_signup.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    //abstract fun projectDao() : WishDao

    companion object{

        const val DATABASE_NAME = "rasta_db"

        }

}