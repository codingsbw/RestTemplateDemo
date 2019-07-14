package com.example.controller;

import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author Steven Shi
 * @date 2019/7/14 12:53
 */
@RestController
@RequestMapping("/client")
public class RestClientController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException ;
     * public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException ;
     * public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException;
     * <p>
     * public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException ;
     * public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;
     * public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException;
     *
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public R restClient(@RequestParam("name") String name) {
        String URL = "http://localhost:8081/server/hello?name={1}";
        return restTemplate.getForObject(URL, R.class, name);
    }

    /**
     * public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException ;
     * public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;
     * public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException ;
     * <p>
     * public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException ;
     * public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;
     * public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException ;
     *
     * @param map
     * @return
     */
    @PostMapping("/hello2")
    public R restCient2(@RequestBody Map<String, Object> map) {
        String URL = "http://localhost:8081/server/hello2";
        URI uri = URI.create(URL);
        //传输的map只能是MultiValueMap
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("name", (String) map.get("name"));
        request.add("age", (String) map.get("age"));
        return restTemplate.postForObject(uri, request, R.class);
    }

    @PostMapping("/hello3")
    public R restCient3(@RequestBody Map<String, Object> map) {
        String URL = "http://localhost:8081/server/hello3";
        URI uri = URI.create(URL);
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //RestTemplate带参传的时候要用HttpEntity<?>对象传递
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
        R r = restTemplate.postForObject(uri, request, R.class);
        return r;
    }
}
