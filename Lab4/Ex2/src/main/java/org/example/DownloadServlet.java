package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");
        String filePath = getServletContext().getRealPath("/data/" + fileName);
        File file = new File(filePath);

        if (fileName == null || fileName.isEmpty()) {
            response.getWriter().write("File not found");
            return;
        }

        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setContentType("application/octet-stream");

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                long startTime = System.currentTimeMillis();
                long speed = (speedParam != null && !speedParam.isEmpty()) ? Long.parseLong(speedParam) : Long.MAX_VALUE;

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);

                    // Tính thời gian đã trôi qua
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime == 0) {
                        elapsedTime = 1; // Tránh chia cho 0
                    }

                    // Tính tốc độ hiện tại
                    long currentSpeed = (bytesRead * 1000) / elapsedTime; // bytes/sec

                    // Điều chỉnh tốc độ tải xuống nếu cần thiết
                    if (currentSpeed > speed) {
                        long sleepTime = ((bytesRead * 1000) / speed) - elapsedTime;
                        if (sleepTime > 0) {
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt(); // Khôi phục trạng thái ngắt quãng
                                break;
                            }
                        }
                    }

                    // Đặt lại thời gian bắt đầu
                    startTime = System.currentTimeMillis();
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid speed parameter");
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("File not found");
        }
    }
}
