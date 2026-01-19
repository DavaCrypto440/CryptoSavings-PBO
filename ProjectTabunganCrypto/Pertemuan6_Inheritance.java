import java.util.Scanner;

public class Pertemuan6_Inheritance {

    static class Account {
        protected double saldo;

        public Account(double saldoAwal) {
            this.saldo = saldoAwal;
        }

        public void deposit(double amount) {
            if (amount > 0) saldo += amount;
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= saldo) saldo -= amount;
        }

        public double getSaldo() {
            return saldo;
        }
    }

    static class CryptoSavings extends Account {
        public CryptoSavings(double saldoAwal) {
            super(saldoAwal);
        }

        @Override
        public void deposit(double amount) {
            double fee = amount * 0.005;
            super.deposit(amount - fee);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            CryptoSavings acc = new CryptoSavings(0);

            System.out.print("Deposit: ");
            acc.deposit(sc.nextDouble());

            System.out.print("Withdraw: ");
            acc.withdraw(sc.nextDouble());

            System.out.println("Saldo akhir: " + acc.getSaldo());
        }
    }
}