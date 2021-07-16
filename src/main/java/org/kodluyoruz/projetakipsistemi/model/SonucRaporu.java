package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sonuc_raporu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonucRaporu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "aciklama")
    private String aciklama;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proje_id", referencedColumnName = "id")
    private Proje proje;
}
