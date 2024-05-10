import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("numbers.txt");
            Scanner scanner = new Scanner(file);
            String numbersStr = scanner.nextLine();
            String[] numbersArr = numbersStr.split(" ");
            int[] numbers = new int[numbersArr.length];
            for (int i = 0; i < numbersArr.length; i++) {
                numbers[i] = Integer.parseInt(numbersArr[i]);
            }
            int min = _min(numbers);
            int max = _max(numbers);
            int sum = _sum(numbers);
            int mult = _mult(numbers);
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Mult: " + mult);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
    
    public static int _min(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    
    public static int _max(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
    
    public static int _sum(int[] numbers) {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum;
    }
    
    public static int _mult(int[] numbers) {
        int mult = 1;
        for (int i : numbers) {
            mult = i * mult;
        }
        return mult;
    }
}
