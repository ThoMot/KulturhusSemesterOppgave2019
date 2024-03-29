package org.group38;

import javafx.application.Application;
import javafx.stage.Stage;
import org.group38.frameworks.sceneHandling.SceneManager;
import org.group38.frameworks.sceneHandling.SceneName;


public class MainApp extends Application {

    @Override
    public void start(Stage primarystage) throws Exception {

        SceneManager sceneManager = SceneManager.INSTANCE;
        sceneManager.setPrimaryStage(primarystage);
        SceneManager.navigate(SceneName.SHOWEVENT);

        primarystage.show();
    }



    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
