package com.example.agravisit.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Itinerary {
    // Using a TreeMap to keep the dates sorted automatically.
    private final Map<LocalDate, List<TouristAttraction>> dailyPlan = new TreeMap<>();

    public void addAttraction(LocalDate date, TouristAttraction attraction) {
        // Compute if absent is a clean way to initialize the list for a new date.
        dailyPlan.computeIfAbsent(date, k -> new java.util.ArrayList<>()).add(attraction);
    }

    public Map<LocalDate, List<TouristAttraction>> getDailyPlan() {
        return dailyPlan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

        if (dailyPlan.isEmpty()) {
            return "No attractions found for the selected interests and dates.";
        }

        for (Map.Entry<LocalDate, List<TouristAttraction>> entry : dailyPlan.entrySet()) {
            sb.append("\n--- ").append(entry.getKey().format(formatter)).append(" ---\n");
            int i = 1;
            for (TouristAttraction attraction : entry.getValue()) {
                sb.append("  ").append(i++).append(". ").append(attraction.getName()).append("\n");
                sb.append("     ").append(attraction.getDescription()).append("\n");
            }
        }
        return sb.toString();
    }
}