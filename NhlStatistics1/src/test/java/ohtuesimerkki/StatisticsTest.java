package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Daniel Riissanen on 1.11.2018.
 */
public class StatisticsTest {

    Statistics stats;
    private List<Player> players = new ArrayList<>();

    @Before
    public void setUp() {
        players.clear();
        players.add(new Player("Semenko", "EDM", 4, 12));   // 16
        players.add(new Player("Lemieux", "PIT", 45, 54));  // 99
        players.add(new Player("Kurri",   "EDM", 37, 53));  // 90
        players.add(new Player("Yzerman", "DET", 42, 56));  // 96
        players.add(new Player("Gretzky", "EDM", 35, 89));  // 124
        stats = new Statistics(() -> players);
    }

    @Test
    public void testConstructor() {
        List<Player> result = stats.topScorers(5);
        assertEquals(5, result.size());
        for (Player p : players)
            assertTrue(result.contains(p));
    }

    @Test
    public void testSearchPlayerExists() {
        for (Player p : players)
            assertNotNull(stats.search(p.getName()));
    }

    @Test
    public void testSearchPlayerNotExists() {
        assertNull(stats.search("Non-existent"));
    }

    @Test
    public void testSearchTeamExists() {
        assertEquals(3, stats.team("EDM").size());
        assertEquals(1, stats.team("PIT").size());
        assertEquals(1, stats.team("DET").size());
    }

    @Test
    public void testTeamNotExists() {
        assertEquals(0, stats.team("NONE").size());
    }

    @Test
    public void testTopScorersCount() {
        assertEquals(0, stats.topScorers(-1).size());
        for (int i = 0; i < players.size(); i++)
            assertEquals(i, stats.topScorers(i).size());
        assertEquals(players.size(), stats.topScorers(players.size() + 1).size());
    }

    @Test
    public void testTopScorersCorrectOrder() {
        List<Player> orderedPlayers = stats.topScorers(players.size());
        assertEquals(5, orderedPlayers.size());
        assertEquals(orderedPlayers.get(0).getName(), "Gretzky");
        assertEquals(orderedPlayers.get(1).getName(), "Lemieux");
        assertEquals(orderedPlayers.get(2).getName(), "Yzerman");
        assertEquals(orderedPlayers.get(3).getName(), "Kurri");
        assertEquals(orderedPlayers.get(4).getName(), "Semenko");
    }
}
