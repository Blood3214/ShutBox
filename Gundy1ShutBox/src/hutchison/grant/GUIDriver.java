package hutchison.grant;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	Die d1 = new Die();
	Die d2 = new Die();

	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label title = new Label("Shut The Box");
		vbox.getChildren().add(title);

		HBox tileBox = new HBox(10);

		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tileBtns[i].setStyle("-fx-background-color:lightgrey");
			tiles[i] = new Tile(i + 1);
			tileBox.getChildren().add(tileBtns[i]);
		}
		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL 1 DICE");
		Button btnRoll2 = new Button("ROLL 2 DICE");
		Button btnlockIn = new Button("LOCK IN");
		Label result = new Label("Result");
		Label lblValue = new Label(); // output of results
		vbox.getChildren().addAll(btnRoll2, btnRoll, result, lblValue, btnlockIn);
		vbox.setAlignment(Pos.CENTER);

		btnRoll.setOnAction(e -> {
			lblValue.setText(String.valueOf(d1.roll()));
		});
		btnRoll2.setOnAction(e -> {
			lblValue.setText(String.valueOf(d2.roll() + d1.roll()));
		});
		
		
		for (Button b : tileBtns) {
			b.setOnAction(e -> {
				if (b.getStyle().equals("-fx-background-color:lightgrey")) {
					b.setStyle("-fx-background-color:lightblue");
				} else {
					b.setStyle("-fx-background-color:lightgrey");
				}
			});
		}
		
		btnlockIn.setOnAction(e -> {
			for (Button b : tileBtns) {
				if (b.getStyle().equals("-fx-background-color:lightblue")) {	
					b.setDisable(true);
				}
			}
		});

		Scene scene = new Scene(vbox, 500, 500);
		stage.setScene(scene);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
