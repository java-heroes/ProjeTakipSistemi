package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "proje_taslagi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjeTaslagi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taslak")
    private String taslak;

    @Column(name = "asama")
    private String asama;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musteri_id", referencedColumnName = "id")
    private Musteri musteri;

}
