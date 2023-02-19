package dev.amal.mongodbrealm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.mongodbrealm.data.MongoRepository
import dev.amal.mongodbrealm.data.MongoRepositoryImpl
import dev.amal.mongodbrealm.model.Address
import dev.amal.mongodbrealm.model.Person
import dev.amal.mongodbrealm.model.Pet
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(Person::class, Address::class, Pet::class)
        )
            .compactOnLaunch()
//            .initialData()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }
}