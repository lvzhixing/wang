package cn.itsource.feign;

import cn.itsource.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient{
    @Override
    public User getById(Long id) {
        return new User(-1L,"null","这是兜底数据");
    }
}