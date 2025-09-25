public class Lab01 {
    public static void main(String[] args) {
        long[] c = new long[13];
        for (int i = 0; i < 13; i++) {
            c[i] = i + 3;
        }

        double[] x = new double[16];
        for (int i = 0; i < 16; i++) {
            x[i] = Math.random() * 24 - 14;
        }

        double[][] c2 = new double[13][16];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 16; j++) {
                if (c[i] == 12) {
                    c2[i][j] = Math.cos(Math.sin((x[j] * x[j]) + 1));
                } else if (c[i] == 3 || c[i] == 4 || c[i] == 6 || c[i] == 8 || c[i] == 9 || c[i] == 13) {
                    c2[i][j] = Math.asin(Math.exp(-Math.pow(Math.cos(x[j]), 2) * Math.sqrt(3)));
                } else {
                    c2[i][j] = 12 * Math.pow(2.0 / 3.0 / Math.sin(x[j]), 2);
                }
            }
        }

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.printf("%.4f ", c2[i][j]);
            }
            System.out.println();
        }
    }
}