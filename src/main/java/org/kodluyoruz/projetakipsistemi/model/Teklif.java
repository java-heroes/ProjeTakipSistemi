package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teklif")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teklif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "baslik")
    private String baslik;

    @Column(name = "teklif_degeri")
    private String teklifDegeri;

    @Column(name = "aciklama")
    private String aciklama;

    @ManyToOne()
    @JoinColumn(name="musteri_id", referencedColumnName = "id")
    private Musteri musteri;
}
