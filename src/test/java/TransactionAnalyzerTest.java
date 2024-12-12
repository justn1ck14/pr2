import org.example.Transaction;
import org.example.TransactionAnalyzer;
import org.example.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }


    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        // Виклик методу, який потрібно протестувати
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCSVReader(){
        List<Transaction> testList = TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv");
        Assertions.assertEquals(false, testList.isEmpty(), "Csv файл пустий");
    }

    @Test
    public void testTopExpances(){
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("30-01-2024", -9100.0, "Інші витрати"));
        list.add(new Transaction("20-01-2024", -8800.0, "Подарунки"));
        list.add(new Transaction("05-12-2023", -7850.0, "Сільпо"));
        List<Transaction> originalList = TransactionAnalyzer.findTopExpenses(TransactionCSVReader.readTransactions("https://informer.com.ua/dut/java/pr2.csv"));
        for (int i = 0; i < 3; i++){
            Assertions.assertEquals(originalList.get(i), list.get(i), "Топ витрати не правильно пораховаені");
        }
    }


}
