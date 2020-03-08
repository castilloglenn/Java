import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Training {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String spaces;
		String asterisks;
		int genCounter = 0;
		int maxima = 0;
		int startX = 0;
		int startY = 0;
		int endX;
		int endY;
		int counterX = 0;
		int counterY = 0;

		while (maxima % 2 == 0 || maxima == 1) {
			System.out.print("Enter diameter:\n(Positive and odd number above 1)\n> ");
			maxima = Integer.parseInt(br.readLine());
		}

		System.out.println("\nHere's your diamond!");
		startY = maxima;

		while (startY != 1) {
			startY -= 2;
			startX += 1;
		}

		endX = startX;
		endY = startY;

		do {
			spaces = "";
			asterisks = "";
			counterX = startX;
			counterY = startY;

			while (counterX > 0) {
				spaces += " ";
				counterX--;
			}
			while (counterY > 0) {
				asterisks += "*";
				counterY--;
			}

			System.out.printf("%s%s\n", spaces, asterisks);
			startY += 2;
			startX -= 1;
		} while (startY < maxima);

		do {
			spaces = "";
			asterisks = "";
			counterX = startX;
			counterY = startY;

			while (counterX > 0) {
				spaces += " ";
				counterX--;
			}
			while (counterY > 0) {
				asterisks += "*";
				counterY--;
			}

			startY -= 2;
			startX += 1;
			System.out.printf("%s%s\n", spaces, asterisks);
		} while (startY >= endY);
	}
}
