package controller.io.writers;

import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;
import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;
import model.stock.Stock;

import static org.junit.Assert.assertTrue;

/**
 * Test cases for PortfolioTxtWriter.
 */
public class PortfolioTXTWriterTest {
  @Test
  public void testWrite() {
    IPortfolioWithTransactions portfolio = new PortfolioWithTransactions("myPortfolio");
    ITransaction transaction1 = new BuyTransaction(
            new Stock("AAPL"),
            10,
            LocalDate.of(2020, 1, 1));
    ITransaction transaction2 = new SellTransaction(
            new Stock("AAPL"),
            5,
            LocalDate.of(2020, 1, 2));
    portfolio = portfolio.addTransaction(transaction1);
    portfolio = portfolio.addTransaction(transaction2);
    File file = new File("out/production/portfolios/myPortfolio.txt");
    assertTrue(file.exists());
    assertTrue(file.length() > 0);
  }
}
