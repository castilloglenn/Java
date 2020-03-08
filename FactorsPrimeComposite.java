import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Arrays;

public class FactorsPrimeComposite {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter number: ");
		int num = Integer.parseInt(br.readLine());

		int[] factors = new int[num];
		int factorsCounter = 0;
		int index = 0;
		boolean dupli1 = false;
		boolean dupli2 = false;

		System.out.printf("\nThe factors of %d are ", num);

		for (int x = 1; x <= num; x++) {
			for (int y = 1; y <= num; y++) {
				if (x * y == num) {
					for (int i = 0; i < factors.length; i++) {
						if (x == factors[i])
							dupli1 = true;
						if (y == factors[i])
							dupli2 = true;
					}

					if (!dupli1) {
						factors[index] = x;
						index++;
					}
					if (!dupli2) {
						factors[index] = y;
						index++;
					}
				}
			}
		}

		Arrays.sort(factors);
		for (int fnum: factors) {
			if (fnum > 0 && fnum != factors[factors.length - 1]) {
				System.out.print(fnum + ", ");
				factorsCounter++;
			}
		}
		System.out.printf("and %d, ", factors[factors.length - 1]);

		if (factorsCounter == 1) {
			System.out.println("It is a prime number");
		} else {
			System.out.println("It is a composite number");
		}
	}
}
