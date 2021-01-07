package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Poruka {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naslov;
    
    @Column
    private String tip;
    
    @Column
    private double potrebanProcenat;
    
    @Column
    private boolean prihvacen = false;
    
    @Column(nullable = false)
    private String opis;

    @ManyToOne(fetch=FetchType.EAGER)
    private Zgrada zgrada;
    
    @OneToMany(mappedBy = "poruka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Glas> glasovi = new ArrayList<>();

    
    //Svaki entity mora imati konstruktor bez parametara
    public Poruka() {
        super();
    }

	public Poruka(Long id, String naslov, String tip, double potrebanProcenat, boolean prihvacen, String opis,
			Zgrada zgrada) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.tip = tip;
		this.potrebanProcenat = potrebanProcenat;
		this.prihvacen = prihvacen;
		this.opis = opis;
		this.zgrada = zgrada;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getPotrebanProcenat() {
		return potrebanProcenat;
	}

	public void setPotrebanProcenat(double potrebanProcenat) {
		this.potrebanProcenat = potrebanProcenat;
	}

	public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Zgrada getZgrada() {
		return zgrada;
	}

	public void setZgrada(Zgrada zgrada) {
		this.zgrada = zgrada;
		if(!zgrada.getPoruke().contains(this)){
			zgrada.getPoruke().add(this);
		}
	}
	
	public List<Glas> getGlasovi() {
		return glasovi;
	}

	public void setGlasovi(List<Glas> glasovi) {
		this.glasovi = glasovi;
	}
	
	public void dodajGlasove(Glas glas) {
		if(glas.getPoruka() != this) {
			glas.setPoruka(this);
		}
		glasovi.add(glas);
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Poruka other = (Poruka) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "Poruka [id=" + id + ", naslov=" + naslov + ", tip=" + tip + ", potrebanProcenat=" + potrebanProcenat
				+ ", prihvacen=" + prihvacen + ", opis=" + opis + ", zgrada=" + zgrada + "]";
	}


	


}
