package za.co.caxtondigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.caxtondigital.service.Albums;
import za.co.caxtondigital.service.Artists;
import za.co.caxtondigital.vo.Album;
import java.util.List;


@RequestMapping("/artist")
@RestController
public class ArtistsController {

    @Autowired
    private Artists artists;

    @Autowired
    private Albums albums;

    @RequestMapping(method = RequestMethod.GET,value = "/search/{keyword}/{page_number}/{page_size}")
    public Artists.SearchResult search(@PathVariable("keyword") String keyword,@PathVariable("page_number") int pageNumber,@PathVariable("page_size") int pageSize) throws  Exception {
        return artists.search(keyword,pageNumber,pageSize);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{artist_id}/albums")
    public List<Album> getAlbums(@PathVariable("artist_id") String id) throws  Exception {
        return albums.getAlbums(id);
    }

}
