package com.example.numberguessinggame;




import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class numberGuessingGame extends Application {
    private int generatedNumber;
    private int remainingAttempts;
    private Label resultLabel;
    private TextField guessTextField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");

        // Generate the initial number
        generateNumber();

        // Create UI components
        Label instructionsLabel = new Label("Enter a number between 1 to 100. Guess the number within 5 trials.");
        guessTextField = new TextField();
        resultLabel = new Label();
        Button guessButton = new Button("Guess");
        guessButton.setOnAction(event -> checkGuess());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(instructionsLabel, guessTextField, guessButton, resultLabel);

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    private void generateNumber() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1;
        remainingAttempts = 5;
    }

    private void checkGuess() {
        int numberGuessed = Integer.parseInt(guessTextField.getText());
        remainingAttempts--;

        if (numberGuessed == generatedNumber) {
            showResult("Congratulations! You have guessed the number.");
        } else if (remainingAttempts > 0) {
            if (numberGuessed < generatedNumber) {
                showResult("The number is greater than " + numberGuessed);
            } else {
                showResult("The number is less than " + numberGuessed);
            }
        } else {
            showResult("Sorry! Try again. You have no more trials remaining for guessing the number.\n" +
                    "The number generated was " + generatedNumber);
        }

        guessTextField.clear();
        guessTextField.requestFocus();
    }

    private void showResult(String message) {
        resultLabel.setText(message);
        if (message.startsWith("Sorry") || message.startsWith("Congratulations")) {
            guessTextField.setEditable(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

