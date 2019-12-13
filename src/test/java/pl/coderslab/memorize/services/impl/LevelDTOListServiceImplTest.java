package pl.coderslab.memorize.services.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LevelDTOListServiceImplTest {

    private LevelDTOListServiceImpl levelDTOListServiceImpl;
    @Before
    public void beforeTest() {
        levelDTOListServiceImpl = new LevelDTOListServiceImpl(null);
    }

    @Test
    public void givenLongString_whenShortenLevelName_thenReturnShortString () {
        // given
        String str = "3rd level 2nd item of 2nd level 1st item";
        String expected = "3rd level 2nd...";

        //when
        String result = levelDTOListServiceImpl.shortenLevelName(str);

        //then
        assertEquals(expected, result);
    }


}