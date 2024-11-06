package com.example.Ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class Ex6Application {

	@Autowired
	private StudentRepositoryPagingAndSorting studentRepositoryPagingAndSorting;

	public static void main(String[] args) {
		SpringApplication.run(Ex6Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			// Thêm sinh viên
			Student student1 = new Student(null, "Nguyen Van A", 22, "a@example.com", 6.5);
			Student student2 = new Student(null, "Le Thi B", 23, "b@example.com", 7.0);
			Student student3 = new Student(null, "Tran Van C", 21, "c@example.com", 6.0);
			Student student4 = new Student(null, "Nguyen Thi D", 23, "d@example.com", 7.5);
			Student student5 = new Student(null, "Nguyen Thi E", 22, "e@example.com", 7.0);
			Student student6 = new Student(null, "Pham Thi F", 23, "f@example.com", 6.5);
			Student student7 = new Student(null, "Vu Thi G", 25, "g@example.com", 6.0);
			Student student8 = new Student(null, "Nguyen Thi H", 21, "h@example.com", 7.5);
			Student student9 = new Student(null, "Le Thi I", 24, "i@example.com", 8.0);
			Student student10 = new Student(null, "Ho Thi J", 26, "j@example.com", 7.5);
			Student student11 = new Student(null, "Tran Thi K", 22, "k@example.com", 6.8);

			studentRepositoryPagingAndSorting.save(student1);
			studentRepositoryPagingAndSorting.save(student2);
			studentRepositoryPagingAndSorting.save(student3);
			studentRepositoryPagingAndSorting.save(student4);
			studentRepositoryPagingAndSorting.save(student5);
			studentRepositoryPagingAndSorting.save(student6);
			studentRepositoryPagingAndSorting.save(student7);
			studentRepositoryPagingAndSorting.save(student8);
			studentRepositoryPagingAndSorting.save(student9);
			studentRepositoryPagingAndSorting.save(student10);
			studentRepositoryPagingAndSorting.save(student11);


			System.out.println("Danh sách sinh viên sắp xếp theo độ tuổi (giảm dần) và điểm IELTS (tăng dần):");
			Sort sort = Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore"));
			studentRepositoryPagingAndSorting.findAll(sort).forEach(System.out::println);


			System.out.println("Danh sách sinh viên từ chỉ số 4-5-6:");
			PageRequest pageRequest = PageRequest.of(1, 3, Sort.by(Sort.Order.asc("id"))); // Trang 2, lấy 3 sinh viên
			Page<Student> studentsPage = studentRepositoryPagingAndSorting.findAll(pageRequest);
			studentsPage.forEach(System.out::println);
		};
	}
}
