package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.File;

public class Controller {

    @FXML
    private Text actiontarget;

    @FXML
    private ImageView imageviewtarget;

    @FXML
    private Group imageGroup;

    @FXML
    private GridPane mainGridPane;

    private void removepoints(){
        for (Node node : imageGroup.getChildren()) {
            if (node.getClass().equals(new Circle(0,0,0).getClass())){
                imageGroup.getChildren().remove(node);
                removepoints();
                break;
            }
        }
    }
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        /*
        imageGroup.getChildren().stream()
                .filter(node -> node.getClass().equals(new Circle(0,0,0).getClass()))
                .forEach(node -> imageGroup.getChildren().remove(node));

         */
        removepoints();
        File folder = new File("C:/Users/dell/Desktop/pd/files/images/");
        File[] listOfFiles = folder.listFiles();
        String filepath;
        String actext;
        for (int i = 0; i < listOfFiles.length; i++) {
            filepath = listOfFiles[i].getAbsolutePath();
            actext = actiontarget.getText();
            if (listOfFiles[i].isFile() && filepath.equals(actext)) {
               if(i+1<listOfFiles.length){
                   imageviewtarget.setImage(new Image(listOfFiles[i+1].toURI().toString()));
                   actiontarget.setText(listOfFiles[i+1].getAbsolutePath());
                   break;
               }
               else{
                   imageviewtarget.setImage(new Image(listOfFiles[0].toURI().toString()));
                   break;
               }
            }
        }
    }
    @FXML
    protected void setOnMouseClicked(MouseEvent event){
            //System.out.println(event.getSceneX());
           // System.out.println(event.getSceneY());
            imageGroup.getChildren().add(new Circle(event.getSceneX(), event.getSceneY(), 3));

    }
}
