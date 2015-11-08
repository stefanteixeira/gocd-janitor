package in.ashwanthkumar.gocd.client;

import com.mashape.unirest.http.JsonNode;
import in.ashwanthkumar.utils.io.IO;

import java.io.IOException;
import java.net.URL;

public class TestUtils {
    public static JsonNode readFileAsJSON(String file) throws IOException {
        URL resource = TestUtils.class.getResource(file);
        return new JsonNode(IO.fromFile(resource.getPath()));
    }

    public static String fileName(String resource) {
        return TestUtils.class.getResource(resource).getPath();
    }
}
