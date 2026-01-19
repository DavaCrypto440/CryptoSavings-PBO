import java.util.Scanner;

public class Pertemuan7_Polymorphism {

    // Parent Class
    static class Account {
        protected double saldo;

        public Account(double saldoAwal) {
            saldo = saldoAwal;
        }

        public void deposit(double amount) {
            saldo += amount;
        }

        public void withdraw(double amount) {
            saldo -= amount;
        }

        public double getSaldo() {
            return saldo;
        }
    }

    // Child Class 1
    static class CryptoAccount extends Account {
        public CryptoAccount(double saldoAwal) {
            super(saldoAwal);
        }

        @Override
        public void deposit(double amount) {
            double fee = amount * 0.005;
            saldo += amount - fee;
        }
    }

    // Child Class 2
    static class VipCryptoAccount extends Account {
        public VipCryptoAccount(double saldoAwal) {
            super(saldoAwal);
        }

        @Override
        public void deposit(double amount) {
            double fee = amount * 0.001;
            saldo += amount - fee;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("=== POLYMORPHISM CRYPTO SAVINGS ===");
            System.out.println("1. CryptoAccount (fee 0.5%)");
            System.out.println("2. VipCryptoAccount (fee 0.1%)");
            System.out.print("Pilih akun: ");
            int pilihan = sc.nextInt();

            // Polymorphism: tipe parent, objek child
            Account akun;
            if (pilihan == 2) {
                akun = new VipCryptoAccount(0);
            } else {
                akun = new CryptoAccount(0);
            }

            System.out.print("Masukkan deposit: ");
            double deposit = sc.nextDouble();
            akun.deposit(deposit);

            System.out.print("Masukkan withdraw: ");
            double withdraw = sc.nextDouble();
            akun.withdraw(withdraw);

            System.out.println("\nSaldo akhir: " + akun.getSaldo());
        }
    }
}