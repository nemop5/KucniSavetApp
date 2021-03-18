package com.ftninformatika.jwd.web.dto;


public class GlasDTO {

    private Long id;

    private String predlogPodrzan;
    
    private String komentar;

    private Long porukaId;
    
    private String porukaNaslov;
    
    
    public GlasDTO() {
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

	public Long getPorukaId() {
		return porukaId;
	}

	public void setPorukaId(Long porukaId) {
		this.porukaId = porukaId;
	}

	public String getPorukaNaslov() {
		return porukaNaslov;
	}

	public void setPorukaNaslov(String porukaNaslov) {
		this.porukaNaslov = porukaNaslov;
	}

    

}
