

# 📈 Stocks Processing Application

Welcome to the **Stocks Processing Application**! This Java-based application processes stock market data based on various criteria such as **sectors**, **market capitalization**, and **price-to-earnings ratios**. The program generates useful output files containing filtered stock data, making it easier to analyze market trends.


## 🚀 Features

- **Process Stock Data**: Reads stock data from a file and processes it based on the user's input.
- **Filter by Sector**: Easily filter stocks by industry sectors such as Technology, Healthcare, Financials, and more.
- **Price-to-Earnings Ratio Analysis**: Generate lists of stocks based on whether their P/E ratio is above or below a given value.
- **Market Capitalization Sorting**: Sort companies by market capitalization (in billions or trillions) in ascending order.
- **Company Name Sorting**: Sort companies alphabetically by their name.
- **Custom Exception Handling**: Handles invalid user inputs with a custom exception called `InvalidInputException`.

## 📝 Input File Structure

The application processes an input file (`stockData.txt`) where each line follows this format:

```
Ticker,Company Name,Sector,Market Capitalization,Current Price,P/E Ratio
```

### Example Data:
```
AAPL,Apple Inc.,Technology,2.5T,145.32,25.38
MSFT,Microsoft Corporation,Technology,2.2T,280.74,31.15
JPM,JPMorgan Chase & Co.,Financials,463B,154.78,12.58
```

The data is separated by commas without spaces, and the market capitalization values end with either 'B' for billions or 'T' for trillions.

## 🛠️ Methods Overview

The application implements the following methods:

### 1. `makeSectorFile(String sector)`
- Creates a file called `sectors.txt` containing stock tickers and current prices for companies in the specified sector.
- **Output Format**:
  ```
  [Stock Ticker] | [Current Price]
  ```

### 2. `makePriceToEarningsFile(double priceToEarnings, boolean greaterThan)`
- Creates a file called `priceToEarnings.txt` containing stocks filtered by P/E ratios.
- If `greaterThan` is true, it includes stocks with a P/E ratio greater than or equal to the given value. If false, it includes those with a lower P/E ratio.

### 3. `makeSortedMarketCapFile()`
- Creates a file called `sortedMarketCap.txt` that contains all stocks sorted by market capitalization in ascending order.

### 4. `makeSortedCompanyNameFile()`
- Creates a file called `sortedCompanyName.txt` with all stock data sorted alphabetically by company name.

### 5. `validateInput(String sector, String marketCapitalization, double currentPrice, double priceToEarnings)`
- Ensures that user input is valid. If invalid, throws a custom `InvalidInputException` with a specific error message.

## 🚧 Custom Exception Handling

The custom exception class `InvalidInputException` is thrown when the user provides invalid input. The conditions for valid input are as follows:

- **Sector**: Must be one of the following:  
  `{Technology, Consumer Discretionary, Communication Services, Financials, Healthcare, Consumer Staples, Energy, Industrials, Utilities}`
- **Market Capitalization**: Must end in either `'B'` (Billions) or `'T'` (Trillions).
- **Current Price**: Must be between `0.01` and `10,000` inclusive.
- **Price-to-Earnings Ratio**: Must be between `1` and `100` inclusive.

If any of these conditions are not met, an exception is thrown with a relevant error message, such as:

- `"Invalid Sector: Sector must be one of the following..."`.
- `"Market Capitalization must end in either B for Billion or T for Trillion"`.
- `"Current Price must be between 0.01 and 10000 inclusive"`.
- `"Price to Earnings must be between 1 and 100 inclusive"`.

## 🔧 Sorting Algorithms

For both the **Market Capitalization** and **Company Name** sorting, the program implements custom sorting using **nested loops**. No built-in sorting methods (e.g., `Collections.sort()`) are used, and the sorting is done manually to reinforce basic algorithmic concepts.

### Sorting by Market Capitalization:
- Convert the values from trillions and billions to a comparable numeric format.
- Sort them in ascending order.

### Sorting by Company Name:
- Use the `String.compareTo()` method to sort company names alphabetically.

## 💾 Output Example

For a sector file generated using the "Energy" sector, the output in `sectors.txt` might look like this:

```
XOM | 57.32
CVX | 106.34
```

## 📂 File Structure

```
/project-root
  ├── /src
  │   ├── Stocks.java
  │   ├── InvalidInputException.java
  │   └── stockData.txt
  ├── README.md
  └── /output
      ├── sectors.txt
      ├── priceToEarnings.txt
      ├── sortedMarketCap.txt
      └── sortedCompanyName.txt
```

## 🖥️ How to Run

1. Compile the Java program:
   ```
   javac Stocks.java
   ```

2. Run the program:
   ```
   java Stocks
   ```

3. Follow the prompts to enter input for sector, price-to-earnings ratio, and sorting preferences.

