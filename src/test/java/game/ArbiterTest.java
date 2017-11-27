package game;

import game.Arbiter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ArbiterTest {


    @DataProvider(name = "checkForVictory")
    public static Object[][] checkForVictory() {
        return new Object[][]{  {Arrays.asList("OOO"), "O", 3, true},
                                {Arrays.asList(" OO"), "O", 3, false}};
    }


    @Test(dataProvider = "checkForVictory")
    public void checkForVictory(List<String> sequences, String mark, int noForVictory, boolean isVictory) {
        Arbiter arbiter = new Arbiter(null, null);
        arbiter.setNoForWin(noForVictory);
        arbiter.update(sequences,mark);
        assertEquals(isVictory,arbiter.isMatchVictory());
    }

}
