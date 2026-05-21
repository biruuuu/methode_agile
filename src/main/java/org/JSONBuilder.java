package org;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
import java.io.InputStream;

public class JSONBuilder {

    private String path;

    public JSONBuilder(String path) {
        this.path = path;
    }

    public void init() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream inputStream = JSONBuilder.class.getResourceAsStream(this.path);

            if (inputStream == null) {
                System.out.println("Error: data.json file not found in resources!");
                return;
            }

            // 3. Automatically map the JSON structure into our Java Object
            Database db = mapper.readValue(inputStream, Database.class);

            System.out.println("JSON chargé avec succès depuis " + this.path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
