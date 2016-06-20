package za.co.caxtondigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.caxtondigital.vo.Album;

import java.io.IOException;
import java.util.List;

@Service
public class Albums {

    @Autowired
    private MusicBrainz musicBrainz;

    public List<Album> getAlbums(String artistId) throws IOException {
        return  musicBrainz.getAlbums(artistId, 0, 10);
    }
}
