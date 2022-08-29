package task2;

public class Main {

    public static void main(String[] args) {
        int[] sourceArr = new int[]{5,6,3,2,5,1,4,9};
        System.out.println("Source array: " + Arrays.toString(sourceArr));
        int[] sortedArr = Arrays.sort(sourceArr);

        assert Arrays.equals(sortedArr, new int[]{1,2,3,4,5,5,6,9}) : "Sorted array not identical to the model!";

        System.out.println("Sorted array: " + Arrays.toString(sortedArr));
    }

    /**
     * Utility class for operations by array.
     */
    public static class Arrays {

        /**
         * Compare two array whether they are identical in their contained values.
         * @param arrayOne First array for compare.
         * @param arrayTwo Second array for compare.
         * @return Returns {@code true} if both arrays is identical and {@code false}
         *                  if is not.
         */
        public static boolean equals(int[] arrayOne, int[] arrayTwo) {
            if (arrayOne.length != arrayTwo.length) return false;
            if (arrayOne == arrayTwo) return true;
            for (int i = 0; i < arrayOne.length; i++) {
                if (arrayOne[i] != arrayTwo[i]) return false;
            }
            return true;
        }

        /**
         * Returns integer array like {@link String}
         * @param array Integer array for transformation.
         * @return Array like {@link String}
         */
        public static String toString(int[] array) {
            String str = "{";
            for (int i = 0; i < array.length; i++) {
                str = str.concat(String.valueOf(array[i]));
                if (i < array.length-1) {
                    str += ",";
                }
            }
            str += "}";
            return str;
        }

        /**
         * Sorting array by quick sort method.
         * @param array Integer array.
         * @return Sorted integer array.
         */
        public static int[] sort(int[] array) {

            if (array.length < 2) return array;
            int base = array[0];
            if (array.length > 2) {
                Random rnd = new Random(0, array.length - 1);
                base = array[rnd.nextInt()];
            }
            int[] lt = new int[]{};
            int[] gt = new int[]{};
            int[] eq = new int[]{};
            for (int value : array) {
                if (value < base) {
                    lt = append(lt, value);
                }
                if (value > base) {
                    gt = append(gt, value);
                }
                if (value == base) {
                    eq = append(eq, value);
                }
            }
            lt = sort(lt);
            gt = sort(gt);
            return join(lt, eq, gt);
        }

        /**
         * Append value to array.
         * @param array Source array.
         * @param values New values for appending.
         * @return Appended array.
         */
        public static int[] append(int[] array, int ... values) {
            if (values.length == 0) return array;
            int[] resArr = new int[array.length + values.length];
            int index = 0;
            for (int val :
                    array) {
                resArr[index++] = val;
            }
            for (int val :
                    values) {
                resArr[index++] = val;
            }
            return resArr;
        }

        /**
         * Joins arrays in one array.
         * @param arrays Arrays for joining.
         * @return Joined array.
         */
        public static int[] join(int[] ... arrays) {
            int size = 0;
            for (int[] arr : arrays) {
                size += arr.length;
            }
            int[] resArr = new int[size];
            for (int i = arrays.length - 1; i >= 0 ; i--) {
                for (int j = arrays[i].length - 1; j >= 0 ; j--) {
                    resArr[--size] = arrays[i][j];
                }
            }
            return resArr;
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