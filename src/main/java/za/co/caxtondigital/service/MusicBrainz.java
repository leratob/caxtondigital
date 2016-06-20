package za.co.caxtondigital.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import za.co.caxtondigital.musicbrainz.Metadata;
import za.co.caxtondigital.vo.Album;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
public class MusicBrainz {

    private String url = "http://musicbrainz.org/ws/2/recording";

    protected CloseableHttpClient client;

    @PostConstruct
    public void init() {

        client = HttpClientBuilder.create().build();
    }

    public List<Album> getAlbums(String artistId, int page, int pageSize) throws IOException {

        List<Album> albums = new LinkedList<>();
        StringBuilder requestUrl = new StringBuilder(url);
        List<NameValuePair> urlParameters = new LinkedList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("?query", "arid:" + artistId));
        urlParameters.add(new BasicNameValuePair("&limit", Integer.toString(pageSize)));
        urlParameters.add(new BasicNameValuePair("&offset", Integer.toString(page)));
        Iterator<NameValuePair> itr = urlParameters.iterator();
        while (itr.hasNext()) {
            NameValuePair nameValuePair = itr.next();
            requestUrl.append(nameValuePair.getName() + "=" + URLEncoder.encode(nameValuePair.getValue()));
        }
        HttpGet get = new HttpGet(requestUrl.toString());
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            Metadata metadata = JAXB.unmarshal(entity.getContent(), Metadata.class);
            if (metadata == null) {
                return albums;
            }
            Metadata.RecordingList recordingList = metadata.getRecordingList();
            if (recordingList == null) {
                return albums;
            }
            List<Metadata.RecordingList.Recording> recordings = recordingList.getRecording();
            for (Metadata.RecordingList.Recording recording : recordings) {

                List<Metadata.RecordingList.Recording.ReleaseList.Release> releases = recording.getReleaseList().getRelease();
                for (Metadata.RecordingList.Recording.ReleaseList.Release release : releases) {
                    albums.add(Album.get(release));
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(response);
        }

        return albums;

    }
}
