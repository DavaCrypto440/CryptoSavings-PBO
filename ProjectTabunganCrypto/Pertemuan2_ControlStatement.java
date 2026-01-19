import java.util.Scanner;

public class Pertemuan2_ControlStatement {
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
                        saldo += sc.nextDouble();
                    }
                    case 2 -> {
                        System.out.print("Jumlah withdraw: ");
                        saldo -= sc.nextDouble();
                    }
                    case 3 -> System.out.println("Saldo: " + saldo);
                    case 0 -> run = false;
                    default -> System.out.println("Menu salah!");
                }
            }
        }
    }
}