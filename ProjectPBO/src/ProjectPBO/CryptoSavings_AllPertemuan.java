package ProjectPBO;

import java.util.Scanner;

public class CryptoSavings_AllPertemuan {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n=== MENU PERTEMUAN 1-10 ===");
                System.out.println("1. P1 (Method + Ekspresi)");
                System.out.println("2. P2 (Control Statement)");
                System.out.println("3. P3 (Function Parameter)");
                System.out.println("4. P4 (Encapsulation)");
                System.out.println("5. P5 (Memory Management)");
                System.out.println("6. P6 (Inheritance)");
                System.out.println("7. P7 (Polymorphism)");
                System.out.println("8. P8 (Exception Handling)");
                System.out.println("9. P9 (Collection)");
                System.out.println("10. P10 (GUI Colorful)");
                System.out.println("0. Keluar");
                System.out.print("Pilih: ");

                int menu;
                try {
                    menu = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input harus angka!");
                    continue;
                }

                if (menu == 0) break;

                switch (menu) {
                    case 1 -> pertemuan1(sc);
                    case 2 -> pertemuan2(sc);
                    case 3 -> pertemuan3(sc);
                    case 4 -> pertemuan4(sc);
                    case 5 -> pertemuan5(sc);
                    case 6 -> pertemuan6();
                    case 7 -> pertemuan7(sc);
                    case 8 -> pertemuan8(sc);
                    case 9 -> pertemuan9(sc);
                    case 10 -> pertemuan10();
                    default -> System.out.println("Menu salah!");
                }
            }

            System.out.println("Program selesai.");
        }
    }
    static void pertemuan1(Scanner sc) {
        System.out.print("Nama: ");
        String user = sc.nextLine();

        System.out.print("Deposit: ");
        double deposit = Double.parseDouble(sc.nextLine());

        double fee = deposit * 0.005;
        double saldo = deposit - fee;

        System.out.println(user + " | Saldo: " + saldo);
    }
    static void pertemuan2(Scanner sc) {
        double saldo = 0;

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Saldo 0.Keluar");
            System.out.print("Pilih: ");

            int m;
            try {
                m = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            if (m == 0) break;

            switch (m) {
                case 1 -> {
                    System.out.print("Deposit: ");
                    saldo += Double.parseDouble(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Withdraw: ");
                    saldo -= Double.parseDouble(sc.nextLine());
                }
                case 3 -> System.out.println("Saldo: " + saldo);
                default -> System.out.println("Menu salah!");
            }
        }
    }
    static void pertemuan3(Scanner sc) {
        double saldo = 0;

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Saldo 0.Keluar");
            System.out.print("Pilih: ");

            int m;
            try {
                m = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            if (m == 0) break;

            switch (m) {
                case 1 -> {
                    System.out.print("Deposit: ");
                    double a = Double.parseDouble(sc.nextLine());
                    if (a > 0) saldo = saldo + a;
                }
                case 2 -> {
                    System.out.print("Withdraw: ");
                    double a = Double.parseDouble(sc.nextLine());
                    if (a > 0 && a <= saldo) saldo = saldo - a;
                }
                case 3 -> System.out.println("Saldo: " + saldo);
                default -> System.out.println("Menu salah!");
            }
        }
    }
    static void pertemuan4(Scanner sc) {
        class Wallet {
            private double saldo;

            double getSaldo() {
                return saldo;
            }

            void deposit(double a) {
                if (a > 0) saldo += a;
            }

            void withdraw(double a) {
                if (a > 0 && a <= saldo) saldo -= a;
            }
        }

        Wallet w = new Wallet();

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Saldo 0.Keluar");
            System.out.print("Pilih: ");

            int m;
            try {
                m = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            if (m == 0) break;

            switch (m) {
                case 1 -> {
                    System.out.print("Deposit: ");
                    w.deposit(Double.parseDouble(sc.nextLine()));
                }
                case 2 -> {
                    System.out.print("Withdraw: ");
                    w.withdraw(Double.parseDouble(sc.nextLine()));
                }
                case 3 -> System.out.println("Saldo: " + w.getSaldo());
                default -> System.out.println("Menu salah!");
            }
        }
    }
    static void pertemuan5(Scanner sc) {
        class Wallet {
            double saldo;

            Wallet(double s) {
                saldo = s;
            }

            void deposit(double a) {
                saldo += a;
            }
        }

        System.out.print("Saldo awal: ");
        double saldoAwal = Double.parseDouble(sc.nextLine());

        System.out.print("Deposit: ");
        double dep = Double.parseDouble(sc.nextLine());

        Wallet w = new Wallet(saldoAwal);
        w.deposit(dep);

        double hitung = saldoAwal + dep;

        System.out.println("Saldo object : " + w.saldo);
        System.out.println("Saldo hitung : " + hitung);
    }
    static void pertemuan6() {
        class Account {
            protected double saldo;

            Account(double s) {
                saldo = s;
            }

            void deposit(double a) {
                if (a > 0) saldo += a;
            }

            void withdraw(double a) {
                if (a > 0 && a <= saldo) saldo -= a;
            }
        }

        Account a = new Account(0);
        a.deposit(100);
        a.withdraw(30);

        System.out.println("Saldo akhir: " + a.saldo);
    }
    static void pertemuan7(Scanner sc) {
        class Account {
            double saldo;

            void deposit(double a) {
                saldo += a;
            }
        }

        class CryptoAccount extends Account {
            @Override
            void deposit(double a) {
                saldo += a - (a * 0.005);
            }
        }

        class VipCryptoAccount extends Account {
            @Override
            void deposit(double a) {
                saldo += a - (a * 0.001);
            }
        }

        System.out.print("1.Crypto (0.5%) | 2.VIP (0.1%): ");
        int pilih = Integer.parseInt(sc.nextLine());

        Account acc = (pilih == 2) ? new VipCryptoAccount() : new CryptoAccount();

        System.out.print("Deposit: ");
        acc.deposit(Double.parseDouble(sc.nextLine()));

        System.out.println("Saldo akhir: " + acc.saldo);
    }
    static void pertemuan8(Scanner sc) {
        double saldo = 0;

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Saldo 0.Keluar");
            System.out.print("Pilih: ");

            int m;
            try {
                m = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            if (m == 0) break;

            try {
                switch (m) {
                    case 1 -> {
                        System.out.print("Deposit: ");
                        double a = Double.parseDouble(sc.nextLine());
                        if (a <= 0) throw new IllegalArgumentException("Deposit harus > 0");
                        saldo += a;
                    }
                    case 2 -> {
                        System.out.print("Withdraw: ");
                        double a = Double.parseDouble(sc.nextLine());
                        if (a <= 0) throw new IllegalArgumentException("Withdraw harus > 0");
                        if (a > saldo) throw new IllegalArgumentException("Saldo tidak cukup");
                        saldo -= a;
                    }
                    case 3 -> System.out.println("Saldo: " + saldo);
                    default -> System.out.println("Menu salah!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
    static void pertemuan9(Scanner sc) {
        java.util.Map<String, Double> saldoCoin = new java.util.HashMap<>();
        java.util.List<String> history = new java.util.ArrayList<>();

        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Saldo 4.History 0.Keluar");
            System.out.print("Pilih: ");

            int m;
            try {
                m = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            if (m == 0) break;

            switch (m) {
                case 1 -> {
                    System.out.print("Coin: ");
                    String coin = sc.nextLine().toUpperCase();
                    System.out.print("Jumlah: ");
                    double a = Double.parseDouble(sc.nextLine());

                    saldoCoin.put(coin, saldoCoin.getOrDefault(coin, 0.0) + a);
                    history.add("DEPOSIT " + coin + " : " + a);
                }
                case 2 -> {
                    System.out.print("Coin: ");
                    String coin = sc.nextLine().toUpperCase();
                    System.out.print("Jumlah: ");
                    double a = Double.parseDouble(sc.nextLine());

                    double saldo = saldoCoin.getOrDefault(coin, 0.0);
                    if (a > 0 && a <= saldo) {
                        saldoCoin.put(coin, saldo - a);
                        history.add("WITHDRAW " + coin + " : " + a);
                    } else {
                        history.add("GAGAL WITHDRAW " + coin + " : " + a);
                    }
                }
                case 3 -> System.out.println("Saldo: " + saldoCoin);
                case 4 -> {
                    if (history.isEmpty()) System.out.println("Belum ada transaksi.");
                    else history.forEach(System.out::println);
                }
                default -> System.out.println("Menu salah!");
            }
        }
    }
    static void pertemuan10() {
        CryptoSavings_ColorfulGUI.main(new String[]{});
    }
}