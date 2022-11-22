package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class App extends Application implements IMapRefreshObserver{

    private AbstractWorldMap map;
    private GridPane gridPane;
    private RefreshSimulationEngine engine;
    private Thread engineThread;

    @Override
    public void init() throws Exception{
        super.init();

        Parameters parameters = getParameters();
        String[] args = parameters.getRaw().toArray(new String[0]);

        try {
            Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(5, 5)};
            this.map = new GrassField(10);
            engine = new RefreshSimulationEngine(this.map, positions, 300);
            engine.addObserver(this);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
            System.exit(1);
        }
    }

    @Override
    public void start(Stage primaryStage){
        this.gridPane = new GridPane();
        int cellWidth = 40;
        int cellHeight = 40;
        this.gridPane.setHgap(0);
        this.gridPane.setVgap(0);

        VBox root = new VBox();
        TextField textField = new TextField();
        Button button = new Button("Start");

        button.setOnAction(event -> {
            String[] strMoves = textField.getCharacters().toString().split(" ");
            MoveDirection[] directions = new OptionsParser().parse(strMoves);
            this.engine.setMoves(directions);
            this.engineThread = new Thread(this.engine);
            this.engineThread.start();
        });

        root.getChildren().addAll(textField, button);

        root.getChildren().add(this.gridPane);

        this.gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        this.gridPane.getRowConstraints().add(new RowConstraints(cellHeight));

        renderScene();

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void renderScene(){
        int cellWidth = 40;
        int cellHeight = 40;

        int minY = this.map.checkLowerLeft().y;
        int minX = this.map.checkLowerLeft().x;
        int maxY = this.map.checkUpperRight().y;
        int maxX = this.map.checkUpperRight().x;

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        this.gridPane.add(xyLabel, 0, 0, 1, 1);

        for (int i = minY; i < maxY+1; i++) {
            Label label = new Label(Integer.toString(i));
            this.gridPane.add(label, 0, maxY - i +1, 1, 1);
            this.gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = minX; i < maxX+1; i++) {
            Label label = new Label(Integer.toString(i));
            this.gridPane.add(label, i-minX+1, 0, 1, 1);
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = minX; x < maxX+1; x++) {
            for (int y = minY; y < maxY+1; y++) {
                Vector2d position = new Vector2d(x, y);
                if (this.map.isOccupied(position)) {
                    IMapElement worldMapElement = (IMapElement) this.map.objectAt(position);
                    GuiElementBox element = new GuiElementBox(worldMapElement);
                    VBox box = element.getBox();
                    GridPane.setHalignment(box, HPos.CENTER);
                    this.gridPane.add(box, position.x - minX + 1, maxY - position.y + 1, 1, 1);
                }
            }
        }

        this.gridPane.setGridLinesVisible(true);
    }

    @Override
    public void refresh() {
        Platform.runLater( () -> {
            this.gridPane.getChildren().clear();
            this.gridPane.getColumnConstraints().clear();
            this.gridPane.getRowConstraints().clear();
            this.gridPane.setGridLinesVisible(false);
            renderScene();
        });
    }
}
