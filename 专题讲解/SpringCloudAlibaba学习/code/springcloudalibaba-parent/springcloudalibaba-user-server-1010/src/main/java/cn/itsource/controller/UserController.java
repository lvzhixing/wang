package cn.itsource.controller;

import cn.itsource.dto.User;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UserController {

    @Value("${temp.testValue}")
    private String testValue;

    @GetMapping("/user/{id}")
    //限流降级
    @SentinelResource(value="getUserById", blockHandler="exceptionHandler", fallback = "getUserByIdFallback")
    public User getById(@PathVariable Long id){
        int i = 1 / 0;	//方法异常，触发熔断
        System.out.println("测试配置testValue=" + testValue);
        return new User(id,"张三:" + id, "我是张三");
    }

    // 熔断降级，参数和返回值与源方法一致
    public User getUserByIdFallback(@PathVariable Long id){
        System.out.println(testValue);
        return new User(id,"zs:" + id, "这是熔断托底数据");
    }

    // 限流与阻塞处理 : 参数要和 被降级的方法参数一样
    public User exceptionHandler(@PathVariable Long id, BlockException ex) {
        ex.printStackTrace();
        System.out.println("限流了...");
        return new User(-1L,"限流了","限流了");
    }
}