package za.co.caxtondigital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import za.co.caxtondigital.vo.Artist;

@Repository
public interface ArtistsRepository extends PagingAndSortingRepository<Artist, String> {

    Page<Artist> findByNameContainingIgnoreCase(String name,Pageable pageRequest);
}