//Garrett Wayne
//
//This program executes a range of expressions including 
//those with fractions and mixed numbers
//

import java.util.Scanner;

public class FracCalc5 {

	public static void main(String[] args) {
		// read in the first expression
		Scanner scanner = new Scanner(System.in);
		String input = readLine(scanner);

		// loop until user enters 'quit'
		while (!input.equals("quit")) {
			System.out.println(produceAnswer(input));
			System.out.println();
			input = readLine(scanner);
		}
		// if user enters quit, the while loop closes
		System.out.println("TERMINATED");
		scanner.close();
	}

	public static String produceAnswer(String input) {
		String firstOperand = getFirstOperand(input);
		String secondOperand = getSecondOperand(input);
		String operator = getOperator(input);
		System.out.println("First operand = " + firstOperand
			+ "; second operand = " + secondOperand + "; operator = "
			+ operator);

		if (operator.equals("+")) { 		// Sends input to addition method for
							// further evaluation
			return addition(input);
		} else if (operator.equals("-")) { 	// Sends input to subtraction method
							// for further evaluation
			return subtraction(input);
		} else if (operator.equals("*")) { 	// Sends input to multiplication
							// method for further evaluation
			return multiplication(input);
		} else if (operator.equals("/")) { 	// Sends input to division method for
							// further evaluation
			return division(input);
		} else {
			return "Invalid operator!";
		}
	}

	public static String addition(String input) {
		int num = (firstNum(input) + (Math.abs(firstWholeNumber(input)) * firstDenom(input)))
				* makeSure1(input)
				* secondDenom(input)
				+ (secondNum(input) + secondDenom(input)
						* Math.abs(secondWholeNumber(input)))
				* makeSure2(input) * firstDenom(input); // The formula for the
									// answer's numerator
		int denom = firstDenom(input) * secondDenom(input); 	// The formula for
									// the answer's
									// denominator

		return simp(num, denom); 	// Takes the above Numerator and Denominator
						// pair for simplification
	}

	public static String subtraction(String input) {
		int num = (firstNum(input) + Math.abs(firstWholeNumber(input))
				* firstDenom(input))
				* secondDenom(input)
				* makeSure1(input)
				- (secondNum(input) + secondDenom(input)
						* Math.abs(secondWholeNumber(input)))
				* firstDenom(input) * makeSure2(input);
		int denom = firstDenom(input) * secondDenom(input); 	// The formula for
									// the answer's
									// denominator

		return simp(num, denom); 	// Takes the above Numerator and Denominator
						// pair for simplification
	}

	public static String multiplication(String input) {
		int num = ((firstNum(input) + (Math.abs(firstWholeNumber(input)) * firstDenom(input))) * makeSure1(input))
			* ((secondNum(input) + Math.abs(secondWholeNumber(input))
			* secondDenom(input)) * makeSure2(input)); 	// The formula
									// for the
									// answer's
									// numerator
		
		int denom = firstDenom(input) * secondDenom(input); 	// The formula for
									// the answer's
									// denominator

		return simp(num, denom); 	// Takes the above Numerator and Denominator
						// pair for simplification
	}

	public static String division(String input) {
		int num = ((firstNum(input) + Math.abs(firstWholeNumber(input))
				* firstDenom(input)) * secondDenom(input))
				* makeSure1(input); // The formula for the answer's numerator

		int denom = (firstDenom(input) * (secondNum(input) + Math
				.abs(secondWholeNumber(input)) * secondDenom(input)))
				* makeSure2(input); // The formula for the answer's denominator

		if (denom < 0) {
			num *= -1;
			denom *= -1;
		}

		return simp(num, denom); 	// Takes the above Numerator and Denominator
						// pair for simplification
	}

	public static int firstWholeNumber(String wholeNumber) { 	// Finds the first
									// whole number
									// and returns
									// it to the
									// formula
		int wholeNumber1 = 0;
		int space = wholeNumber.indexOf(" ");
		int under = wholeNumber.indexOf("_");

		if (under < space && under > 0) {
			String int1 = wholeNumber.substring(0, under);
			wholeNumber1 = Integer.parseInt(int1);
			return wholeNumber1;
		}
		return wholeNumber1;
	}

	public static int secondWholeNumber(String wholeNumber) { 	// Finds the
									// second whole
									// number and
									// returns it to
									// the formula
		int wholeNumber2 = 0;
		int space = wholeNumber.lastIndexOf(" ");
		int under = wholeNumber.lastIndexOf("_");

		if (under > space) {
			String int2 = wholeNumber.substring(space + 1, under);
			wholeNumber2 = Integer.parseInt(int2);
			return wholeNumber2;
		}
		return wholeNumber2;
	}

