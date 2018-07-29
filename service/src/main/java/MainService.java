import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import types.Script;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainService {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("script.json");
        String jsonTxt = IOUtils.toString(is, "UTF-8");
        JsonObject json = (JsonObject) new JsonParser().parse(jsonTxt);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Script script = gson.fromJson(json, Script.class);
        for (String s: script.getPlayers().get(0).getMission()) {
            System.out.println(s);
        }

        System.out.println(script.getNpcs().get(0).getDescription());

    }
}
