package za.co.caxtondigital.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.caxtondigital.repository.ArtistsRepository;
import za.co.caxtondigital.vo.Artist;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class Artists {

    @Autowired
    private ArtistsRepository artistsRepository;

    public SearchResult search(String keyword,int pageNumber,int pageSize) {

        Pageable pageable = new PageRequest(pageNumber,pageSize);
        Page<Artist> pages = artistsRepository.findByNameContainingIgnoreCase(keyword,pageable);
        return new SearchResult(pages);
    }

    private String getValue(Cell cell) {

        if(cell ==null) {
            return  null;
        }
        return  cell.getStringCellValue();
    }

    @PostConstruct
    public void importData() throws IOException {

        InputStream is = null;
        XSSFWorkbook artistsWorkBook = null;
        try {
            is = getClass().getResourceAsStream("/artists.xlsx");;
            artistsWorkBook = new XSSFWorkbook (is);
            XSSFSheet artistsSheet = artistsWorkBook.getSheetAt(0);
            Row row;
            for(int i=1; i<=artistsSheet.getLastRowNum(); i++){
                row = artistsSheet.getRow(i);
                String name = getValue(row.getCell(0));
                String id = getValue(row.getCell(1));
                String country = getValue(row.getCell(2));
                String aliases = getValue(row.getCell(3));
                Artist artist = new Artist();
                artist.setName(name);
                artist.setAliases(aliases);
                artist.setCountry(country);
                artist.setId(id);
                artistsRepository.save(artist);
            }
        } catch (IOException e) {
            throw e;
        }finally {

            if(artistsWorkBook != null) {
                artistsWorkBook.close();
            }
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw  e;
                }
            }


        }

    }



    public class SearchResult {

        public SearchResult(Page<Artist> pages) {

            numberOfPages = pages.getTotalPages();
            page =pages.getNumber();
            artists = pages.getContent();
            pageSize = pages.getSize();
            numberOfSearchResults = pages.getTotalElements();
        }

        @Getter @Setter
        private List<Artist> artists;

        @Getter @Setter
        private long numberOfSearchResults;

        @Getter @Setter
        private int page;

        @Getter @Setter
        private int pageSize;

        @Getter @Setter
        private int numberOfPages;
    }
}
