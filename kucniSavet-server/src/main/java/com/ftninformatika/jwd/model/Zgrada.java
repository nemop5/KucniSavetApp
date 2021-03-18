package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Zgrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String adresa;

    @Column(nullable = false)
    private String predsednik;
    
    @Column
    private int brojStanova;
    
    @Column(nullable = false)
    private int brojStanara;
    
    @OneToMany(mappedBy = "zgrada", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Poruka> poruke = new ArrayList<>();
    
    
    
    public Zgrada() {}

    public Zgrada(Long id, String adresa, String predsednik, int brojStanova, int brojStanara) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.predsednik = predsednik;
		this.brojStanova = brojStanova;
		this.brojStanara = brojStanara;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPredsednik() {
		return predsednik;
	}

	public void setPredsednik(String predsednik) {
		this.predsednik = predsednik;
	}

	public int getBrojStanova() {
		return brojStanova;
	}

	public void setBrojStanova(int brojStanova) {
		this.brojStanova = brojStanova;
	}

	public int getBrojStanara() {
		return brojStanara;
	}

	public void setBrojStanara(int brojStanara) {
		this.brojStanara = brojStanara;
	}
	
	public List<Poruka> getPoruke() {
		return poruke;
	}

	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}
	
	public void dodajPoruke(Poruka poruka) {
		if(poruka.getZgrada() != this) {
			poruka.setZgrada(this);
		}
		poruke.add(poruka);
	}

	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Zgrada other = (Zgrada) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "Zgrada [id=" + id + ", adresa=" + adresa + ", predsednik=" + predsednik + ", brojStanova=" + brojStanova
				+ ", brojStanara=" + brojStanara + "]";
	}

}
