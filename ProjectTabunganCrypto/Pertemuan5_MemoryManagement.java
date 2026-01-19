import java.util.Scanner;

public class Pertemuan5_MemoryManagement {

    static class Wallet {
        private double saldo;

        public Wallet(double saldo) {
            this.saldo = saldo;
        }

        public void deposit(double amount) {
            saldo += amount;
        }

        public double getSaldo() {
            return saldo;
        }
    }

    // a) Typical calling sequence: main -> prosesTabungan -> hitungSaldoAkhir
    private static double hitungSaldoAkhir(double saldo, double deposit) {
        double hasil = saldo + deposit; // b) simple variable (stack)
        return hasil;
    }

    private static void prosesTabungan(Scanner sc) {
        System.out.print("Masukkan saldo awal: ");
        double saldoAwal = sc.nextDouble(); // b) simple variable (stack)

        System.out.print("Masukkan deposit: ");
        double deposit = sc.nextDouble(); // b) simple variable (stack)

        Wallet wallet = new Wallet(saldoAwal); // c) reference variable -> object di heap
        wallet.deposit(deposit);

        double saldoAkhir = hitungSaldoAkhir(saldoAwal, deposit);

        System.out.println("\nSaldo dari object Wallet : " + wallet.getSaldo());
        System.out.println("Saldo dari perhitungan   : " + saldoAkhir);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            prosesTabungan(sc);
        }
    }
}