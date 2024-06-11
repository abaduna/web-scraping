package com.serch.webscapers.Repository;

import com.serch.webscapers.models.WebPages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebPageRespository  extends CrudRepository<WebPages,Integer> {

    @Query("SELECT w FROM WebPages w WHERE w.domain LIKE %:text% OR w.description LIKE %:text% OR w.title LIKE %:text% OR w.url LIKE %:text% order by ranck DESC")
    List<WebPages> findByText(@Param("text") String text);

    @Query("SELECT w FROM WebPages w WHERE w.url = :url")
    WebPages findByUrl(@Param("url")  String url);
}
