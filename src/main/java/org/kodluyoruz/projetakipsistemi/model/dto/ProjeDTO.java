package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjeDTO {
    private int id;
    private String proje;
    private String aciklama;
    private int projeDurum;
    private List<GorevDTO> gorevs;
    private SonucRaporuDTO sonucRaporu;
}
