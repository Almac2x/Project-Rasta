package com.rastatech.projectrasta.di

import android.app.Application
import androidx.room.Room
import com.rastatech.projectrasta.features.splash_login_signup.data.data_source.UserDatabase
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepositoryImpl
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.DeleteUser
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.GetSingleUser
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.GetAllUsers
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
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
    fun provideNoteDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getAllUsers = GetAllUsers(repository),
            deleteUser = DeleteUser(repository),
            getUserUse = GetSingleUser(repository),
            //addNote = AddUserUerUsecase(repository),
        )
    }



}