package com.ftninformatika.jwd.web.dto;


public class ZgradaDTO {

    private Long id;
    
    private String adresa;
    
    private String predsednik;
    
    private int brojStanova;
    
    private int brojStanara;
   
    
    
    public ZgradaDTO() {
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


}
