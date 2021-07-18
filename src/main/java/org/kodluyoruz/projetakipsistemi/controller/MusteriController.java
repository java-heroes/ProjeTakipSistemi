package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.core.exception.UserAlreadyException;
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
    public ResponseEntity<?> kayit(@RequestBody Musteri musteri) throws UserAlreadyException {
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

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(musteriService.delete(id));
    }

    @GetMapping(value = "login")
    public ResponseEntity<?> login(@RequestParam("kullaniciAdi") String kullaniciAdi, @RequestParam("sifre") String sifre){
        return  ResponseEntity.ok(musteriService.login(kullaniciAdi, sifre));
    }

    @GetMapping(value = "forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String mail){
        return ResponseEntity.ok(musteriService.forgotPassword(mail));
    }

    @GetMapping(value = "musteriTeklifs")
    public ResponseEntity<?> musteriTeklifs(@RequestParam int id){
        return ResponseEntity.ok(musteriService.musteriTeklifs(id));
    }

    @GetMapping(value = "musteriProjeTaslaks")
    public ResponseEntity<?> musteriProjeTaslaks(@RequestParam int id){
        return ResponseEntity.ok(musteriService.musteriProjeTaslaks(id));
    }

}
