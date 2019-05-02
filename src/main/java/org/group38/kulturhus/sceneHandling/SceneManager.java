package org.group38.kulturhus.sceneHandling;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.group38.kulturhus.controllers.MainController;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum SceneManager {
     INSTANCE;

     private Stage primaryStage = null;
    private final Map<SceneName, SceneInfo> scenes;
    private boolean initialized;
    private BorderPane borderPane;
    private MainController activeController;
    private Stage currentPopUpStage;
    private FXMLLoader currentLoader;

    private MainController currentController;

    SceneManager(){
        scenes = new HashMap<>();

        createSceneInfos();
    }

    private void createSceneInfos(){
        SceneInfo showEvent = new SceneInfo("Arrangementsoversikt", "/org/group38/showEvent.fxml");
        SceneInfo addEvent = new SceneInfo("Legg til Arrangement", "/org/group38/addEvent.fxml"  );
        SceneInfo addTickets = new SceneInfo("Reserver Billett", "/org/group38/addTicket.fxml"  );
        SceneInfo showTickets = new SceneInfo("Vis Billetter", "/org/group38/showTickets.fxml"  );
        SceneInfo showVenues = new SceneInfo("Oversikt over Lokaler", "/org/group38/showVenues.fxml"  );
        SceneInfo addVenue = new SceneInfo("Legg til lokale", "/org/group38/addVenues.fxml"  );
        SceneInfo fileEditor = new SceneInfo("Bytt Filer", "/org/group38/FileEditor.fxml");

        scenes.put(SceneName.SHOWEVENT, showEvent);
        scenes.put(SceneName.ADDEVENT, addEvent);
        scenes.put(SceneName.ADDTICKET, addTickets);
        scenes.put(SceneName.SHOWTICKET, showTickets);
        scenes.put(SceneName.SHOWVENUE, showVenues);
        scenes.put(SceneName.ADDVENUE, addVenue);
        scenes.put(SceneName.FILEEDITOR, fileEditor);

    }
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = Objects.requireNonNull(primaryStage, "primary stage cna't be null");

        primaryStage.setOnCloseRequest((windowEvent) -> {
            if (currentController != null) {
                System.out.println("closing");
                currentController.exit();
            }
        }  );

        initialized = true;
    }

    public static void navigate(SceneName sceneName){
        INSTANCE.changeToScene(sceneName);
    }

    public void changeToScene(SceneName sceneName) {
        Objects.requireNonNull(sceneName);

        if (!scenes.containsKey(sceneName)) {
            throw new InvalidParameterException();
        }

        if (currentController != null) {
            currentController.exit();
        }

        SceneInfo info = scenes.get(sceneName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(info.getViewpath()));
        setCurrentLoader(loader);
        Pane root;

        try {
            root = loader.load();

            currentController = loader.getController();

            Scene scene = new Scene(root);

            primaryStage.setTitle(info.getSceneTitle());
            primaryStage.setScene(scene);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setCurrentLoader(FXMLLoader loader) {
        this.currentLoader = loader;
    }

    public Stage getCurrentPopUpStage() {
        return currentPopUpStage;
    }

    private void setCurrentPopUpStage(Stage popUpStage) {
        this.currentPopUpStage = popUpStage;
    }

    public FXMLLoader getCurrentLoader() {
        return currentLoader;
    }

    //TODO Endre navn på denne
    public void createUndecoratedStageWithScene(Stage popUpStage, SceneName sceneName, double heightDivisor, double widthDivisor){ //throws NoPrimaryStageException {
     //   if (this.primaryStage == null) {
          //  throw new NoPrimaryStageException("No primary stage. Do not call this method before a Primary Stage has been defined");
   //     }

        Objects.requireNonNull(popUpStage, "The new stage can't be null, please provide a Stage object");
        popUpStage.setWidth(this.primaryStage.getWidth()/widthDivisor);
        popUpStage.setHeight(this.primaryStage.getHeight()/heightDivisor);
        popUpStage.setMinHeight(333);
        popUpStage.setMinWidth(400);

        SceneInfo sceneInfo = scenes.get(sceneName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneInfo.getViewpath()));
        Pane root;

        try {
            root = loader.load();
            Scene scene = new Scene(root);
            popUpStage.setScene(scene);
            popUpStage.setTitle(sceneInfo.getSceneTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCurrentPopUpStage(popUpStage);
        popUpStage.show();

    }





    @Override
    public String toString() {
        return "SceneManager{" +
                "primaryStage=" + primaryStage +
                ", scenes=" + scenes +
                ", initialized=" + initialized +
                ", currentController=" + currentController +
                '}';
    }
}


