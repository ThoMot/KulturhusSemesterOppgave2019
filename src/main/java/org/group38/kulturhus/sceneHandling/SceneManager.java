package org.group38.kulturhus.sceneHandling;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    private MainController currentController;

    SceneManager(){
        scenes = new HashMap<>();

        createSceneInfos();
    }

    private void setupApp(Scene scene){
        assert scene != null;

    }




    private void createSceneInfos(){
        SceneInfo frontpage = new SceneInfo("Frontpage - KultRes", "/org/group38/frontpage.fxml"  );
        SceneInfo addEvent = new SceneInfo("addEvent - KultRes", "/org/group38/addEvent.fxml"  );
        SceneInfo showEvent = new SceneInfo("showEvent - KultRes", "/org/group38/showEvent.fxml"  );
        SceneInfo addTickets = new SceneInfo("addTicket - KultRes", "/org/group38/addTicket.fxml"  );
        SceneInfo showTickets = new SceneInfo("showTicket - KultRes", "/org/group38/showTicket.fxml"  );
        SceneInfo showVenues = new SceneInfo("showVenues - KultRes", "/org/group38/showVenues.fxml"  );

        scenes.put(SceneName.FRONTPAGE, frontpage);
        scenes.put(SceneName.ADDEVENT, addEvent);
        scenes.put(SceneName.SHOWEVENT, showEvent);
        scenes.put(SceneName.ADDTICKET, addTickets);
        scenes.put(SceneName.SHOWTICKET, showTickets);
        scenes.put(SceneName.SHOWVENUE, showVenues);

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
        INSTANCE.changeToScene(sceneName, null);
    }

    public void changeToScene(SceneName sceneName, Object parameters) {
        Objects.requireNonNull(sceneName);

        if (!scenes.containsKey(sceneName)) {
            throw new InvalidParameterException();
        }

        if (currentController != null) {
            currentController.exit();
        }

        SceneInfo info = scenes.get(sceneName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(info.getViewpath()));

        Pane root;

        try {
            root = loader.load();

            currentController = loader.getController();

            Scene scene = new Scene(root);

            primaryStage.setTitle(info.getTitle());
            primaryStage.setScene(scene);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

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


