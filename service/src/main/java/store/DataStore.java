package store;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import types.Room;
import types.Script;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

/**
 *  FireBase DataStore.
 */
public final class DataStore {
    private static final String DATABASE_URL = "https://mysterymurder-jiar.firebaseio.com/";
    private static DatabaseReference database;

    public static final DataStore dataStore = new DataStore();

    public static final String SCRIPTS = "scripts";
    public static final String ROOMS = "rooms";
    public static final String USERS = "users";

    public static final String SERVICE_ACCOUNT_FILE = "service-account.json";
    public static final String SERVICE_ACCOUNT_ENV = "FIREBASE_SERVICE_ACCOUNT";

    public DataStore () {
        try {
            final String accountEnv = System.getenv(SERVICE_ACCOUNT_ENV);

            InputStream serviceAccount = accountEnv == null ?
                new FileInputStream(SERVICE_ACCOUNT_FILE) :                             /* use credential file */
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
            .child(SCRIPTS)
            .child(id)
            .addListenerForSingleValueEvent(get(toResponse, Script.class));
    }

    public void createRoom(final String id, final Room room, final Consumer<String>toResponse) {
        database.child(ROOMS).child(id).setValue(room, (error, ref) -> toResponse.accept(null));
    }

    public void addGuestToRoom(final String roomId, final String guest, final Consumer<List<String>> toResponse) {
        database
            .child(ROOMS)
            .child(roomId)
            .addListenerForSingleValueEvent(
                get((room -> addGuestToRoom(roomId, guest, room, toResponse)), Room.class));
    }

    private void addGuestToRoom(final String roomId, final String guest,
                                final Room room, final Consumer<List<String>> toResponse) {
        room.getGuests().add(guest);
        database.child(ROOMS).child(roomId).setValue(room, (error, ref) -> toResponse.accept(room.getGuests()));
    }
}
