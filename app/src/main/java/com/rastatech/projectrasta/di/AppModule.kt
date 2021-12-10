package com.rastatech.projectrasta.di

import android.app.Application
import androidx.room.Room
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.api.WishApi
import com.rastatech.projectrasta.features.main.data.repository.MainRepositoryImp
import com.rastatech.projectrasta.features.main.data.repository.WishRepositoryImp
import com.rastatech.projectrasta.features.main.domain.repository.MainRepository
import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import com.rastatech.projectrasta.features.main.domain.use_case.MainUseCases
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.features.main.domain.use_case.main_use_case.GetOwnProfile
import com.rastatech.projectrasta.features.main.domain.use_case.main_use_case.GetUserBalance
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.CreateAWish
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.GetHomeScreenWishes
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.GetWish
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.LikeAWish
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


    /**
     *
     *
     * Provides User Repository
     */

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao(), retrofit = RetrofitInstance) // add here the retrofit instance
    }

    /**
     * Provides User Use-Cases
     */
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


    /**
     * Provides Wish Dependencies
     */
    @Provides
    @Singleton
    fun provideWishRepository(): WishRepository{
        return WishRepositoryImp(api = RetrofitInstance.wishApi)
    }

    @Provides
    @Singleton
    fun provideWishUseCases(repository: WishRepository): WishUseCases{
        return WishUseCases(
            getWish = GetWish(repository = repository),
            getHomeScreenWishes = GetHomeScreenWishes(repository = repository),
            likeAWish = LikeAWish(repository = repository),
            createAWish = CreateAWish(repository = repository)
        )
    }

    /**
     * Provide Main Feature Dependencies
     */
    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository {
        return MainRepositoryImp(api = RetrofitInstance.mainApi)
    }

    @Provides
    @Singleton
    fun provideMainUseCases(repository: MainRepository): MainUseCases {
        return MainUseCases(
            getOwnProfile = GetOwnProfile(repository = repository),
            getUserBalance = GetUserBalance(repository = repository)
        )
    }



}