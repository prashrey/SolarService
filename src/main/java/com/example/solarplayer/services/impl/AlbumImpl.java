package com.example.solarplayer.services.impl;

import com.example.solarplayer.services.Album;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.example.solarplayer.commons.Constants.*;

@Service
public class AlbumImpl implements Album {

    private static final Logger log = LoggerFactory.getLogger(AlbumImpl.class);

    @Override
    public String search(String name) {
        try {
            String URL_ALBUM_SEARCH = API_HOST + "method=album.search&album=" + name + API_KEY_NAME + API_KEY + API_FORMAT;
            HttpResponse response = sendRequest(URL_ALBUM_SEARCH);
            log.info("Response Code : " , response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getInfo(String albumName, String artistName) {
        try {
            String infoUrl = API_HOST + "method=album.getinfo" + API_KEY_NAME + API_KEY + "&artist=" + artistName + "&album="+  albumName + API_FORMAT;
            HttpResponse response = sendRequest(infoUrl);
            log.info("Response Code : " , response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private HttpResponse sendRequest(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            log.info("Sending 'GET' request to URL : " , url);
            return client.execute(request);
        }catch (Exception e){
            log.info("Exception occured while sending request", e);
        }
        return null;
    }

}
