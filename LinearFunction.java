import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Training {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int counter = 0;
		int startX = 0;
		int startY = 1;

		System.out.println("Linear Function\nf(x)=ax+b");

		System.out.print("Enter value of a: ");
		int a = Integer.parseInt(br.readLine());

		System.out.print("Enter value of b: ");
		int b = Integer.parseInt(br.readLine());

		System.out.printf("\nLinear Equation:\nf(x)=%dx%s%d\n"
							, a
							, (b > 0) ? "+" : "-"
							, Math.abs(b));

		while (counter < 4) {
			startX -= a;
			startY -= b;
			counter++;
		}

		counter = 0;
		System.out.printf("The graph is %s with the following coordinates:\n"
							, (a > 0 && b > 0) ? "diagonal to right" : "diagonal to left");
		while (counter < 10) {
			System.out.printf("#%d: (%d, %d)\n"
							, counter +1
							, startX
							, startY);
			startX += a;
			startY += b;
			counter++;
		}
	}
}
