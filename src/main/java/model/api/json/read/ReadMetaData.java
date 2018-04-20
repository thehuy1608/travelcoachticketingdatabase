/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.json.read;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.CipherInputStream;
import model.api.json.model.Metadata;
import model.api.security.Encryption;

/**
 *
 * @author User
 */
public class ReadMetaData implements Serializable {

    /**
     * Read the Metadata object in JSON file from input stream.
     *
     * @return Metadata data
     */
    public static Metadata read_JSON_user_data_file() {
        try {
            String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "data.json";
            InputStream fis = new FileInputStream(path);
            CipherInputStream cis = new CipherInputStream(fis, Encryption.get_decrypt_cipher());
            ArrayList<Byte> json_data_array_list = new ArrayList<>();
            int next_byte;
            while ((next_byte = cis.read()) != -1) {
                json_data_array_list.add((byte) next_byte);
            }
            byte[] json_data_byte_array = convert_byte_list_to_byte_array(json_data_array_list);

            String json_data_string = new String(json_data_byte_array);

            Gson gson = new Gson();
            gson.serializeNulls();
            Metadata data = gson.fromJson(json_data_string, Metadata.class);

            return data;
        } catch (RuntimeException | IOException e) {
        }
        return null;
    }

    //Simple function to convert an primtive type list to array.
    private static byte[] convert_byte_list_to_byte_array(List<Byte> byte_list) {
        byte[] byte_array = new byte[byte_list.size()];
        for (int i = 0; i < byte_list.size(); i++) {
            byte_array[i] = byte_list.get(i);
        }
        return byte_array;
    }
}
