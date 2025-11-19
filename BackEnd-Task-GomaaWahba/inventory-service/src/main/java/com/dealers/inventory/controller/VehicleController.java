package com.dealers.inventory.controller;

import com.dealers.inventory.model.Vehicle;
import com.dealers.inventory.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    @Autowired
    private  VehicleService svc;

    @PostMapping  public ResponseEntity<?> create(@RequestBody Vehicle v){
        v.setTenantId(com.dealers.inventory.tenant.TenantContext.get());
        return ResponseEntity.status(CREATED).body(svc.create(v));
    }

    @GetMapping("/{id}") public Vehicle get(@PathVariable UUID id)
      {
        return svc.get(id);
      }

    @GetMapping public Page<Vehicle> search(@RequestParam(required = false) String model,
                                           @RequestParam(required = false) Vehicle.Status status,
                                           @RequestParam(required = false) Double min,
                                           @RequestParam(required = false) Double max,
                                           Pageable p){
        return svc.search(model,status,min,max,p);
    }

    @GetMapping("/premium") public List<Vehicle> premium(){ return svc.premium(); }
}
