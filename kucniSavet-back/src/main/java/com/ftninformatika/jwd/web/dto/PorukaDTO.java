package com.ftninformatika.jwd.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PorukaDTO {

    private Long id;
    
    @NotBlank
    private String naslov;

    private String tip;
    
	@Min(0)
	@Max(100)
    private double potrebanProcenat;
    
    private boolean prihvacen;

    private String opis;
  
    private Long zgradaId;
    private String zgradaAdresa;

    public PorukaDTO() {
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

	public Long getZgradaId() {
		return zgradaId;
	}

	public void setZgradaId(Long zgradaId) {
		this.zgradaId = zgradaId;
	}

	public String getZgradaAdresa() {
		return zgradaAdresa;
	}

	public void setZgradaAdresa(String zgradaAdresa) {
		this.zgradaAdresa = zgradaAdresa;
	}
	
}
