package com.dealers.inventory.controller;

import com.dealers.inventory.model.Dealer;
import com.dealers.inventory.service.DealerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/dealers")
@RequiredArgsConstructor
public class DealerController {
    @Autowired
    private  DealerService svc;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Dealer d){
        return ResponseEntity.status(CREATED).body(svc.create(d));
    }

    @GetMapping("/{id}") public Dealer get(@PathVariable UUID id){ return svc.get(id); }

    @GetMapping public Page<Dealer> list(Pageable p){ return svc.list(p); }
}
