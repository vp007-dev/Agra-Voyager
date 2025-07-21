package com.example.agravisit;

import com.example.agravisit.model.Itinerary;
import com.example.agravisit.service.PlannerService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoyagerViewController {

    private final PlannerService plannerService = new PlannerService();

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ToggleGroup paceGroup;
    @FXML private ToggleButton historyToggle;
    @FXML private ToggleButton shoppingToggle;
    @FXML private ToggleButton foodToggle;
    @FXML private ToggleButton gardensToggle;
    @FXML private ToggleButton artToggle;
    @FXML private ToggleButton photographyToggle;

    @FXML
    void handleGenerateButtonAction() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            showAlert("Invalid Dates", "Please select a valid start and end date.");
            return;
        }

        RadioButton selectedPaceRadio = (RadioButton) paceGroup.getSelectedToggle();
        String pace = selectedPaceRadio.getText();

        List<String> selectedInterests = new ArrayList<>();
        if (historyToggle.isSelected()) selectedInterests.add("History");
        if (shoppingToggle.isSelected()) selectedInterests.add("Shopping");
        if (foodToggle.isSelected()) selectedInterests.add("Food");
        if (gardensToggle.isSelected()) selectedInterests.add("Gardens");
        if (artToggle.isSelected()) selectedInterests.add("Art");
        if (photographyToggle.isSelected()) selectedInterests.add("Photography");

        if (selectedInterests.isEmpty()) {
            showAlert("No Interests Selected", "Please select at least one interest to generate a plan.");
            return;
        }

        Itinerary itinerary = plannerService.generatePlan(startDate, endDate, selectedInterests, pace);

        showResultsWindow(itinerary);
    }

    private void showResultsWindow(Itinerary itinerary) {
        try {
            // UPDATED: Points back to the original ResultsView.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/agravisit/ResultsView.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the data
            ResultsViewController resultsController = loader.getController();
            resultsController.populateItinerary(itinerary);

            Stage stage = new Stage();
            stage.setTitle("Your Generated Itinerary");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the results window.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        startDatePicker.setValue(LocalDate.now());
    }
}