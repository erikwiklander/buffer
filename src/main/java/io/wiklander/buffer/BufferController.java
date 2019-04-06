package io.wiklander.buffer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BufferController {

    private final Buffer<String> buffer = new Buffer<>(10, list -> log.info("FULL list: {}", list));

    @PostMapping("buffer")
    public void pub(@RequestParam String val) {
        buffer.put(val);
    }

}
