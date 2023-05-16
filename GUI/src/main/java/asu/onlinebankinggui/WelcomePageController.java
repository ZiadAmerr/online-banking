package asu.onlinebankinggui;

import javafx.fxml.FXML;
import java.io.IOException;
import static asu.onlinebankinggui.ControllerUtility.changeScene;

public class WelcomePageController {

    @FXML
    protected void onLoginButtonClick() throws IOException {
        changeScene("LoginPage.fxml");
    }

    @FXML
    protected void onSignupButtonClick() throws IOException {
        changeScene("SignupPage.fxml");
    }
}