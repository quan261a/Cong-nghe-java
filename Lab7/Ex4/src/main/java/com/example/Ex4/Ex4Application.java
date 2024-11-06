package com.example.Ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex4Application {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex4Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			// Thêm sinh viên
			Student student1 = new Student(null, "Nguyen Van A", 22, "a@example.com", 6.5);
			Student student2 = new Student(null, "Le Thi B", 23, "b@example.com", 7.0);
			Student student3 = new Student(null, "Tran Van C", 21, "c@example.com", 6.0);
			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);

			System.out.println("Danh sách sinh viên ban đầu:");
			studentRepository.findAll().forEach(System.out::println);

			Student studentToUpdate = studentRepository.findById(student1.getId()).orElse(null);
			if (studentToUpdate != null) {
				studentToUpdate.setIeltsScore(7.5);
				studentRepository.save(studentToUpdate);
			}

			System.out.println("Danh sách sinh viên hơn 22 tuổi:");
			studentRepository.findByAgeGreaterThanEqual(22).forEach(System.out::println);


			long count = studentRepository.countByIeltsScore(7.5);
			System.out.println("Số lượng sinh viên có điểm Ielts là 7.5: " + count);

		};
	}
}
