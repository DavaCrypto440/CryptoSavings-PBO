import java.util.Scanner;

public class Pertemuan4_EncapsulationClass {

    static class Wallet {
        private double saldo;

        public double getSaldo() {
            return saldo;
        }

        public void deposit(double amount) {
            if (amount > 0) saldo += amount;
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= saldo) saldo -= amount;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            Wallet wallet = new Wallet();
            boolean run = true;

            while (run) {
                System.out.println("\n1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Cek Saldo");
                System.out.println("0. Keluar");
                System.out.print("Pilih: ");
                int menu = sc.nextInt();

                switch (menu) {
                    case 1 -> {
                        System.out.print("Jumlah deposit: ");
                        wallet.deposit(sc.nextDouble());
                    }
                    case 2 -> {
                        System.out.print("Jumlah withdraw: ");
                        wallet.withdraw(sc.nextDouble());
                    }
                    case 3 -> System.out.println("Saldo: " + wallet.getSaldo());
                    case 0 -> run = false;
                    default -> System.out.println("Menu salah!");
                }
            }
        }
    }
}