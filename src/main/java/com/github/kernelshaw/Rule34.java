package com.github.kernelshaw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;


public class Rule34 {

    private static String baseURL = "https://rule34.xxx/index.php?page=dapi&s=post&q=index";

    public Rule34(){
    }

    public String returnURL(String tag){
        String urlGen = baseURL + "&tags=" + tag.replace(' ', '_');
        String xmlData = getXmlText(urlGen);
        if (empty(xmlData)){
            return "There's nothing here! What a shame.";
        }
        int start = xmlData.indexOf("file_url=") + 10;
        int end = xmlData.indexOf("parent_id=") - 2;
        return xmlData.substring(start, end);
    }

    private String getXmlText(String urlXml) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        String result = null;

        try {
            url = new URL(urlXml);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
        } catch (Exception e) {
            return "There's nothing here! What a shame.";
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {}
        }
        return result;
    }

    public static int randomOne(String toCheck){
        int cur = 0;
        int total = 0;
        while (toCheck.indexOf("file_url=", cur) != -1){
            total++;
            cur = toCheck.indexOf("file_url=", cur) + 1;
        }
        return total;
    }

    public static boolean empty(String XML){
        int pos = XML.indexOf("posts count=\"");
        if (XML.charAt(pos + 13) == '0'){
            return true;
        }
        return false;
    }
}
