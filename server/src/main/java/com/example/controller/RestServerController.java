package com.example.controller;

import com.example.utils.R;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Steven Shi
 * @date 2019/7/14 12:40
 */
@RestController
@RequestMapping("/server")
public class RestServerController {
    @GetMapping("/hello")
    public R restServer(@RequestParam("name") String name) {
        return R.ok().put("name", name);
    }

    @PostMapping("/hello2")
    public R restServer2(@RequestParam("name") String name, @RequestParam("age") String age) {
        return R.ok().put("name", name).put("age", age);
    }

    @PostMapping("/hello3")
    public R restServer3(@RequestBody Map<String, Object> map) {
        return R.ok().put("data", map);
    }
}
