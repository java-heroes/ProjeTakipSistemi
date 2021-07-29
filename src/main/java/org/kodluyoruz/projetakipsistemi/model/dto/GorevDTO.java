package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GorevDTO {
    private int id;
    private String gorev;
    private String aciklama;
    private Date baslamaTarihi;
    private Date bitisTarihi;
    private int gorevDurum;
    private int projeId;
    private int personelId;
}
