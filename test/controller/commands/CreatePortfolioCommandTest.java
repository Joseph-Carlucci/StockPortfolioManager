package controller.commands;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for CreatePortfolioCommand.
 */
public class CreatePortfolioCommandTest {
  ICommand command;
  Appendable output;
  IModel2 model;
  Scanner scanner;
  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new CreatePortfolioCommand(output);
  }

  @Test
  public void execute() throws IOException {
    input = new StringReader("myPortfolio myPortfolio2");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio Created: myPortfolio\n", output.toString());
    command.execute(model, scanner);
    assertEquals("Portfolio Created: myPortfolio\n"
            + "Portfolio Created: myPortfolio2\n", output.toString());
    model.getStocksInPortfolio("myPortfolio");

  }

  @Test
  public void getInstructions() {
    assertEquals("Create Portfolio: \n"
            + "This command creates a new portfolio for the user.\n"
            + "Enter the following parameters separated by spaces:\n"
            + "1. Command name (CreatePortfolio)\n"
            + "2. Portfolio name\n", command.getInstructions());
  }
}