package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private AbstractWorldMap map;

    @Override
    public void init() throws Exception{
        super.init();
        Parameters parameters = getParameters();
        String[] args = parameters.getRaw().toArray(new String[0]);

        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(5, 5)};
            this.map = new GrassField(10);
            IEngine engine = new SimulationEngine(directions, this.map, positions);

            System.out.println(this.map.toString());
            engine.run();
            System.out.println(this.map.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
            System.exit(1);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        int cellWidth = 30;
        int cellHeight = 30;

        int minY = this.map.checkLowerLeft().y;
        int minX = this.map.checkLowerLeft().x;
        int maxY = this.map.checkUpperRight().y;
        int maxX = this.map.checkUpperRight().x;

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
        gridPane.add(xyLabel, 0, 0, 1, 1);

        for (int i = minY; i < maxY+1; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, 0, maxY - i +1, 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = minX; i < maxX+1; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, i-minX+1, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = minX; x < maxX+1; x++) {
            for (int y = minY; y < maxY+1; y++) {
                Vector2d position = new Vector2d(x, y);
                if (this.map.isOccupied(position)) {
                    Object worldMapElement = this.map.objectAt(position);
                    Label label = new Label(worldMapElement.toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    gridPane.add(label, position.x - minX + 1, maxY - position.y + 1, 1, 1);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
