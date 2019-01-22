package ul.fr.miage.rue.HelloFX;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App2 extends Application {
	
	private static final Logger LOG = Logger.getLogger(App.class.getName());
	
	private String filename;
	
	//Méthode permettant de créer le reader CSV
	public CSVParser buildCSVParser() throws IOException {
		CSVParser res = null;
		Reader in;
		in = new FileReader(filename);
		CSVFormat csvf = CSVFormat.DEFAULT.withCommentMarker('#').withDelimiter(';');
		res = new CSVParser(in, csvf);
		return res;
	}

	//Mise en place l'interface graphique
	@Override
	public void start(Stage primaryStage) {
		BorderPane blayout = new BorderPane();
		TextArea ta = new TextArea();
		Button btn = new Button("Lire le fichier");
		btn.setOnAction((e) -> {this.click(ta);;});
		blayout.setBottom(btn);
		blayout.setCenter(ta);
		BorderPane.setAlignment(btn, Pos.BOTTOM_CENTER);
		Scene scene = new Scene(blayout, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lecture fichier CSV");
		primaryStage.show();
		
	}
	
	//Méthode permettant de lire le fichier CSV
	public void click(TextArea ta) {
		filename = "names.csv";
		try {
        	CSVParser p = this.buildCSVParser();
        	for(CSVRecord r: p) {
        		ta.appendText("Hello " + r.get(0) + " " + r.get(1) + " !\n");
        		
        	}
        }catch (IOException e) {
        	LOG.severe("Erreur de lecture dans le fichier CSV");
        }
	}

	public static void main(String[] args) {
		launch(args);
        
    }
}
