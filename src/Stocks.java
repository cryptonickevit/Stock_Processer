import java.io.*;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Stocks {

    private static final String INVALID_SECTOR = "Sector must be one of the following: Technology, Consumer" +
            " Discretionary, " +
            "Communication Services, Financials, Healthcare, Consumer Staples, Energy, Industrials, Utilities";
    private static final String INVALID_MARKET_CAPITALIZATION = "Market Capitalization must end in either B" +
            " for Billion or T for Trillion";
    private static final String INVALID_CURRENT_PRICE = "Current Price must be between 0.01 and 10000 inclusive";
    private static final String INVALID_PRICE_TO_EARNINGS = "Price To Earnings must be between 1 and 100 inclusive";

    public void makeSectorFile(String sector) {
        File f = new File("sector.txt");
        File pf = new File("stockData.txt");

        if (!f.exists()) { // checks to see if sector.txt doesn't exist
            try {
                f.createNewFile(); // creates the file if it doesn't
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end of if statement
        else { // if sector.txt does exist
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                 BufferedReader br = new BufferedReader(new FileReader(pf))
            ) { // using try-with-resources to close after use
                String line = br.readLine(); // reads the line and then pointer moves to the next line
                while (line != null && line.contains(sector)) {
                    String [] fields = line.split("w,"); // creates an array that divides different parts of the stock
                    String ticker = fields[0];
                    String currentPrice = fields[4];
                    pw.println(ticker + " " + currentPrice);
                    line = br.readLine(); // reads the line and then pointer moves to the next line
                }
            } catch (IOException e) {
                e.printStackTrace();
            } // end of else statement

        }
    }

    public void makePriceToEarningsFile(double priceToEarnings, boolean greaterThan) {
        File f = new File("priceToEarnings.txt");
        File pf = new File("stockData.txt");

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
             BufferedReader br = new BufferedReader(new FileReader(pf))) {

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {  // Ensure there are at least 6 fields
                    double peRatio = Double.parseDouble(fields[5]);

                    if ((greaterThan && peRatio >= priceToEarnings) || (!greaterThan && peRatio < priceToEarnings)) {
                        pw.println(line);
                    }
                }
                // Read the next line and continue
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static double parseMarketCap(String marketCapStr) {
        char suffix = marketCapStr.charAt(marketCapStr.length() - 1);
        double value = Double.parseDouble(marketCapStr.substring(0, marketCapStr.length() - 1));

        if (suffix == 'B') {
            return value * 1_000_000_000; // Convert Billion to a number
        } else if (suffix == 'T') {
            return value * 1_000_000_000_000L; // Convert Trillion to a number
        }
        return value;
    }

    // Method to create sortedMarketCap.txt based on Market Cap in ascending order
    public void makeSortedMarketCapFile() {
        File inputFile = new File("stockData.txt"); // Input file with stock data
        File outputFile = new File("sortedMarketCap.txt"); // Output file to write sorted data

        List<String[]> stockData = new ArrayList<>();

        // Step 1: Read the stock data from input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // Split line into fields
                stockData.add(fields); // Add the stock's data to the list
            }

            // Step 2: Implement a simple bubble sort to sort by Market Cap
            for (int i = 0; i < stockData.size() - 1; i++) {
                for (int j = 0; j < stockData.size() - 1 - i; j++) {
                    double marketCapA = parseMarketCap(stockData.get(j)[3]);     // Market Cap of current stock
                    double marketCapB = parseMarketCap(stockData.get(j + 1)[3]); // Market Cap of next stock

                    // Swap if the current stock's Market Cap is greater than the next stock's
                    if (marketCapA > marketCapB) {
                        String[] temp = stockData.get(j);
                        stockData.set(j, stockData.get(j + 1));
                        stockData.set(j + 1, temp);
                    }
                }
            }

            // Step 3: Write the sorted stock tickers and current prices to the output file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile))) {
                for (String[] stock : stockData) {
                    pw.println(stock[0] + " | " + stock[4]); // Write Ticker and Current Price
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeSortedCompanyNameFile() {
        File inputFile = new File("stockData.txt"); // Input file with stock data
        File outputFile = new File("sortedCompanyName.txt"); // Output file to write sorted data

        List<String[]> stockData = new ArrayList<>();

        // Step 1: Read the stock data from input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // Split line into fields
                stockData.add(fields); // Add the stock's data to the list
            }

            // Step 2: Implement bubble sort to sort by company name in descending order
            for (int i = 0; i < stockData.size() - 1; i++) {
                for (int j = 0; j < stockData.size() - 1 - i; j++) {
                    String companyNameA = stockData.get(j)[1];     // Company name of current stock
                    String companyNameB = stockData.get(j + 1)[1]; // Company name of next stock

                    // Swap if the current stock's name is lexicographically less than the next stock's name
                    if (companyNameA.compareTo(companyNameB) < 0) { // Sorting in descending order
                        String[] temp = stockData.get(j);
                        stockData.set(j, stockData.get(j + 1));
                        stockData.set(j + 1, temp);
                    }
                }
            }

            // Step 3: Write the sorted stock tickers and current prices to the output file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile))) {
                for (String[] stock : stockData) {
                    pw.println(stock[0] + " | " + stock[4]); // Write Ticker and Current Price
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateInput(String sector, String marketCapitalization, double currentPrice, double priceToEarnings)
            throws InvalidInputException {

        // List of valid sectors
        List<String> validSectors = Arrays.asList("Technology", "Consumer Discretionary",
                "Communication Services", "Financials", "Healthcare", "Consumer Staples",
                "Energy", "Industrials", "Utilities");

        // Validate sector
        if (!validSectors.contains(sector)) {
            throw new InvalidInputException("Sector must be one of the following: Technology, Consumer Discretionary, "
                    + "Communication Services, Financials, Healthcare, Consumer Staples, Energy, Industrials, Utilities");
        }

        // Validate market capitalization (must end in 'B' for Billion or 'T' for Trillion)
        if (!(marketCapitalization.endsWith("B") || marketCapitalization.endsWith("T"))) {
            throw new InvalidInputException("Market Capitalization must end in either B for Billion or T for Trillion");
        }

        // Validate current price (must be between 0.01 and 10000 inclusive)
        if (currentPrice < 0.01 || currentPrice > 10000) {
            throw new InvalidInputException("Current Price must be between 0.01 and 10000 inclusive");
        }

        // Validate price to earnings ratio (must be between 1 and 100 inclusive)
        if (priceToEarnings < 1 || priceToEarnings > 100) {
            throw new InvalidInputException("Price To Earnings must be between 1 and 100 inclusive");
        }
    }


    public static void main(String[] args) {
        Stocks stocks = new Stocks();

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Sector that the company belongs to:");

        String sector = scan.nextLine();

        System.out.println("Enter the company's Market Capitalization:");

        String marketCapitalization = scan.nextLine();

        System.out.println("Enter the Current Price of the company's stock:");

        double currentPrice = scan.nextDouble();

        System.out.println("Will the filter be greater or less than?");

        boolean greaterThan = scan.nextBoolean();

        System.out.println("Enter the Price to Earnings ratio for the company's stock:");

        double priceToEarnings = scan.nextDouble();

        stocks.makeSectorFile(sector);
        stocks.makePriceToEarningsFile(priceToEarnings, greaterThan);
        stocks.makeSortedMarketCapFile();
        stocks.makeSortedCompanyNameFile();
    }


}