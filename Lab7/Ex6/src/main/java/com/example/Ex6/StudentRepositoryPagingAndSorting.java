package com.example.Ex6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentRepositoryPagingAndSorting extends JpaRepository<Student, Long> {

    // Phương thức tìm tất cả sinh viên với sắp xếp
    List<Student> findAll(Sort sort);

    // Phương thức tìm tất cả sinh viên với phân trang
    Page<Student> findAll(Pageable pageable);
}

