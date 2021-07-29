package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonucRaporuDTO {
    private int id;
    private String aciklama;
    private int projeId;
}
