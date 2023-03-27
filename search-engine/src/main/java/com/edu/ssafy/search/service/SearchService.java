package com.edu.ssafy.search.service;

import com.edu.ssafy.search.dto.HitSong;
import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private final Gson gson;

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://j8b304.p.ssafy.io:9200")
            .build();

    public SearchService() {
        this.gson = new Gson();
    }

    public List<HitSong> searchBytitle(String title) {
        String responseBody = webClient.method(HttpMethod.GET)         // POST method
                .uri("/tj_song_list_idx/_search?filter_path=hits.hits.*,aggregations.*")    // baseUrl 이후 uri
                .headers(headers -> headers.setBasicAuth("elastic", "b304b304")) // basic auth
                .acceptCharset(Charset.forName("UTF-8"))
                .contentType(MediaType.APPLICATION_JSON) // json body
                .bodyValue(
                        String.format(
                                "{\n" +
                                        "  \"size\": 10000,\n" +
                                        "  \"query\": {\n" +
                                        "    \"match\": {\n" +
                                        "      \"title\" : \"%s\"\n" +
                                        "    }\n" +
                                        "  }\n" +
                                        "}", title
                        )
                )     // set body value
                .retrieve()                 // client message 전송
                .bodyToMono(String.class)  // body type : EmpInfo
                .block();                   // await


        Map<String, Map<String, List<Map<String, Map<String, Object>>>>> map = gson.fromJson(responseBody, Map.class);
        List<HitSong> list = new LinkedList<>();

        for (int i = 0;i < map.get("hits").get("hits").size();i++) {
            int id = Integer.parseInt((String) map.get("hits").get("hits").get(i).get("_source").get("id"));
            String _title = (String) map.get("hits").get("hits").get(i).get("_source").get("title");
            String singer = (String) map.get("hits").get("hits").get(i).get("_source").get("singer");
            String youtube_url = (String) map.get("hits").get("hits").get(i).get("_source").get("youtube_url");

            list.add(
                    HitSong.builder()
                            .id(id)
                            .title(_title)
                            .singer(singer)
                            .youtube_url(youtube_url)
                            .build());
        }

        list.sort(
                (lhs, rhs) -> {
                    if (lhs.getTitle().length() == rhs.getTitle().length()) return lhs.getTitle().compareTo(rhs.getTitle());
                    return lhs.getTitle().length() - rhs.getTitle().length();
                }
        );


        return list;
    }

    public List<HitSong> searchBysinger(String singer) {
        String responseBody = webClient.method(HttpMethod.GET)         // POST method
                .uri("/tj_song_list_idx/_search?filter_path=hits.hits.*,aggregations.*")    // baseUrl 이후 uri
                .headers(headers -> headers.setBasicAuth("elastic", "b304b304")) // basic auth
                .contentType(MediaType.APPLICATION_JSON) // json body
                .bodyValue(
                        String.format(
                                "{\n" +
                                        "  \"size\": 10000,\n" +
                                        "  \"query\": {\n" +
                                        "    \"match\": {\n" +
                                        "      \"singer\" : \"%s\"\n" +
                                        "    }\n" +
                                        "  }\n" +
                                        "}", singer
                        )
                )     // set body value
                .retrieve()                 // client message 전송
                .bodyToMono(String.class)  // body type : EmpInfo
                .block();                   // await



        Map<String, Map<String, List<Map<String, Map<String, Object>>>>> map = gson.fromJson(responseBody, Map.class);
        List<HitSong> list = new LinkedList<>();

        for (int i = 0;i < map.get("hits").get("hits").size();i++) {
            int id = Integer.parseInt((String) map.get("hits").get("hits").get(i).get("_source").get("id"));
            String title = (String) map.get("hits").get("hits").get(i).get("_source").get("title");
            String _singer = (String) map.get("hits").get("hits").get(i).get("_source").get("singer");
            String youtube_url = (String) map.get("hits").get("hits").get(i).get("_source").get("youtube_url");

            list.add(
                    HitSong.builder()
                            .id(id)
                            .title(title)
                            .singer(_singer)
                            .youtube_url(youtube_url)
                            .build());
        }

        list.sort(
                (lhs, rhs) -> {
                    if (lhs.getTitle().length() == rhs.getTitle().length()) return lhs.getTitle().compareTo(rhs.getTitle());
                    return lhs.getTitle().length() - rhs.getTitle().length();
                }
        );


        return list;
    }
}
