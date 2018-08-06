package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import types.Script;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *  Temporarily DataStore. Need to be replaced by a real database
 */
public class DataStore {
    private Script script;

    public static final DataStore dataStore = new DataStore();

    public DataStore () {
        try {
            InputStream is = new FileInputStream("script.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            JsonObject json = (JsonObject) new JsonParser().parse(jsonTxt);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            script = gson.fromJson(json, Script.class);
        } catch (Exception e) {

        }

    }
    public Script getScript() {
        return script;
    }
}
