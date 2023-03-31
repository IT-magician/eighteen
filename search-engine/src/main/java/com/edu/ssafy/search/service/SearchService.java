package com.edu.ssafy.search.service;

import com.edu.ssafy.search.dto.SongDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Value("${elasticsearch.max_data}")
    private long max_data_size;

    private final Gson gson;

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://j8b304.p.ssafy.io:9200")
            .build();

    public SearchService() {
        this.gson = new Gson();
    }

    public List<SongDTO> searchBytitle(String title) {

        System.out.println("size : " + getTotalSongCount("tj_song_list_idx"));
        System.out.println(
                String.format(
                        "{\n" +
                                "  \"size\": " + max_data_size + ",\n" +
                                "  \"query\": {\n" +
                                "    \"match\": {\n" +
                                "      \"title\" : \"%s\"\n" +
                                "    }\n" +
                                "  }\n" +
                                "}", title
                ));

        String responseBody = webClient.method(HttpMethod.GET)         // POST method
                .uri("/tj_song_list_idx/_search?filter_path=hits.hits.*,aggregations.*")    // baseUrl 이후 uri
                .headers(headers -> headers.setBasicAuth(username, password)) // basic auth
                .acceptCharset(Charset.forName("UTF-8"))
                .contentType(MediaType.APPLICATION_JSON) // json body
                .bodyValue(
                        String.format(
                                "{\n" +
                                        "  \"size\": " + max_data_size + ",\n" +
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
        List<SongDTO> list = new LinkedList<>();

        for (int i = 0;i < map.get("hits").get("hits").size();i++) {
            int id = Integer.parseInt((String) map.get("hits").get("hits").get(i).get("_source").get("id"));
            String _title = (String) map.get("hits").get("hits").get(i).get("_source").get("title");
            String singer = (String) map.get("hits").get("hits").get(i).get("_source").get("singer");
            String youtube_url = (String) map.get("hits").get("hits").get(i).get("_source").get("youtube_url");

            list.add(
                    SongDTO.builder()
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

    public List<SongDTO> searchBysinger(String singer) {
        String responseBody = webClient.method(HttpMethod.GET)         // POST method
                .uri("/tj_song_list_idx/_search?filter_path=hits.hits.*,aggregations.*")    // baseUrl 이후 uri
                .headers(headers -> headers.setBasicAuth(username, password)) // basic auth
                .contentType(MediaType.APPLICATION_JSON) // json body
                .bodyValue(
                        String.format(
                                "{\n" +
                                        "  \"size\": " + max_data_size + ",\n" +
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
        List<SongDTO> list = new LinkedList<>();

        for (int i = 0;i < map.get("hits").get("hits").size();i++) {
            int id = Integer.parseInt((String) map.get("hits").get("hits").get(i).get("_source").get("id"));
            String title = (String) map.get("hits").get("hits").get(i).get("_source").get("title");
            String _singer = (String) map.get("hits").get("hits").get(i).get("_source").get("singer");
            String youtube_url = (String) map.get("hits").get("hits").get(i).get("_source").get("youtube_url");

            list.add(
                    SongDTO.builder()
                            .id(id)
                            .title(title)
                            .singer(_singer)
                            .youtube_url(youtube_url)
                            .build());
        }

        list.sort(
                (lhs, rhs) -> {
                    if (lhs.getSinger().length() == rhs.getSinger().length()) return lhs.getSinger().compareTo(rhs.getSinger());
                    return lhs.getSinger().length() - rhs.getSinger().length();
                }
        );


        return list;
    }

    public Long getTotalSongCount(String idx_name) {
        String responseBody = webClient.method(HttpMethod.GET)         // POST method
                .uri("/"+ idx_name + "/_stats")    // baseUrl 이후 uri
                .headers(headers -> headers.setBasicAuth(username, password)) // basic auth
                .acceptCharset(Charset.forName("UTF-8"))
                .contentType(MediaType.APPLICATION_JSON) // json body
                .retrieve()                 // client message 전송
                .bodyToMono(String.class)  // body type : EmpInfo
                .block();                   // await

        Map<String, Map<String, List<Map<String, Map<String, Object>>>>> map = gson.fromJson(responseBody, Map.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> docs = objectMapper.convertValue(map.get("_all").get("primaries"), Map.class);
        docs = objectMapper.convertValue(docs.get("docs"), Map.class);

        return Math.round((Double) docs.get("count"));
    }

}
