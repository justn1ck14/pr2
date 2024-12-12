package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
    public static void printExpenseByCatMonthg(List<Transaction> transactions, double starsCof) {
        Map<String, Map<String, Double>> expenseMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                expenseMap.putIfAbsent(monthYear, new HashMap<>());
                Map<String, Double> categoryMap = expenseMap.get(monthYear);

                String category = transaction.getDescription();
                categoryMap.put(category, categoryMap.getOrDefault(category, 0.0) + Math.abs(transaction.getAmount()));
            }
        }

        for (String monthYear : expenseMap.keySet()) {
            System.out.println("Місяць: " + monthYear);
            Map<String, Double> categoryMap = expenseMap.get(monthYear);
            for (String category : categoryMap.keySet()) {
                double totalExpense = categoryMap.get(category);
                int symbolCount = (int) (totalExpense / starsCof);

                System.out.println(category + ": " + "*".repeat(symbolCount) + " (" + totalExpense + " грн)");
            }
            System.out.println();
        }
    }
}
