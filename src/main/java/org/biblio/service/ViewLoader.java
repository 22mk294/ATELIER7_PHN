package org.biblio.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.biblio.Main;

import java.io.IOException;

public class ViewLoader {
    public static void loadView(String viewPath, String viewName) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewPath));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = ((Stage) Stage.getWindows().filtered(Window::isShowing).getFirst());
            stage.setTitle(viewName);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
