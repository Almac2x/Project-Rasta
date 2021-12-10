package com.rastatech.projectrasta.di

import android.app.Application
import androidx.room.Room
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.splash_login_signup.data.data_source.UserDatabase
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.LoginApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.SignUpApi
import com.rastatech.projectrasta.features.splash_login_signup.data.repository.UserRepositoryImpl
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * One Model Per Feature
 * According to Philipp Lackner Each Feature can have its own Module = Video(43:10)
 *
 *
 * This App Module is for features/splash_login_signup
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao(), retrofit = RetrofitInstance) // add here the retrofit instance
    }


    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getAllUsers = GetAllUsers(repository),
            deleteUser = DeleteUser(repository),
            getSingleUser = GetSingleUser(repository),
            addUser = AddUser(repository),
            addUserApiRequest = AddUserApiRequest(repository),
            getLoginTokenApiRequest = GetLoginTokenApiRequest(repository)
        )
    }



}