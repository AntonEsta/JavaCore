package task1;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[5][5];

        // randomize array
        Random rnd = new Random(-10, 100);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = rnd.nextInt();
            }
        }
        // get max value
        int maxValue = Arrays.getMaxValue(arr);
        // get min value
        int minValue = Arrays.getMinValue(arr);
        // get average value
        double avgValue = Arrays.getAvgValue(arr);

        // print results
        String prefix = "Source Array:";
        String postfix = String.format("Minimum value: %d\nMaximum value: %d\nAverage value: %.2f\n", minValue, maxValue, avgValue);
        Arrays.print(arr, prefix, postfix);
    }


    /**
     * Utility class for operations by array.
     */
    public static class Arrays {

        /**
         * Prints a two-dimensional array in a user-friendly style.
         * @param array Two-dimensional array for printing.
         */
        public static void print(int[][] array) {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_GREEN = "\u001B[32m";
            boolean headComplete = false;
            boolean rowNumber;
            for (int i = 0; i < array.length; i++) {
                rowNumber = false;
                for (int j = 0; j < array[i].length; j++) {
                    if (!headComplete && i == 0) {
                        for (int k = 0; k < array[i].length; k++) {
                            System.out.print("\t" + ANSI_GREEN + (k + 1) + ANSI_RESET);
                        }
                        headComplete = true;
                    }
                    if (!rowNumber) {
                        System.out.print("\n" + ANSI_GREEN + (i + 1) + " |" + ANSI_RESET);
                        rowNumber = true;
                    }
                    System.out.print("\t" + array[i][j]);
                }
            }
            System.out.println();
        }

        /**
         * Prints a two-dimensional array in a user-friendly style.
         * @param array Two-dimensional array for printing.
         * @param prefix {@link String} for printing before array.
         * @param postfix {@link String} for printing before array.
         */
        public static void print(int[][] array, String prefix, String postfix) {
            System.out.println("\n" + prefix);
            print(array);
            System.out.println(postfix);
        }

        /**
         * Returns maximum value from integer array.
         * @param array Integer array.
         * @return Maximum value from integer array.
         */
        public static int getMaxValue(int[] array) {
            int max = 0;
            for (int value :
                    array) {
                if (value > max) {
                    max = value;
                }
            }
            return max;
        }

        /**
         * Returns maximum value from two-dimensional integer array.
         * @param array Two-dimensional integer array.
         * @return Maximum value from array.
         */
        public static int getMaxValue(int[][] array) {
            int max = 0;
            for (int[] arr :
                    array) {
                int tmpMax = getMaxValue(arr);
                if (tmpMax > max) {
                    max = tmpMax;
                }
            }
            return max;
        }

        /**
         * * Returns minimum value from integer array.
         * @param array Integer array.
         * @return Minimum value from integer array.
         */
        public static int getMinValue(int[] array) {
            int min = array[0];
            for (int value :
                    array) {
                if (value < min) {
                    min = value;
                }
            }
            return min;
        }

        /**
         * Returns minimum value from two-dimensional integer array.
         * @param array Two-dimensional integer array.
         * @return Minimum value from array.
         */
        public static int getMinValue(int[][] array) {
            int min = array[0][0];
            for (int[] arr :
                    array) {
                int tmpMin = getMinValue(arr);
                if (tmpMin < min) {
                    min = tmpMin;
                }
            }
            return min;
        }

        /**
         * Returns average value from integer array.
         * @param array Integer array.
         * @return Average value from array.
         */
        public static double getAvgValue(int[] array) {
            double avg = 0;
            for (int value :
                    array) {
                    avg += value;
            }
            return avg/array.length;
        }

        /**
         * Returns average value from two-dimensional integer array.
         * @param array Two-dimensional integer array.
         * @return Average value from array.
         */
        public static double getAvgValue(int[][] array) {
            double avg = 0;
            for (int[] arr :
                    array) {
                avg += getAvgValue(arr);
            }
            return avg/array.length;
        }
    }

    /**
     * Random value generation class.
     */
    public static class Random {

        private final int start;
        private final int end;

        public Random(int start, int end) {
            if (start < end) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        }

        /**
         * Returns randomized integer number.
         * @return random integer value.
         */
        public int nextInt() {
            long mul = 0x5DEECE66DL;
            int rndNum;
            do {
                rndNum = (int) (System.nanoTime() * mul) >> getZeroBitsCount(end);
            } while ((rndNum >= end) || (rndNum <= start));
            return rndNum;
        }

        /**
         * Calculating the number of zero-bits from start.
         * @param number Number to analyze.
         * @return Number of bits used.
         */
        private static short getZeroBitsCount(int number) {
            short bit = 32;
            while (number != 0) {
                number >>= 1;
                bit--;
            }
            return --bit;
        }

    }

}
