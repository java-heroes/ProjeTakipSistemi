package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.Kullanici;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesajDTO {
    private int id;
    private String baslik;
    private String mesajinIcerigi;
    private Kullanici alici;
    private Kullanici gonderen;

}
