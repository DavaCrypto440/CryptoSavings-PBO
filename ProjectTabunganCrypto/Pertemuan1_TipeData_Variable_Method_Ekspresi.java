import java.util.Scanner;

public class Pertemuan1_TipeData_Variable_Method_Ekspresi {

    private static double fee(double a) {
        return a * 0.005;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Nama: ");
            String user = sc.nextLine();

            System.out.print("Deposit: ");
            double deposit = sc.nextDouble();

            double saldo = deposit - fee(deposit);

            System.out.println(user + " | Saldo: " + saldo);
        }
    }
}