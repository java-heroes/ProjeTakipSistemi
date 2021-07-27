package org.kodluyoruz.projetakipsistemi.controller;

import org.kodluyoruz.projetakipsistemi.core.exception.UserAlreadyException;
import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.kodluyoruz.projetakipsistemi.service.YoneticiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/yonetici/")
public class YoneticiController {

    @Autowired
    YoneticiService yoneticiService;

    @PostMapping(value = "kayit")
    public ResponseEntity<?> kayit(@RequestBody Yonetici yonetici) throws UserAlreadyException {
        return ResponseEntity.ok(yoneticiService.kayit(yonetici));
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(yoneticiService.getAll());
    }

    @GetMapping(value = "get")
    public ResponseEntity<?> get(@RequestParam int id){
        return ResponseEntity.ok(yoneticiService.get(id));
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> update(@RequestBody Yonetici yonetici){
        return ResponseEntity.ok(yoneticiService.update(yonetici));
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        return ResponseEntity.ok(yoneticiService.delete(id));
    }

    @GetMapping(value = "login")
    public ResponseEntity<?> login(@RequestParam("kullaniciAdi") String kullaniciAdi, @RequestParam("sifre") String sifre){
        return  ResponseEntity.ok(yoneticiService.login(kullaniciAdi, sifre));
    }

    @GetMapping(value = "forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String mail){
        return ResponseEntity.ok(yoneticiService.forgotPassword(mail));
    }


}
