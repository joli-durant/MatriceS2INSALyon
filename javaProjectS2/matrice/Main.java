package matrice;
import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        System.out.println("Welcome to the matrix calculator");
        System.out.println();
        boolean x = true;
        while (x==true) {
            System.out.println("Please enter a proper algorithm that you want to excecute(ex.enter “1” for Addition)");
            System.out.println();
            //ask user to select a available algorithm
            System.out.println("7 types available: 1.Addition 2.Substraction 3.Multiplication 4.ScalarMultiplication 5.Transpose 6.Determinant 7.inverse");
            calculateResult();
            System.out.println("Do you want to restart? No for 1 and Yes for 0!");
            Scanner startprogram = new Scanner(System.in);
            int restartsymbol = startprogram.nextInt();
            if (restartsymbol==1) {
                x = false;
            }
        }
    }

    public static double[][] calculateResult() {
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        System.out.println();
        Matrice calculate = new Matrice();// creat a variable "calculate" to get access to the methodes in Matrix.java
        System.out.println("Please enter the relevant matrices by following the directions below:");
        switch(type) {
            case 1://addition
                System.out.println("please enter your first matrix:");
                double[][] a = enterMatrix();
                System.out.println("please enter your second matrix:");
                double[][] b = enterMatrix();
                double[][] result1 = calculate.addMatrix(a, b);
                if (calculate.executable == true) {
                    calculate.printResult(result1);
                } else {
                    System.out.println("Rows and columns are not equal so addition can not be performed");
                }
                return result1;

            case 2://substraction
                System.out.println("please enter your first matrix:");
                double[][] c = enterMatrix();
                System.out.println("please enter your second matrix:");
                double[][] d = enterMatrix();
                double[][] result2 = calculate.subtractMatrix(c, d);
                if (calculate.executable == true) {
                    calculate.printResult(result2);
                } else {
                    System.out.println("Rows and columns are not equal so substraction can not be performed");
                }
                return result2;

            case 3://multiplication
                System.out.println("please enter your first matrix:");
                double[][] e = enterMatrix();
                System.out.println("please enter your second matrix:");
                double[][] f = enterMatrix();
                double[][] result3 = calculate.multiplyMatrix(e, f);
                if (calculate.executable == true) {
                    calculate.printResult(result3);
                } else {
                    System.out.println("Multiplication can not be performed");
                }
                return result3;

            case 4://scalar multiplication
                System.out.println("please enter the scalor:");
                double scalar = in.nextDouble();
                System.out.println("please enter your matrix:");
                double[][] g = enterMatrix();
                double[][] result4 = calculate.scalarmultiplyMatrix(g, scalar);
                calculate.printResult(result4);
                return result4;

            case 5://transpose matrix
                System.out.println("please enter your matrix:");
                double[][] h = enterMatrix();
                double[][] result5 = calculate.transposeMatrix(h);
                calculate.printResult(result5);
                return result5;

            case 6://determinant
                System.out.println("please enter your matrix:");
                double[][] i = enterMatrix();
                double result6 = calculate.determinantMatrix(i);
                if (calculate.executable == true) {
                    System.out.println("the result is " + result6);
                } else {
                    System.out.println("the matrix is not a square matrix so the process of calculate the determinant can not be performed");
                }//return a vacant array to keep the code in fonction
                return null;

            case 7://inverse
                System.out.println("please enter your matrix:");
                double[][] j = enterMatrix();
                double[][] result7 = calculate.inverseMatrix(j);
                if (calculate.executable == true) {
                    calculate.printResult(result7);
                } else {
                    System.out.println("The matrix is not invertible");
                }
                return result7;

            default:
                System.out.println("Not a valid type");
                return null;//add reurn statement two keep the code in fonction
        }
    }

    public static double[][] enterMatrix(){
        Scanner in = new Scanner(System.in);
        System.out.println("Whats the row number? (ex.3)");
        int row = in.nextInt();
        System.out.println("Whats the column number? (ex.4)");
        int column = in.nextInt();
        double[][] matrix = new double[row][column];//creat an initial matrix
        //ask the user to enter the elements
        System.out.println("Please enter the elements one by one (horizontally), using enter to feed the matrix");

        for(int i = 0; i < row*column;i++){
            System.out.println("plese enter the element ["+(i/column+1)+"]["+(i%column+1)+"]:");
            matrix[i/column][i%column] = in.nextDouble();//to avoid the situation of "out of boarder"
        }
        return matrix;
    }
}
