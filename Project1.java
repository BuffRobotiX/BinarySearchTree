import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) {

		BST bst = new BST();

		//I handle all the input by reading a line, splitting it based on spaces, and parsing the array
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the initial sequence of values:");
		String[] values = input.nextLine().split(" ");

		for (int i = 0; i < values.length; i++) {
			int value;
			try {
				value = Integer.parseInt(values[i]); //If there is a problem parsing, report it.
			}
			catch (NumberFormatException e) {
				System.out.println("Error reading input.");
				break;
			}
			bst.insert(value);
		}

		System.out.print("Pre-order: ");
		bst.preorder();
		System.out.print("\nIn-order: ");
		bst.inorder();
		System.out.print("\nPost-order: ");
		bst.postorder();

		System.out.print("\nCommand? ");
		String command = input.nextLine();
		char c = command.toLowerCase().charAt(0); //Get the command letter

		while (c != 'e') { //Repeat the loop until they enter E or e or anything that starts with e really.

			if (c == 'h') {
				System.out.println(" I Insert a value\n D Delete a value\n P Find predecessor\n S Find successor\n E Exit program\n H Display this message");
			}
			else if (c == 'i' || c == 'd' || c == 'p' || c == 's') { //Needed in case there is an invalid command and the value paramater cannot be read.
				values = command.split(" "); //Split into an array

				if (values.length != 2) { //Error cecking for if there isn't a parameter or too many, ie the array length is not 2 (1 for the command and one for the paramater)
					System.out.println("Error reading parameter.");
				}
				else {
					try { //Error checking for parsing ints, ie they entered something other than an integer
						int value = Integer.parseInt(values[1]);

						switch (c) {
							case 'i': if (bst.insert(value)) { //Only reprint if we actually inserted something.
										System.out.print("In-order: ");
										bst.inorder();
										System.out.println();
									}
								break;
							case 'd': if (bst.delete(value)) { //Only reprint if we actually deleted something.
										System.out.print("In-order: ");
										bst.inorder();
										System.out.println();
									}
								break;
							case 'p': int pred = bst.predecessor(value);
								if (pred != value) //It returns itself meaning there is no predecessor
									System.out.println(pred);
								break;
							case 's': int suc = bst.successor(value);
								if (suc != value) //It returns itself meaning there is no successor
									System.out.println(suc);
								break;
							default: System.out.println("Invalid command."); //Impossible because of the if check
								break;
						}

					}
					catch (NumberFormatException e) {
						System.out.println("Error reading input.");
					}
				}
			}
			else {
				System.out.println("Invalid command.");
			}
			System.out.print("Command? ");
			command = input.nextLine();
			c = command.toLowerCase().charAt(0); //Get the command letter
		}

		System.out.println("Thank you for using!");
	}
}