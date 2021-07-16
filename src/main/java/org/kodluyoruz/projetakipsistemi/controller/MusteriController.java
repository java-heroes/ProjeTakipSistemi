package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/musteri/")
public class MusteriController {

    @Autowired
    MusteriService musteriService;

    @PostMapping(value = "kayit")
    public ResponseEntity<?> kayit(@RequestBody Musteri musteri){
        return ResponseEntity.ok(musteriService.kayit(musteri));
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(musteriService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(musteriService.get(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody Musteri musteri){
        return ResponseEntity.ok(musteriService.update(musteri));
    }

}
