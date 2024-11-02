package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateData;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateSettings;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public record UserInfoController(UserInfoService userInfoService) {

    @GetMapping
    UserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        System.out.println(user.getUsername());
        return userInfoService.getUserInfo(user.getUsername());
    }

    @PatchMapping("/data/{email}")
    void updateUserInfoData(@PathVariable String email, @RequestBody UserInfoUpdateData userInfoUpdateData) {
        userInfoService.updateUserInfoData(email, userInfoUpdateData);
    }

    @PatchMapping("/settings/{email}")
    void updateUserInfoSettings(@PathVariable String email, @RequestBody UserInfoUpdateSettings userInfoUpdateSettings) {
        userInfoService.updateUserInfoSettings(email, userInfoUpdateSettings);
    }
}