package com.pastebin.configuration;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.pastebin.Dao.Impl.FirebaseDataAccessor;

import lombok.extern.log4j.Log4j2;

@Configuration
@PropertySource("classpath:application.properties")
@Log4j2
public class DaoConfig {

    @Value("${firebase.database.url}")
    private String dbUrl;

    @Value("${firebase.database.pathPrefix}")
    private String pathPrefix;

    @Value("${firebase.database.credential.path}")
    private String crdentialPath;

    @Bean
    public FirebaseDataAccessor getFirebaseDataAccessor() {
        return new FirebaseDataAccessor();
    }

    @Bean
    public CollectionReference getDbRef() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(getOptions());
        }
        final Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(pathPrefix);
    }

    private FirebaseOptions getOptions() throws IOException {
        log.info("Path: {}", crdentialPath);
        FileInputStream serviceAccount = new FileInputStream(crdentialPath);
        // Initialize the app with a service account, granting admin privileges
        return FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(dbUrl)
                .build();
    }
}
