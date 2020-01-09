package com.cg.Client;

import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.net.URL;

public class Client {

    public static String postRequest(String body) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL("http://localhost:8008/serve");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        byte[] outputInBytes = body.getBytes("UTF-8");

        OutputStream os = con.getOutputStream();
        os.write( outputInBytes );    
        os.close();

        InputStream in = new BufferedInputStream(con.getInputStream());
        String result = null;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            result = scanner.useDelimiter("\\A").next();
        }

        in.close();
        con.disconnect();

        return result;
    }
}
