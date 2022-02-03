import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s20511 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);
        Scanner sr = new Scanner(file);

        int currentNumber = sr.nextInt();
        int currentSumUp = currentNumber;
        int currentSumDown = currentNumber;
        int lastNumber;
        int sequenceUp = 1;
        int sequenceDown = 1;
        int finalSequence = 1;
        int finalSum = currentNumber;

        while (sr.hasNextInt()) {

            lastNumber = currentNumber;
            currentNumber = sr.nextInt();

            if (currentNumber == lastNumber) {
                currentSumDown += currentNumber;
                sequenceDown++;
                currentSumUp += currentNumber;
                sequenceUp++;
            } else if (currentNumber < lastNumber) {
                currentSumDown += currentNumber;
                currentSumUp = currentNumber;
                sequenceUp = 1;
                sequenceDown++;
            } else {
                currentSumUp += currentNumber;
                currentSumDown = currentNumber;
                sequenceDown = 1;
                sequenceUp++;
            }

            if (sequenceUp > finalSequence) {
                finalSum = currentSumUp;
                finalSequence = sequenceUp;
            }

            if (sequenceDown > finalSequence) {
                finalSum = currentSumDown;
                finalSequence = sequenceDown;
            }
        }

        sr.close();
        System.out.println(finalSequence + " " + finalSum);
    }
}
