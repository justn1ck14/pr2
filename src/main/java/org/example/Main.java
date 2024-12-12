package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        System.out.println("Загальний баланс: " + totalBalance);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        String monthYear = "01-2024";
        TransactionReportGenerator.printBalanceReport(totalBalance);
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions);
        System.out.println("Кількість транзакцій за " + monthYear + ": " + transactionsCount);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        List<Transaction> minMaxExpences = TransactionAnalyzer.minMaxExpences(transactions, "22-12-2023", "20-01-2024");
        for (Transaction transaction : minMaxExpences) {
            System.out.println(transaction);
        }

        TransactionReportGenerator.printExpenseByCatMonthg(transactions, 1000);

        TransactionReportGenerator.printTopExpensesReport(TransactionAnalyzer.findTopExpenses(transactions));
    }

}