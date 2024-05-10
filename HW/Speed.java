import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainSpeedTests {
    static int[] numNumbers = new int []{1, 100, 10000, 100000, 1000000};
    int min = 1;
    int max = 10000;
    static String[] methods = new String[]{"_min", "_max", "_sum", "_mult"};

    @Test
    public void test_min() throws IOException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Random rand = new Random();
        for (String classMethod : methods) {
            for (int numLenght : numNumbers) {
                Main.numbers = new int[numLenght];
                max = numLenght;
                long referenceVal = 0;
                if (classMethod.equals("_min")) {
                    referenceVal = max;
                } else if (classMethod.equals("_max")) {
                    referenceVal = min;
                } else if (classMethod.equals("_sum")) {
                    referenceVal = 0;
                } else if (classMethod.equals("_mult")) {
                    referenceVal = 1;
                }
                String numsFile = numLenght + "_numm.rtf";
                BufferedWriter writer = new BufferedWriter(new FileWriter(numsFile));
                for (int j = 0; j < numLenght; j++) {
                    int randomNum = rand.nextInt(min, max);
                    if (classMethod.equals("_min")) {
                        if (referenceVal > randomNum) {
                            referenceVal = randomNum;
                        }
                    } else if (classMethod.equals("_max")) {
                        if (referenceVal < randomNum) {
                            referenceVal = randomNum;
                        }
                    } else if (classMethod.equals("_sum")) {
                        referenceVal += randomNum;
                    } else if (classMethod.equals("_mult")) {
                        referenceVal *= randomNum;
                    }
                    writer.write(randomNum + " ");
                }
                writer.close();
                long start = System.currentTimeMillis();
                Main.loadFile(numsFile);
                Method method = Main.class.getMethod(classMethod);
                Assertions.assertEquals(referenceVal, method.invoke(Main.class));
                long duration = System.currentTimeMillis() - start;
                System.out.println(numLenght + " ints " + classMethod + "() calculation takes "
                        + duration + " milliseconds to run");
                File targetFile = new File(numsFile);
                targetFile.delete();
            }
        }
    }
}
