package org.kodluyoruz.projetakipsistemi.controller;


import org.kodluyoruz.projetakipsistemi.model.Teklif;
import org.kodluyoruz.projetakipsistemi.model.dto.TeklifDTO;
import org.kodluyoruz.projetakipsistemi.service.TeklifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teklif/")
public class TeklifController {
    @Autowired
    TeklifService teklifService;

    @GetMapping(value = "teklifMusteri")
    public ResponseEntity<?> teklifMusteri(@RequestBody TeklifDTO teklifDTO){
        return ResponseEntity.ok(teklifService.teklifMusteri(teklifDTO));
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(teklifService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(teklifService.get(id));
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> add(@RequestBody Teklif teklif){
        return ResponseEntity.ok(teklifService.add(teklif));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody TeklifDTO teklifDTO){
        return ResponseEntity.ok(teklifService.update(teklifDTO));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestBody TeklifDTO teklifDTO){
        return ResponseEntity.ok(teklifService.delete(teklifDTO));
    }

}
