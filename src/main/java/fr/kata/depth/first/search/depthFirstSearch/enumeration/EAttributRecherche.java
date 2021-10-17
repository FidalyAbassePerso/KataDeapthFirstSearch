package fr.kata.depth.first.search.depthFirstSearch.enumeration;

public enum EAttributRecherche {
	
	ENTREE(1,"Entrée"),
	SORTIE(-1,"Sortie");
	
	private Integer valeur;
	private String type;
	
	EAttributRecherche(Integer valeur,String type) {
		
		this.valeur = valeur;
		this.type = type;
	}

	public Integer getValeur() {
		return valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
