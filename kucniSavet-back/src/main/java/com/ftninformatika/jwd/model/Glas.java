package com.ftninformatika.jwd.model;

import javax.persistence.*;


@Entity
public class Glas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String predlogPodrzan;
    
    @Column
    private String komentar;

    @ManyToOne(fetch=FetchType.EAGER)
    private Poruka poruka;
    

    public Glas() {
        super();
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getPredlogPodrzan() {
		return predlogPodrzan;
	}

	public void setPredlogPodrzan(String predlogPodrzan) {
		this.predlogPodrzan = predlogPodrzan;
	}
	
	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Poruka getPoruka() {
		return poruka;
	}

	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
		if(!poruka.getGlasovi().contains(this)){
			poruka.getGlasovi().add(this);
		}
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Glas other = (Glas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "Glas [id=" + id + ", predlogPodrzan=" + predlogPodrzan + ", poruka=" + poruka + "]";
	}




}
