package com.example.teksystemassessment.problemSolving.question3;

public class codingSolution {

    public static void main(String[] args) {
        int[] planets = new int[]{2, 4, 6, 5, 4};
        System.out.println(getPlanetToDestroy(planets));
    }
    public static int getPlanetToDestroy (int[] planets) {
        if (planets == null || planets.length == 0) return -1;

        int n = planets.length;

        for (int i = 0; i < n; i++) {
            int odd = 0;
            int even = 0;
            int pos = 1;

            for (int j = 0; j < n; j++) {
                if (j == i) continue;

                if (pos % 2 == 1) {
                    odd += planets[j];
                } else {
                    even += planets[j];
                }
                pos++;
            }

            if (odd == even) {
                return i + 1;
            }
        }

        return -1;
    }
}
