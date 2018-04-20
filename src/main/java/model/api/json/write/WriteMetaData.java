/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.json.write;

import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.CipherOutputStream;
import model.api.json.model.Metadata;
import model.api.security.Encryption;

/**
 *
 * @author User
 */
public class WriteMetaData implements Serializable {

    /**
     * Make a directory in User Document directory to store JSON file.
     */
    private static void make_temp_dir() {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing";
        File documents_dir = new File(path);
        if (documents_dir.exists()) {
            System.out.println("Directory has already existed");
        } else if (documents_dir.mkdirs()) {
            System.out.println("Directory was created successfully.");
        } else {
            System.out.println("Error");
        }
    }
    
     /**
     * Write Metadata object to the JSON writer stream.
     *
     * @param writer
     * @param data
     */
    private static void write_user_data_object(JsonWriter writer, Metadata data) {
        try {
            writer.beginObject();
            writer.name("user_id").value(data.get_users_id());
            writer.name("username").value(data.get_username());
            writer.endObject();
        } catch (IOException ex) {
            Logger.getLogger(WriteMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write Metadata object to the output stream
     *
     * @param out
     * @param data
     */
    private static void write_JSON_user_data_stream(OutputStream out, Metadata data) {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"))) {
            writer.setIndent("    ");
            writer.setSerializeNulls(true);
            writer.setHtmlSafe(true);
            write_user_data_object(writer, data);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WriteMetaData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write Metadata object to the JSON file with AES encryption.
     *
     * @param data
     */
    public static void write_JSON_user_data_file(Metadata data) {
        try {
            String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "data.json";
            File json_file = new File(path);
            Files.deleteIfExists(json_file.toPath());
            if (json_file.createNewFile()) {               
                FileOutputStream fos = new FileOutputStream(json_file);                
                CipherOutputStream cos = new CipherOutputStream(fos, Encryption.get_encrypt_cipher());
                write_JSON_user_data_stream(cos, data);
                System.out.println("Data has been added.");
            } else {
                System.out.println("Unknown Error");
            }
        } catch (IOException ex) {
            Logger.getLogger(WriteMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
