package org.example;

import java.util.Objects;

public class Matchup {

    private Team away;
    private Team home;

    public Matchup(Team away, Team home) {
        this.away = away;
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matchup matchup = (Matchup) o;
        return away.equals(matchup.away) && home.equals(matchup.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(away, home);
    }

    @Override
    public String toString() {
        return away.getDisplayName() + " @ " + home.getDisplayName();
    }
}
