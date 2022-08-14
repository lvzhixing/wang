package cn.itsource.feign;

import cn.itsource.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-server", fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User getById(@PathVariable Long id);
}