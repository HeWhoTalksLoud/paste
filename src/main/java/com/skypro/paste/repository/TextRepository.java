package com.skypro.paste.repository;

import com.skypro.paste.model.Text;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, String> {
    @Query(value = "select * from text\n" +
            "where is_non_deletable = false\n" +
            "and expiry_date_time < ?1", nativeQuery = true)
    List<Text> findObsoleteRecords(Instant expiryDate);
    @Query(value = "select * from text\n" +
            "where access_type = 'PUBLIC'\n" +
            "and expiry_date_time > ?1\n" +
            "order by creation_date_time desc\n" +
            "limit 10", nativeQuery = true)
    List<Text> findLast10(LocalDateTime dateTime);

    List<Text> findAll(Specification<Text> specification);


}
