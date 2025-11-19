package com.dealers.inventory.controller;

import com.dealers.inventory.service.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('GLOBAL_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private  DealerService svc;

    @GetMapping("/dealers/countBySubscription")
    public Map<String,Long> count(){ return svc.countBySubscription(); }
}
