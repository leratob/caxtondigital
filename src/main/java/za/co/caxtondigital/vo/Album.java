package za.co.caxtondigital.vo;

import lombok.Getter;
import lombok.Setter;
import za.co.caxtondigital.musicbrainz.Metadata;

import java.util.LinkedList;
import java.util.List;


public class Album {

    @Getter @Setter
    private String releaseId;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String label;

    @Getter @Setter
    private int numberOfTracks;

    @Getter @Setter
    private List<Artist> otherArtists;

    public static Album get(Metadata.RecordingList.Recording.ReleaseList.Release release) {

        Album album = new Album();
        album.setNumberOfTracks(release.getMediumList().getMedium().getTrackList().getCount());
        album.setReleaseId(release.getId());
        album.setStatus(release.getStatus());
        album.setTitle(release.getTitle());
        List<Artist> artists = new LinkedList<>();
        if(release.getArtistCredit() != null) {
            List<Metadata.RecordingList.Recording.ReleaseList.Release.ArtistCredit.NameCredit> nameCredits = release.getArtistCredit().getNameCredit();
            for(Metadata.RecordingList.Recording.ReleaseList.Release.ArtistCredit.NameCredit nameCredit : nameCredits){
                Artist artist = new Artist();
                artist.setId(nameCredit.getArtist().getId());
                artist.setName(nameCredit.getArtist().getName());
                artists.add(artist);
            }

        }
        album.setOtherArtists(artists);
        return album;

    }
}
