package com.example.Ex4;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeGreaterThanEqual(int age);

    long countByIeltsScore(double score);

    List<Student> findByNameContainingIgnoreCase(String name);
}

