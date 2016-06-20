package za.co.caxtondigital.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import za.co.caxtondigital.vo.Album;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by caesarmashau on 16/06/19.
 */

@ComponentScan(value = "za.co.caxtondigital")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MusicBrainz.class,loader=AnnotationConfigContextLoader.class)
public class MusicBrainzTest {

    @Autowired
    private MusicBrainz musicBrainz;

    @Test
    public void testGetAlbums() throws Exception {

        List<Album> albums = musicBrainz.getAlbums("b625448e-bf4a-41c3-a421-72ad46cdb831", 0, 10);
        Assert.assertNotNull(albums);
    }
}