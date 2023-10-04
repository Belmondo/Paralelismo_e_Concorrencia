import parallel.MatrixMultiplicationParallel;
import serial.SerialMultiplication;

public class main {
    public static void main(String [] args){
        SerialMultiplication.startSerial();
        MatrixMultiplicationParallel.startParallel();
    }
}
