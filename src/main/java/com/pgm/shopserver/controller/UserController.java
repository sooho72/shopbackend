package com.pgm.shopserver.controller;

import com.pgm.shopserver.domain.Role;
import com.pgm.shopserver.security.UserPrinciple;
import com.pgm.shopserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @PutMapping("change/{role}")
    public ResponseEntity<Object> changeRole(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable Role role) {
        userService.changeRole(role,userPrinciple.getUsername());
        return ResponseEntity.ok(true);
    }

}
