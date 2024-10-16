import java.util.Scanner;

public class Stocks {

    private static final String INVALID_SECTOR = "Sector must be one of the following: Technology, Consumer" +
            " Discretionary, " +
            "Communication Services, Financials, Healthcare, Consumer Staples, Energy, Industrials, Utilities";
    private static final String INVALID_MARKET_CAPITALIZATION = "Market Capitalization must end in either B" +
            " for Billion or T for Trillion";
    private static final String INVALID_CURRENT_PRICE = "Current Price must be between 0.01 and 10000 inclusive";
    private static final String INVALID_PRICE_TO_EARNINGS = "Price To Earnings must be between 1 and 100 inclusive";

    public void makeSectorFile(String sector) {
    }

    public void makePriceToEarningsFile(double priceToEarnings, boolean greaterThan) {
    }

    public void makeSortedMarketCapFile() {
    }

    public void makeSortedCompanyNameFile() {
    }

    public void validateInput(String sector, String marketCapitalization, double currentPrice, double priceToEarnings)
            throws InvalidInputException(){

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