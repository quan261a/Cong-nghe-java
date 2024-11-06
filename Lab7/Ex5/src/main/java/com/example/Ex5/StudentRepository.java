package com.example.Ex5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findByAgeGreaterThanEqual(@Param("age") int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore = :score")
    long countByIeltsScore(@Param("score") double score);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> findByNameContainingIgnoreCase(@Param("name") String name);
}