	public static int firstNum(String num) { 	// Finds the first numerator and
							// returns it to the formula
		String firstNum;
		int space = num.indexOf(" ");
		int slash = num.indexOf("/");
		int under = num.indexOf("_");

		if (slash < 0 || slash > space) {
			firstNum = num.substring(0, space);
			int num1 = Integer.parseInt(firstNum);
			return num1;
		}
		if (under >= 0 && under <= space) {
			firstNum = num.substring(under + 1, slash);
			int num1 = Integer.parseInt(firstNum);
			return num1;
		}
		firstNum = num.substring(0, slash);
		int num1 = Integer.parseInt(firstNum);
		return num1;
	}

	public static int secondNum(String num) { 	// Finds the second numerator and
							// returns it to the formula
		String secondNum;
		int space = num.lastIndexOf(" ");
		int slash = num.lastIndexOf("/");
		int under = num.lastIndexOf("_");

		if (slash < space) {
			secondNum = num.substring(space + 1);
			int num2 = Integer.parseInt(secondNum);
			return num2;
		}
		if (under >= 0 && under >= space) {
			secondNum = num.substring(under + 1, slash);
			int num2 = Integer.parseInt(secondNum);
			return num2;
		}
		secondNum = num.substring(space + 1, slash);
		int num2 = Integer.parseInt(secondNum);
		return num2;
	}

	public static int firstDenom(String denom) { 	// Finds the first denominator
							// and returns it to the
							// formula
		String firstDenom = "1";
		int space = denom.indexOf(" ");
		int slash = denom.indexOf("/");
		int denom1 = Integer.parseInt(firstDenom);
		if (slash >= 0 && slash < space) {
			firstDenom = denom.substring(slash + 1, space);
			denom1 = Integer.parseInt(firstDenom);
			return denom1;
		}
		return denom1;
	}

	public static int secondDenom(String denom) { 	// Finds the second
							// denominator and returns
							// it to the formula
		String secondDenom = "1";
		int space = denom.lastIndexOf(" ");
		int slash = denom.lastIndexOf("/");
		int denom2 = Integer.parseInt(secondDenom);
		if (slash > space && slash != (space + 1)) {
			secondDenom = denom.substring(slash + 1);
			denom2 = Integer.parseInt(secondDenom);
			return denom2;
		}
		return denom2;
	}

	public static int makeSure1(String ripe) { 	// This method makes sure that
							// the signs and negatives are
							// okay to evaluate
		int sign1 = 1;
		if (firstWholeNumber(ripe) < 0) {
			sign1 = -1;
			return sign1;
		}
		return sign1;
	}

	public static int makeSure2(String riper) { 	// This method makes sure that
							// the signs and negatives are
							// okay to evaluate
		int sign2 = 1;
		if (secondWholeNumber(riper) < 0) {
			sign2 = -1;
			return sign2;
		}
		return sign2;
	}

	public static String simp(int num, int den) {
		int newNum = num;
		int newDen = den;
		int newWholeNumber = 0;
		for (int i = Math.abs(num); i >= 2; i--) {
			if (newNum % i == 0 && newDen % i == 0) {
				newNum = num / i;
				newDen = den / i;
			}
		}
		if (Math.abs(newNum) > newDen && newDen != 0) {
			newWholeNumber = newNum / newDen;
			newNum = newNum % newDen;
		}
		if (newDen == 0) {
			return "Error: can't divide by zero"; 	// Cannot divide
								// by zero
		} else if (newNum == newDen) {
			return "" + newNum / newDen; 		// Prints answer
								// (ex. 10/10 would equal 1)
		} else if (newNum == 0 && newWholeNumber == 0) {
			return "" + newWholeNumber; 		// Prints answer
								// (ex. 1/2 - 1/2 would equal 0)
		} else if (newWholeNumber == 0) {
			return newNum + "/" + newDen; 		// Prints answer 
								// (ex. 3/6 + 3/6 would equal 5/6)
		} else if (newNum == 0 && newWholeNumber != 0) {
			return "" + newWholeNumber; 		// Prints answer
								// (ex. 1/2 + 1/2 would equal 1)
		}
		else {
			return newWholeNumber + "_" + Math.abs(newNum) + "/" + newDen;
		} 						// Prints answer (ex. 2 + 3_1/4 would equal 5_1/4)
	}

	private static String getFirstOperand(String input) { 	// Looks for the first
								// operand in the
								// input and returns
								// the substring
		// The first operand is the substring before the first space
		return input.substring(0, input.indexOf(" "));
	}

	private static String getSecondOperand(String input) { 	// Looks for the
								// second operand in
								// the input and
								// returns the
								// substring
		// The second operand is the substring after the second (i.e., last) space
		return input.substring(input.lastIndexOf(" ") + 1);
	}

	private static String getOperator(String input) { 	// Looks for the operator
								// in the input and
								// returns the substring
		// The operator is the character after the first space
		return input.substring(input.indexOf(" ") + 1, input.indexOf(" ") + 2);
	}

	private static String readLine(Scanner scanner) { 	// The actual scanner that
								// the loop refers to
								// and reads the input
		System.out.println("Please enter an expression (or type \"quit\"): ");
		return scanner.nextLine().trim().toLowerCase(); // ex. turns 'Quit' and
								// 'QUIT ' into quit
	}
}
