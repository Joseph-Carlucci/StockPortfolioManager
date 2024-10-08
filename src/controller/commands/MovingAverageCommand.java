package controller.commands;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the moving average of a given stock.
 * The moving average is the average of the closing prices of a stock over a specified number of
 * days, going back from the given date.
 * (Closing prices only).
 */
public class MovingAverageCommand extends AWriterCommand {
  /**
   * Constructs a MovingAverageCommand object.
   * @param out an appendable object to send output to
   */
  public MovingAverageCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the MovingAverageCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalStateException if the command fails to execute
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String ticker = getNextString(scanner);
    String dateEntered = getNextString(scanner);
    int days = getPositiveInt(scanner);

    tryWrite(ticker, dateEntered, days);

    try {
      double movingAverage = model.movingAverage(ticker, LocalDate.parse(dateEntered), days);
      this.out.append(String.format("Moving average is: %.2f\n", movingAverage));
    } catch (Exception e) {
      throw new IllegalStateException("Could not process command.");
    }
  }

  /**
   * Gets the instructions for the MovingAverageCommand.
   * @return the instructions for the MovingAverageCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Moving Average: \n");
    instructions.append("This command calculates the moving average of a stock over a given period "
            + "of time.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (MovingAverage)\n");
    instructions.append("2. Stock ticker symbol\n");
    instructions.append("3. Ending date in the format yyyy-mm-dd\n");
    instructions.append("4. Number of days to calculate the moving average over\n");
    return instructions.toString();
  }
}
