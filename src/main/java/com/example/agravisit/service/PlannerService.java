package com.example.agravisit.service;

import com.example.agravisit.model.Itinerary;
import com.example.agravisit.model.TouristAttraction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlannerService {

    private final List<TouristAttraction> allAttractions;

    public PlannerService() {
        this.allAttractions = loadAttractionsFromFile();
    }

    public Itinerary generatePlan(LocalDate startDate, LocalDate endDate, List<String> interests, String pace) {
        Itinerary itinerary = new Itinerary();
        List<LocalDate> tripDates = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());

        List<TouristAttraction> relevantAttractions = allAttractions.stream()
                .filter(attraction -> !Collections.disjoint(attraction.getTags(), interests))
                .collect(Collectors.toList());

        int sitesPerDay = getSitesPerDay(pace);
        int attractionIndex = 0;

        for (LocalDate date : tripDates) {
            for (int i = 0; i < sitesPerDay; i++) {
                if (attractionIndex >= relevantAttractions.size()) break;

                TouristAttraction currentAttraction = relevantAttractions.get(attractionIndex);

                if (!currentAttraction.isClosedOn(date.getDayOfWeek())) {
                    itinerary.addAttraction(date, currentAttraction);
                    attractionIndex++;
                } else {
                    int nextAvailableIndex = findNextAvailable(relevantAttractions, attractionIndex + 1, date.getDayOfWeek());
                    if (nextAvailableIndex != -1) {
                        itinerary.addAttraction(date, relevantAttractions.get(nextAvailableIndex));
                        Collections.swap(relevantAttractions, attractionIndex, nextAvailableIndex);
                        attractionIndex++;
                    }
                }
            }
        }
        return itinerary;
    }

    private int findNextAvailable(List<TouristAttraction> attractions, int startIndex, DayOfWeek day) {
        for (int i = startIndex; i < attractions.size(); i++) {
            if (!attractions.get(i).isClosedOn(day)) return i;
        }
        return -1;
    }

    private int getSitesPerDay(String pace) {
        return switch (pace) {
            case "Packed" -> 5;
            case "Moderate" -> 4;
            default -> 3;
        };
    }

    private List<TouristAttraction> loadAttractionsFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<TouristAttraction>> typeReference = new TypeReference<>() {};

        // FIX: This is a more robust way to find the file that bypasses classpath issues.
        // It now looks in the user's home directory.
        String homeDir = System.getProperty("user.home");
        String filePath = Paths.get(homeDir, "attractions.json").toString();

        System.out.println("Attempting to load attractions from: " + filePath);

        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            List<TouristAttraction> attractions = mapper.readValue(inputStream, typeReference);
            System.out.println("Successfully loaded " + attractions.size() + " attractions.");
            return attractions;
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: Unable to load attractions from file system.");
            System.err.println("Please ensure 'attractions.json' is in your user home directory: " + homeDir);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}