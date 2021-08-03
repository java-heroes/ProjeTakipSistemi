package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.model.dto.MesajDto;
import org.kodluyoruz.projetakipsistemi.service.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mesaj/")
public class MesajController {
    @Autowired
    private MesajService mesajService ;
    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(mesajService.getAll());
    }
    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody MesajDto mesajDto) {
        return ResponseEntity.ok(mesajService.add(mesajDto));
    }

}
