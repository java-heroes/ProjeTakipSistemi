package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciDTO {
    private int id;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String mail;
    private String telefon;
}
