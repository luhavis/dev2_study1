package com.luhavis.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AuthUtil {

    @Value("${google.auth.clientId}")
    private String clientId;

    @Value("${google.auth.clientSecret}")
    private String clientSecret;

    public AuthUtil() {

    }

    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
    private static String CLIENT_ID = "UlAHKcKy5rPFZH9Mm_8Z";
    private static String CLIENT_SECRET = "QfR_JNVo0E";


    /** Naver 사용자 Access token 요청
     *  @see <a href="https://developers.naver.com/docs/login/web/#1-5--%EC%A0%91%EA%B7%BC-%ED%86%A0%ED%81%B0-%EC%9A%94%EC%B2%AD">네이버 개발자 Docs<a>
     * */
    public static Map<String, String> getNaverAccessToken(String state, String code) throws Exception {
        URL url = new URL("https://nid.naver.com/oauth2.0/token?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&grant_type=authorization_code&state="+state+"&code="+code);

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> m = mapper.readValue(response.toString(), Map.class);

        return m;
    }

    /** Naver 사용자 프로필 정보 조회
     * @see <a href="https://developers.naver.com/docs/login/web/#1-6--%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%82%AC%EC%9A%A9%EC%9E%90-%ED%94%84%EB%A1%9C%ED%95%84-%EC%A0%95%EB%B3%B4-%EC%A1%B0%ED%9A%8C">네이버 개발자 Docs<a>
     * */
    public static Map<String, String> getNaverUserInfo(String accessToken) throws Exception {
        URL url = new URL("https://openapi.naver.com/v1/nid/me");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> m = mapper.readValue(response.toString(), Map.class);

        return m;
    }
}
