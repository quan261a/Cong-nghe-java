public class Ex1 {
    public static void main(String[] args) {
        // Ensure that exactly three arguments are passed
        if (args.length != 3) {
            System.out.println("Invalid expression");
            return;
        }

        try {
            // Lấy các phần từ đối số dòng lệnh
            String operand1 = args[0];
            String operator = args[1];
            String operand2 = args[2];

            // Chuyển đổi các toán hạng thành số thực
            double num1 = Double.parseDouble(operand1);
            double num2 = Double.parseDouble(operand2);

            // Tính toán và in kết quả
            double result = calculate(num1, operator, num2);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.err.println("Loi: Vui long đam bao rang cac toan hang la so hop le.");
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Phương pháp tính toán dựa trên toán tử
    private static double calculate(double a, String operator, double b) throws ArithmeticException {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Chia cho số không");
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException(" Unsupported operator\n " + operator);
        }
    }
}
