package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeklifDTO{
    private int id;
    private String baslik;
    private String aciklama;
    private String teklifDegeri;
}
