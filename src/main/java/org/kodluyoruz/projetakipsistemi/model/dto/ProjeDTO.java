package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.SonucRaporu;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjeDTO {
    private int id;
    private String proje;
    private String aciklama;
    private int projeDurum;
    private int takimId;
    private List<GorevDTO> gorevs;
    private List<SonucRaporuDTO> sonucRaporus;
}
