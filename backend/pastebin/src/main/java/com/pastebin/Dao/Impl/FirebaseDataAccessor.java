package com.pastebin.Dao.Impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.pastebin.Dao.DataBaseAccessor;
import com.pastebin.enums.Response;
import com.pastebin.pojo.Paste;
import com.pastebin.pojo.PasteResponse;
import com.pastebin.pojo.PasteResponse.PasteResponseBuilder;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FirebaseDataAccessor implements DataBaseAccessor {

    @Autowired
    private CollectionReference dbRef;

    @Override
    public PasteResponse getPaste(@NonNull String id) {
        try {
            final DocumentReference docRef = dbRef.document(id);
            final ApiFuture<DocumentSnapshot> future = docRef.get();
            final DocumentSnapshot document = future.get();
            if (document.exists()) {
                final Paste response = document.toObject(Paste.class);
                log.info("Document data: " + response);
                return PasteResponse.builder()
                        .response(Response.SUCCESS)
                        .responseTime(System.currentTimeMillis())
                        .content(response)
                        .build();
            } else {
                log.info("No such document!");
            }
        } catch (InterruptedException e) {
            log.error("Write operation interrupted: {}", e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("Error occured while executing write request: {}", e);
            throw new RuntimeException(e);
        }
        return PasteResponse.builder()
                .response(Response.DATA_UNAVAILABLE)
                .responseTime(System.currentTimeMillis())
                .build();
    }

    @Override
    public Response createPaste(@NonNull final Paste pasteData) throws RuntimeException {
        try {
            log.info("Writing to firebase with id {}", pasteData.getUniqueId());
            final ApiFuture<WriteResult> future = dbRef.document(pasteData.getUniqueId())
                                        .set(pasteData);
            log.info("Successfully wrote the data at timestamp: {}", future.get().getUpdateTime());
        } catch (InterruptedException e) {
            log.error("Write operation interrupted: {}", e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("Error occured while executing write request: {}", e);
            throw new RuntimeException(e);
        }
        return Response.SUCCESS;
    }

}
