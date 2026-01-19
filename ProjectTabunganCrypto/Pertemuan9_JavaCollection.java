import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pertemuan9_JavaCollection {

    static class Wallet {
        private final Map<String, Double> saldoCoin = new HashMap<>();
        private final List<String> history = new ArrayList<>();

        public void deposit(String coin, double amount) {
            coin = coin.toUpperCase();
            saldoCoin.put(coin, saldoCoin.getOrDefault(coin, 0.0) + amount);
            history.add("DEPOSIT " + coin + " : " + amount);
        }

        public void withdraw(String coin, double amount) {
            coin = coin.toUpperCase();
            double saldo = saldoCoin.getOrDefault(coin, 0.0);

            if (amount > 0 && amount <= saldo) {
                saldoCoin.put(coin, saldo - amount);
                history.add("WITHDRAW " + coin + " : " + amount);
            } else {
                history.add("GAGAL WITHDRAW " + coin + " : " + amount);
            }
        }

        public void showSaldo() {
            if (saldoCoin.isEmpty()) {
                System.out.println("Saldo kosong.");
                return;
            }
            for (Map.Entry<String, Double> e : saldoCoin.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
        }

        public void showHistory() {
            if (history.isEmpty()) {
                System.out.println("Belum ada transaksi.");
                return;
            }
            for (String h : history) {
                System.out.println(h);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            Wallet w = new Wallet();
            boolean run = true;

            while (run) {
                System.out.println("\n1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Lihat Saldo");
                System.out.println("4. Riwayat");
                System.out.println("0. Keluar");
                System.out.print("Pilih: ");
                int menu = sc.nextInt();
                sc.nextLine();

                switch (menu) {
                    case 1 -> {
                        System.out.print("Coin: ");
                        String coin = sc.nextLine();
                        System.out.print("Jumlah: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        w.deposit(coin, amt);
                    }
                    case 2 -> {
                        System.out.print("Coin: ");
                        String coin = sc.nextLine();
                        System.out.print("Jumlah: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        w.withdraw(coin, amt);
                    }
                    case 3 -> w.showSaldo();
                    case 4 -> w.showHistory();
                    case 0 -> run = false;
                    default -> System.out.println("Menu salah!");
                }
            }
        }
    }
}