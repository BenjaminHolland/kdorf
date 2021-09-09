package land.generic.kdorf.modules.firestore

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FirestoreModule {
    private val projectId = "dorfbot-chainlink"
    @Provides
    @Singleton
    fun provideFirestore():Firestore{
        val firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
            .setProjectId(projectId)
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .build()
        return firestoreOptions.service
    }
}