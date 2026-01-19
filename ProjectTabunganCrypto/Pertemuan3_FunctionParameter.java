import java.util.Scanner;

public class Pertemuan3_FunctionParameter {

    private static double deposit(double saldo, double amount) {
        return saldo + amount;
    }

    private static double withdraw(double saldo, double amount) {
        return saldo - amount;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            double saldo = 0;
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
                        double amount = sc.nextDouble();
                        if (amount > 0) saldo = deposit(saldo, amount);
                    }
                    case 2 -> {
                        System.out.print("Jumlah withdraw: ");
                        double amount = sc.nextDouble();
                        if (amount > 0 && amount <= saldo) saldo = withdraw(saldo, amount);
                    }
                    case 3 -> System.out.println("Saldo: " + saldo);
                    case 0 -> run = false;
                    default -> System.out.println("Menu salah!");
                }
            }
        }
    }
}