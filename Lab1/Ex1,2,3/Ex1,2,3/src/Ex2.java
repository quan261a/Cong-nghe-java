import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;

public class Ex2 {
    public static void main(String[] args) {
        // Khởi tạo hai mảng một chiều
        int[] a = {5, 2, 8, 3};
        int[] b = {7, 6, 1, 4};

        // In hai mảng a và b ra console
        ArrayOutput.print(a);
        ArrayOutput.print(b);

        // Gộp hai mảng a và b thành mảng c
        int[] c = ArrayHandler.merge(a, b);
        System.out.println("Mang sau khi gop:");
        ArrayOutput.print(c);

        // Sắp xếp mảng c theo thứ tự tăng dần
        ArrayHandler.sort(c);
        System.out.println("Mang sau khi sap xep:");
        ArrayOutput.print(c);

        // Ghi mảng c vào file output.txt
        ArrayOutput.write(c, "output.txt");
    }
}
