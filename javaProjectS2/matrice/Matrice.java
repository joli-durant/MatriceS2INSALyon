package matrice;

public class Matrice {
    boolean executable = true;
    // Addition of matrices
    public double[][] addMatrix(double[][] matrix1, double[][] matrix2){
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;


        double sumMatrix[][] = new double[rows1][columns1];
        if(rows1 == rows2 && columns1 == columns2){// determine if the two matrices are in the same sizes
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns1; j++){
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }

        } else {
            executable = false;
        }
        return sumMatrix;
    }

    // subtraction of matrices
    public double[][] subtractMatrix(double[][] matrix1, double[][] matrix2){
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;

        double subMatrix[][] = new double[rows1][columns1];
        if(rows1 == rows2 && columns1 == columns2){// determine if the two matrices are in the same sizes
            boolean executable = true;
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns1; j++){
                    subMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }

        } else{
            executable = false;
        }

        return subMatrix;
    }

    // Multiplication of matrices
    public double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2){
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;

        double mulMatrix[][] = new double[rows1][columns2];
        if(columns1 == rows2){//determine if the two matrices can be multiplied
            boolean executable = true;
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns2; j++){
                    for (int k=0; k<columns1; k++)
                        mulMatrix[i][j] = mulMatrix[i][j] + matrix1[i][k] * matrix2[k][j];
                }
            }

        } else {
            executable = false;
        }
        return mulMatrix;
    }

    // Scalar multiplication of matrices
    public double[][] scalarmultiplyMatrix(double[][] adjA, double d){
        boolean executable = true;
        int rows1 = adjA.length;
        int columns1 = adjA[0].length;

        double mulMatrix[][] = new double[rows1][columns1];
        for (int i=0; i<rows1; i++){
            for (int j=0; j<columns1; j++){
                mulMatrix[i][j] = d * adjA[i][j];
            }
        }

        return mulMatrix;
    }

    //transpose of a matrix
    public double[][] transposeMatrix(double[][] matrix1){
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;

        double transposeMatrix[][] = new double[columns1][rows1];
        for (int i=0; i<columns1; i++){
            for (int j=0; j<rows1; j++){
                transposeMatrix[i][j] = matrix1[j][i];
            }
        }

        return transposeMatrix;
    }


    //determinant of a matrix
    //principle: expand the determinant by minors(cofactor)
    public double determinantMatrix(double[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;
        double result = 0;//initialize the result

        if(rows == columns){//determine if it is a square matrix
            executable = true;
            //when the size equals 1
            if(rows == 1){
                result = matrix[0][0];
                return result;
            }

            //when the size equals 2
            if(rows == 2){
                result = matrix[0][0]* matrix[1][1] - matrix[0][1]* matrix[1][0];
                return result;
            }

            //when the size equals n >= 3
            //expand by the first line
            for(int j = 0; j < matrix[0].length; j++){//an n matrix takes n times of operations(the length of first row)
                //to store the minor
                double minor[][] = Minor_of_Matrix(matrix, 0, j);
                result = result + (matrix[0][j]*Math.pow(-1, j)* determinantMatrix(minor));
            }

        }
        else{
            executable = false;
        }
        return result;
    }


    //the inverse of matrix
    private double[][] Minor_of_Matrix(double [][] A, int line, int column){ // The method for finding the minor of the ith row and the jth column of the Matrix A //
        double [][] B = new double[A.length-1][A[0].length-1];
        int i1 = 0;
        int j1 = 0;
        for(int i = 0; i < A.length-1; i++){
            if( i1 == line){                                                     // Skip the ith row according to the definition of the minor //
                i1++;
            }
            for(int j = 0; j < A[0].length-1; j++){

                if( j1 == column){                                              // Skip the jth column according to the definition of the minor //
                    j1++;
                }
                B[i][j] = A[i1][j1];                                              // Copy the value of the rest part of the matrix //
                j1++;
            }
            j1 = 0;
            i1++;
        }
        return B;
    }

    public double[][] inverseMatrix(double [][] A){            //Here, we use the formula A^(-1) = (-1)^(i+j)*(A*)/det(A) to calculate the inverse matrix, A* is the adjugate matrix.//
        double detA = this.determinantMatrix(A);
        if(detA == 0){
            executable = false;// The matrix is invertible only when its determinant isn't equal to 0, here's to prevent error of division of zero.//
            return A;
        }else{
            double [][] Inverse = new double [A.length][A[0].length];
            double [][] adjA = new double[A.length][A[0].length];        // Firstly we search for the adjugate matrix //
            for ( int i = 0; i < adjA.length; i++) {
                for ( int j = 0; j < adjA[0].length; j++) {
                    adjA[i][j] = (Math.pow(-1,i+j))*this.determinantMatrix(this.Minor_of_Matrix(A, i, j)); //Find its transpose matrix according to the definition using the method for finding the minors. //
                    if (adjA[i][j] == -0.) {
                        adjA[i][j] = Math.abs(adjA[i][j]);
                    }
                }
            }
            adjA = this.transposeMatrix(adjA);
            Inverse = this.scalarmultiplyMatrix(adjA, 1/detA);       // We obtain the inverse matrix by multiply it by 1/det(A) //
            return Inverse;

        }
    }

    //print the result of calculation
    public void printResult(double[][] matrix){
        System.out.println("the result is:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }

    }

}
