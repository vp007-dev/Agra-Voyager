package com.example.agravisit.model;

import java.time.DayOfWeek;
import java.util.Set;

// This is now a "POJO" (Plain Old Java Object) for Jackson to use.
public class TouristAttraction {
    private String name;
    private String description;
    private Set<String> tags;
    private Set<DayOfWeek> closedDays;
    private String openingTime;
    private String closingTime;
    private String bestTimeToVisit;

    // A no-argument constructor is needed for Jackson
    public TouristAttraction() {}

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Set<String> getTags() { return tags; }
    public Set<DayOfWeek> getClosedDays() { return closedDays; }
    public String getOpeningTime() { return openingTime; }
    public String getClosingTime() { return closingTime; }
    public String getBestTimeToVisit() { return bestTimeToVisit; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTags(Set<String> tags) { this.tags = tags; }
    public void setClosedDays(Set<DayOfWeek> closedDays) { this.closedDays = closedDays; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }
    public void setClosingTime(String closingTime) { this.closingTime = closingTime; }
    public void setBestTimeToVisit(String bestTimeToVisit) { this.bestTimeToVisit = bestTimeToVisit; }

    public boolean isClosedOn(DayOfWeek day) {
        return closedDays != null && closedDays.contains(day);
    }

    @Override
    public String toString() {
        return name;
    }
}