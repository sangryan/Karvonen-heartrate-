package com.exfp.reps;

import java.io.Console;

public class KarvonenHeartRate {

	public static void main(String[] args) {
		Console c = System.console();

		System.out.print("Age: ");
		int age = Integer.parseInt(c.readLine());
		System.out.print("Resting heart rate: ");
		int hr = Integer.parseInt(c.readLine());

		System.out.printf("Resting Pulse: %d     Age: %d\n", hr, age);
		System.out.println();
		System.out.println("Intensity    | Rate");
		System.out.println("-------------|--------");
		for (int i = 55; i <= 95; i += 5) {
			int thr = (((220 - age) - hr) * i / 100) + hr;
			System.out.printf("%d%%          | %3d bpm\n", i, thr);
		}

		System.exit(1);
	}
}
