package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.Musteri;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjeTaslagiDTO {
    private int id;
    private String taslak;
    private String asama;
    private int musteriId;
}
