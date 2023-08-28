package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final int GAMES_PER_SEASON = 34;

    public static void main(String[] args) {
        try (InputStream stream = Main.class.getClassLoader().getResourceAsStream("mls.json")) {
            Objects.requireNonNull(stream, "Input stream cannot be null");
            League league = MAPPER.readValue(stream.readAllBytes(), League.class);

            List<Team> teams = league.getConferences()
                .stream()
                .flatMap(c -> c.getTeams().stream())
                .collect(Collectors.toList());

            List<Matchup> matchups = getConferenceMatchups(league);
            matchups.addAll(getInterconferenceMatchups(league));

        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private static List<Matchup> getConferenceMatchups(League league) {
        List<Matchup> matchups = new ArrayList<>();
        for (Conference conf : league.getConferences()) {
            for (Team team : conf.getTeams()) {
                for (Team otherTeam : conf.getTeams()) {
                    if (!team.equals(otherTeam)) {
                        matchups.add(new Matchup(team, otherTeam));
                    }
                }
            }
        }
        return matchups;
    }

    private static List<Matchup> getInterconferenceMatchups(League league) {
        List<Matchup> matchups = new ArrayList<>();

        return matchups;
    }
}