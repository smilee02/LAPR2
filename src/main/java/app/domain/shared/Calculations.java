package app.domain.shared;

public class Calculations {

    public static double summ(double[] array) {
        double summ = 0;
        for (double v : array) {
            summ += v;
        }
        return summ;
    }

    public static double average(double[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        double summ = 0;
        for (double v : array) {
            summ += v;
        }
        return summ/array.length;
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {

        if (a[0].length != b.length) {
            throw new RuntimeException("Impossible to multiply these two matrixes");
        }

        double[][] result = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += (a[i][k] * b[k][j]);
                }
            }
        }
        return result;
    }

    public static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];

        for (int i=0; i<n; ++i) {
            b[i][i] = 1;
        }

        gaussian(a, index);

        for (int i=0; i<n-1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        for (int i=0; i<n; ++i) {
            index[i] = i;
        }
        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }

        int k = 0;

        for (int j=0; j<n-1; ++j) {
            double pi1 = 0;
            for (int i=j; i<n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];

                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;

            for (int i=j+1; i<n; ++i) {
                double pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l=j+1; l<n; ++l) {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }
    }

    public static double[][] matrixTransposta(double[][] matrix) {
        double[][] matrixTransposta = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixTransposta[i][j] = matrix[j][i];
            }
        }
        return matrixTransposta;
    }
}
