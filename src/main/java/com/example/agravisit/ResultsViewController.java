package com.example.agravisit;

import com.example.agravisit.model.Itinerary;
import com.example.agravisit.model.TouristAttraction;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ResultsViewController {

    @FXML
    private VBox itineraryContainer; // This is the main VBox inside the ScrollPane

    /**
     * This method receives the itinerary and dynamically builds the UI with cards.
     * @param itinerary The generated plan.
     */
    public void populateItinerary(Itinerary itinerary) {
        itineraryContainer.getChildren().clear(); // Clear any previous results
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

        if (itinerary.getDailyPlan().isEmpty()) {
            Label emptyLabel = new Label("No attractions found for the selected interests and dates.");
            emptyLabel.getStyleClass().add("day-header");
            itineraryContainer.getChildren().add(emptyLabel);
            return;
        }

        // Loop through each day in the plan
        for (Map.Entry<java.time.LocalDate, List<TouristAttraction>> entry : itinerary.getDailyPlan().entrySet()) {
            // Create a header for the day
            Label dayHeader = new Label(entry.getKey().format(formatter));
            dayHeader.getStyleClass().add("day-header");
            itineraryContainer.getChildren().add(dayHeader);

            // Create a card for each attraction on that day
            for (TouristAttraction attraction : entry.getValue()) {
                BorderPane card = createAttractionCard(attraction);
                itineraryContainer.getChildren().add(card);
            }
        }
    }

    /**
     * Creates a styled BorderPane "card" for a single attraction.
     * @param attraction The attraction to display.
     * @return A styled BorderPane node.
     */
    private BorderPane createAttractionCard(TouristAttraction attraction) {
        BorderPane card = new BorderPane();
        card.getStyleClass().add("attraction-card");

        // VBox for the main content (name, description, etc.)
        VBox contentBox = new VBox(5); // 5px spacing
        contentBox.setPadding(new Insets(15));

        Label nameLabel = new Label(attraction.getName());
        nameLabel.getStyleClass().add("attraction-title");

        Text descriptionText = new Text(attraction.getDescription());
        TextFlow descriptionFlow = new TextFlow(descriptionText);

        Label bestTimeLabel = new Label("Best time to visit: " + attraction.getBestTimeToVisit());
        bestTimeLabel.getStyleClass().add("attraction-details");

        Label timingLabel = new Label("Timings: " + attraction.getOpeningTime() + " - " + attraction.getClosingTime());
        timingLabel.getStyleClass().add("attraction-details");

        contentBox.getChildren().addAll(nameLabel, descriptionFlow, bestTimeLabel, timingLabel);
        card.setCenter(contentBox);

        return card;
    }
}