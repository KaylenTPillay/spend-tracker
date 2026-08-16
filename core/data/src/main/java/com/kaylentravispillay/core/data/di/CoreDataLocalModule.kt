package com.kaylentravispillay.core.data.di

import android.content.Context
import androidx.room3.Room
import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.database.TrackerDatabase
import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.database.daos.TransactionDao
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.data.local.source.TransactionLocalSource
import com.kaylentravispillay.core.data.local.source.impl.CategoryLocalSourceImpl
import com.kaylentravispillay.core.data.local.source.impl.TransactionLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataLocalModule {
    @Provides
    @Singleton
    internal fun providesTrackerDatabase(
        @ApplicationContext context: Context
    ): TrackerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TrackerDatabase::class.java,
            name = TrackerDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    internal fun providesTransactionDao(
        database: TrackerDatabase
    ): TransactionDao {
        return database.getTransactionDao()
    }

    @Provides
    internal fun providesCategoryDao(
        database: TrackerDatabase
    ): CategoryDao {
        return database.getCategoryDao()
    }

    @Provides
    @Singleton
    internal fun providesTransactionLocalSource(
        dao: TransactionDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): TransactionLocalSource {
        return TransactionLocalSourceImpl(
            transactionDao = dao,
            dispatcher = dispatcher
        )
    }

    @Provides
    @Singleton
    internal fun providesCategoryLocalSource(
        dao: CategoryDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CategoryLocalSource {
        return CategoryLocalSourceImpl(
            categoryDao = dao,
            dispatcher = dispatcher
        )
    }
}
