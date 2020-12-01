import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KarvonenHeartRateGUI extends Application {
	
	final Slider intensity = new Slider(55, 95, 5);
	
	final TextField ageField = new TextField();
	final TextField rhrField = new TextField();
	final TextArea table = new TextArea();
	
	final Label ageCaption = new Label("Age:");
	final Label rhrCaption = new Label("Resting heart rate:");
	final Label intensityCaption = new Label("Intensity:");

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setTitle("Karvonen Heart Rate Calculator");
		scene.setFill(Color.WHITE);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);
		scene.setRoot(grid);
		
		GridPane.setConstraints(ageCaption, 0, 0);
		grid.getChildren().add(ageCaption);
		
		GridPane.setConstraints(ageField, 1, 0);
		ageField.textProperty().addListener((ov, oldValue, newValue) -> {
			update();
		});
		grid.getChildren().add(ageField);
		
		GridPane.setConstraints(rhrCaption, 0, 1);
		grid.getChildren().add(rhrCaption);
		
		GridPane.setConstraints(rhrField, 1, 1);
		rhrField.textProperty().addListener((ov, oldValue, newValue) -> {
			update();
		});
		grid.getChildren().add(rhrField);
		
		GridPane.setConstraints(intensityCaption, 0, 2);
		GridPane.setColumnSpan(intensityCaption, 2);
		grid.getChildren().add(intensityCaption);
		
		GridPane.setConstraints(intensity, 0, 3);
		GridPane.setColumnSpan(intensity, 2);
		intensity.valueProperty().addListener((ov, oldValue, newValue) -> {
			update();
		});
		grid.getChildren().add(intensity);
		
		table.setPrefColumnCount(30);
		GridPane.setConstraints(table, 2, 0);
		GridPane.setRowSpan(table, 4);
		grid.getChildren().add(table);
		
		stage.show();
	}
	
	private void update() {
		if (ageField.getText().length() == 0) {
			return;
		}
		if (rhrField.getText().length() == 0) {
			return;
		}
		int age = Integer.parseInt(ageField.getText());
		int rhr = Integer.parseInt(rhrField.getText());
		int intensityValue = (int) intensity.getValue();
		table.setText(generateTable(age, rhr, intensityValue));
	}
	
	private static String generateTable(int age, int rhr, int intensity) {
		StringBuffer result = new StringBuffer();
		result.append(String.format("Resting Pulse: %d     Age: %d\n", rhr, age));
		result.append("");
		int thr = (((220 - age) - rhr) * intensity / 100) + rhr;
		result.append(String.format("Intensity: %d%%  Target Heart Rate: %d", intensity, thr));
		return result.toString();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
