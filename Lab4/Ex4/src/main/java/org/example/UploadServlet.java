package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("txt", "doc", "docx", "img", "pdf", "rar", "zip");
    private static final String UPLOAD_DIRECTORY = "uploads"; // folder uploads

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("name");
        Part filePart = request.getPart("file"); // Đổi tên ở đây
        boolean override = request.getParameter("override") != null;

        // Kiểm tra định dạng tệp
        String fileExtension = getFileExtension(filePart.getSubmittedFileName());
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            response.getWriter().println("Unsupported file extension");
            return;
        }

        // Xác định đường dẫn tệp
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
        }

        File fileToSave = new File(uploadDir, fileName);
        if (fileToSave.exists() && !override) {
            response.getWriter().println("File already exists");
            return;
        }

        // Lưu tệp
        try (InputStream inputStream = filePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Xử lý kết quả
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        if (fileToSave.exists()) {
            response.getWriter().println("File has been uploaded<br>");
            response.getWriter().println("<a href='" + UPLOAD_DIRECTORY + "/" + fileName + "'>Click here to visit file</a>");
        } else {
            response.getWriter().println("File has been overridden<br>");
            response.getWriter().println("<a href='" + UPLOAD_DIRECTORY + "/" + fileName + "'>Click here to visit file</a>");
        }
        response.getWriter().println("</body></html>");
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0 && lastIndexOfDot < fileName.length() - 1) {
            return fileName.substring(lastIndexOfDot + 1).toLowerCase();
        }
        return "";
    }
}
