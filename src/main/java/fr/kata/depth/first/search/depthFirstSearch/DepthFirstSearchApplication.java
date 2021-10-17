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
import fr.kata.depth.first.search.depthFirstSearch.service.MapService;
import fr.kata.depth.first.search.depthFirstSearch.service.MazeDeapthSearchService;
import fr.kata.depth.first.search.depthFirstSearch.service.UserInterfaceService;

@SpringBootApplication
@EntityScan(basePackages = {"fr.kata.depth.first.search.depthFirstSearch.entity"})
@ComponentScan(basePackages = {"fr.kata.depth.first.search.depthFirstSearch","fr.kata.depth.first.search.depthFirstSearch.service.impl"} )
public class DepthFirstSearchApplication {
	
	public static int MAX_ROW = -1;
	
	public static int MAX_COL = -1;
		
	
	static MapService mapService;
	
	
	static MazeDeapthSearchService mazeDeapthSearchService;
	
	static UserInterfaceService userInterfaceService;
	
    static ApplicationContext context;

	

	public static void main(String[] args) {
		
		context = SpringApplication.run(DepthFirstSearchApplication.class, args);
		
		userInterfaceService = (UserInterfaceService) context.getBean("userInterfaceService");

		
		Scanner sc= new Scanner(System.in);    
		
		while (MAX_ROW <= 0) {
			
			MAX_ROW = userInterfaceService.demanderValeurAUtilisateur(sc, "Entrez un nombre de ligne pour la labyrinthe supérieur à 0 : \n");
		}
		
						
		while (MAX_COL <= 0) {
			
			MAX_COL = userInterfaceService.demanderValeurAUtilisateur(sc, "Entrez un nombre de colonnes pour la labyrinthe supérieur à 0 : \n");
		}
		
		
		System.out.print("Souhaitez-vous connaître la position de depart ou les positions de sorties ? \n");
		int recherche = 0;
		
		while (recherche != 1 && recherche != -1) {
			
			recherche = userInterfaceService.demanderValeurAUtilisateur(sc, "Tapez 1 pour la position de départ ou -1 pour les positions de sorties : \n");;
			
		}
		
		
        
        mapService = (MapService) context.getBean("mapService");
        mazeDeapthSearchService = (MazeDeapthSearchService) context.getBean("mazeDeapthSearchService");
        
        String typeRecherche = recherche == 1 ? "entrées" : "sorties";

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
