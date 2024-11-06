package com.example.Ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex3Application {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex3Application.class, args);
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

			// Đọc danh sách sinh viên và in ra console
			System.out.println("Danh sách sinh viên ban đầu:");
			studentRepository.findAll().forEach(System.out::println);

			// Cập nhật thông tin sinh viên
			Student studentToUpdate = studentRepository.findById(student1.getId()).orElse(null);
			if (studentToUpdate != null) {
				studentToUpdate.setIeltsScore(7.5);
				studentRepository.save(studentToUpdate);
			}

			// In ra danh sách sau khi cập nhật
			System.out.println("Danh sách sinh viên sau khi cập nhật:");
			studentRepository.findAll().forEach(System.out::println);

			// Xóa sinh viên và in ra danh sách sau khi xóa
			studentRepository.deleteById(student2.getId());
			System.out.println("Danh sách sinh viên sau khi xóa:");
			studentRepository.findAll().forEach(System.out::println);
		};
	}
}
