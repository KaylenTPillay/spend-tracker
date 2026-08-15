package com.kaylentravispillay.core.data.di

import android.content.Context
import androidx.room3.Room
import com.kaylentravispillay.core.data.local.database.TrackerDatabase
import com.kaylentravispillay.core.data.local.database.daos.TransactionDao
import com.kaylentravispillay.core.data.local.source.TransactionLocalSource
import com.kaylentravispillay.core.data.local.source.impl.TransactionLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    @Singleton
    internal fun providesTransactionLocalSource(
        dao: TransactionDao
    ): TransactionLocalSource {
        return TransactionLocalSourceImpl(transactionDao = dao)
    }
}
