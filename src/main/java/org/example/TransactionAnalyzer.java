package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
    public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    public static List<Transaction> minMaxExpences(List<Transaction> transactions, String firstData, String lastData){
        LocalDate startDate = LocalDate.parse(firstData, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate endDate = LocalDate.parse(lastData, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate currentDate = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (currentDate.isAfter(startDate.minusDays(1)) && currentDate.isBefore(endDate.plusDays(1)) && transaction.getAmount() < 0) {
                filteredList.add(transaction);
            }
        }
        if(filteredList.isEmpty()){
            return new ArrayList<>();
        }
        Transaction maxExpense = filteredList.get(0);
        Transaction minExpense = filteredList.get(0);

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > maxExpense.getAmount()){
                maxExpense = transaction;
            }
            if (transaction.getAmount() < minExpense.getAmount()){
                minExpense = transaction;
            }
        }

        List<Transaction> resultList = new ArrayList<>();
        resultList.add(maxExpense);
        resultList.add(minExpense);
        return resultList;
    }
}
