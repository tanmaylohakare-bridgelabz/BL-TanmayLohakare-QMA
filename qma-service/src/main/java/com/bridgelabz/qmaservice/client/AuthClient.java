package com.bridgelabz.qmaservice.client;

import com.bridgelabz.qmaservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/api/auth/user/{email}")
    User getUserDetails(@PathVariable("email") String email);
}
