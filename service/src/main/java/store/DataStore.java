package store;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import types.Script;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 *  FireBase DataStore.
 */
public final class DataStore {
    private static final String DATABASE_URL = "https://mysterymurder-jiar.firebaseio.com/";
    private static DatabaseReference database;

    public static final DataStore dataStore = new DataStore();

    public DataStore () {
        try {
            final String accountEnv = System.getenv("FIREBASE_SERVICE_ACCOUNT");

            InputStream serviceAccount = accountEnv == null ?
                new FileInputStream("service-account.json") :                         /* use credential file */
                new ByteArrayInputStream(accountEnv.getBytes(StandardCharsets.UTF_8));  /* use environment var */

            final FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DATABASE_URL)
                .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        database = FirebaseDatabase.getInstance().getReference();
    }

    public <T>ValueEventListener get(final Consumer<T>toResponse, final Class<T> dataClass) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                toResponse.accept(snapshot.getValue(dataClass));
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };

    }
    public void getScript(final String id, final Consumer<Script>toResponse) {
        database
            .child("scripts")
            .child(id)
            .addListenerForSingleValueEvent(get(toResponse, Script.class));
    }
}
