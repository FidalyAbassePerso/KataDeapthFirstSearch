package fr.kata.depth.first.search.depthFirstSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import fr.kata.depth.first.search.depthFirstSearch.entity.Coord;
import fr.kata.depth.first.search.depthFirstSearch.entity.Map;
import fr.kata.depth.first.search.depthFirstSearch.enumeration.EAttributRecherche;
import fr.kata.depth.first.search.depthFirstSearch.service.MapService;
import fr.kata.depth.first.search.depthFirstSearch.service.MazeDeapthSearchService;
import fr.kata.depth.first.search.depthFirstSearch.service.UserInterfaceService;

@SpringBootApplication
@EntityScan(basePackages = {"fr.kata.depth.first.search.depthFirstSearch.entity"})
@ComponentScan(basePackages = {"fr.kata.depth.first.search.depthFirstSearch","fr.kata.depth.first.search.depthFirstSearch.service.impl"} )
public class DepthFirstSearchApplication {
	
	private static int MAX_ROW = -1;
	
	private static int MAX_COL = -1;
		
	
	private static MapService mapService;
	
	
	private static MazeDeapthSearchService mazeDeapthSearchService;
	
	private static UserInterfaceService userInterfaceService;
	
    private static ApplicationContext context;

	

	public static void main(String[] args) {
		
		context = SpringApplication.run(DepthFirstSearchApplication.class, args);
		
        //on instancie nos services en s'appuyant sur l'ApplicationContext car à ce moment les composants ne sont pas tous chargés
		userInterfaceService = (UserInterfaceService) context.getBean("userInterfaceService");

		
		Scanner sc= new Scanner(System.in);    
		
		while (MAX_ROW <= 0) {
			
			MAX_ROW = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de ligne pour le labyrinthe supérieur à 0 : \n");
		}
		
						
		while (MAX_COL <= 0) {
			
			MAX_COL = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Entrez un nombre de colonnes pour le labyrinthe supérieur à 0 : \n");
		}
		
		
		System.out.print("Souhaitez-vous connaître la position de depart ou les positions de sorties ? \n");
		int recherche = 0;
		
		while (recherche != EAttributRecherche.ENTREE.getValeur() && recherche != EAttributRecherche.SORTIE.getValeur()) {
			
			recherche = userInterfaceService.demanderValeurEntierAUtilisateur(sc, "Tapez 1 pour la position de départ ou -1 pour les positions de sorties : \n");;
			
		}
		
		
        //on instancie nos services en s'appuyant sur l'ApplicationContext car à ce moment les composants ne sont pas tous chargés
        mapService = (MapService) context.getBean("mapService");
        mazeDeapthSearchService = (MazeDeapthSearchService) context.getBean("mazeDeapthSearchService");
        
        String typeRecherche = recherche == EAttributRecherche.ENTREE.getValeur() ? "entrées" : "sorties";

		Map map = mapService.creerMap(MAX_ROW, MAX_COL);
		
		map.toString();
		
		System.out.println("Voici les positions des "+typeRecherche+" recherchés dans la map : " + mazeDeapthSearchService.chercherPositionSortieOuEntree(map.getPositionDepart(), recherche,new ArrayList<>(), new ArrayList<>(), map));
		
		
		// On arrete le programme à la fin
		SpringApplication.exit(context, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
			        return 0;
			    }
			});
	}


}
