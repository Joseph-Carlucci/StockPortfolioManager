package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import model.IModel;

/**
 * This class represents the CrossoverCommand class that implements the IController interface.
 * A crossover happens when the closing price for a day is greater than the x day moving average.
 * The crossover command determines which days are x-day crossovers for a given stock, a
 * specified date range, and a specified value of x (the number of days in the moving average).
 */
public class CrossoverCommand implements ICommand {
  private final Appendable out;

  public CrossoverCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = "";
    String date = "";
    int days = 0;
    if (scanner.hasNext()) {
      ticker = scanner.next();
    }

    if (scanner.hasNext()) {
      date = scanner.next();
    }

    if (scanner.hasNextInt()) {
      days = scanner.nextInt();
    }

    if (!date.isEmpty() && days > 0) {
      boolean isCrossOver = model.crossOver(ticker, date, days);

      try {
        this.out.append(String.format("Is there a crossover: " + isCrossOver));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
