package configuration;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testPlayerMark() {
        Player player = new Player(Mark.O);
        assertEquals(player.getMark(), Mark.O);
    }

    @Test
    public void testPlayerToString() {
        Player player = new Player(Mark.O);
        player.setName("Robert");
        assertEquals(player.toString(), "Robert");
    }

}
