package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button quitButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button createAccountButton;

    @FXML
    public void login() {
        UserService userService = new UserService();
        try {
            userService.login(username.getText(), password.getText());
            toCourseList();
        } catch (NullPointerException | IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
            username.setText("");
            password.setText("");
        }
    }

    public void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void createAccountScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/virginia/sde/reviews/create-account-screen.fxml"));
            Parent root = loader.load();
            Scene programScene = new Scene(root);
            Stage loginStage = (Stage) createAccountButton.getScene().getWindow();
            loginStage.setScene(programScene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userPassCheck() {
        //TODO: implement user and password match user in database.
        // you have access to username and password, and those are the variable names
        return true;
    }

    public void toCourseList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/virginia/sde/reviews/class-list-screen.fxml"));
            Parent root = loader.load();
            ClassListScreenController listController = loader.getController();
            listController.initData(username.getText());
            Scene programScene = new Scene(root);
            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            loginStage.setScene(programScene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
