package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.SonucRaporuDTO;
import org.kodluyoruz.projetakipsistemi.service.SonucRaporuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sonuc_raporu/")
public class SonucRaporuController {
    @Autowired
    SonucRaporuService sonucRaporuService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sonucRaporuService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(sonucRaporuService.get(id));
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody SonucRaporuDTO sonucRaporuDTO){
        return ResponseEntity.ok(sonucRaporuService.add(sonucRaporuDTO));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(sonucRaporuService.delete(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody SonucRaporuDTO sonucRaporuDTO){
        return ResponseEntity.ok(sonucRaporuService.update(sonucRaporuDTO));
    }
}
