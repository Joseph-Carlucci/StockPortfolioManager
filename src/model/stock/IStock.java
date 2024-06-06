package model.stock;

import java.time.LocalDate;

public interface IStock {
  double getPrice(LocalDate date);
  String getTicker();
  IStock addStockData(LocalDate date, IStockData data);
}
