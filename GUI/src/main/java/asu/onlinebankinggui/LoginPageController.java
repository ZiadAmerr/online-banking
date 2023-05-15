package asu.onlinebankinggui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static asu.onlinebankinggui.ControllerUtility.changeScene;

public class LoginPageController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    protected void onLoginButtonClick() throws IOException{
        // TODO: Call login() from backend
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        changeScene("WelcomePage.fxml");
    }
}
