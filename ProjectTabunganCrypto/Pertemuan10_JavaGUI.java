import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Pertemuan10_JavaGUI {

    // akun sementara
    private static final Map<String, String> users = new HashMap<>();

    static class Wallet {
        private final Map<String, Double> saldoCoin = new HashMap<>();

        public void deposit(String coin, double amount) {
            saldoCoin.put(coin, saldoCoin.getOrDefault(coin, 0.0) + amount);
        }

        public boolean withdraw(String coin, double amount) {
            double saldo = saldoCoin.getOrDefault(coin, 0.0);
            if (amount > 0 && amount <= saldo) {
                saldoCoin.put(coin, saldo - amount);
                return true;
            }
            return false;
        }

        public double getSaldo(String coin) {
            return saldoCoin.getOrDefault(coin, 0.0);
        }
    }

    private static JButton styledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return b;
    }

    private static void refreshTable(DefaultTableModel model, Wallet wallet) {
        model.setRowCount(0);
        for (String coin : new String[]{"BTC", "ETH", "USDT"}) {
            model.addRow(new Object[]{coin, wallet.getSaldo(coin)});
        }
    }

    // ===== LOGIN DIALOG COLORFUL =====
    private static String showLoginDialog() {
        JDialog dialog = new JDialog((Frame) null, "Login - Crypto Savings", true);
        dialog.setSize(420, 280);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("LOGIN CRYPTO SAVINGS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(title, BorderLayout.WEST);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(new Color(245, 245, 245));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();

        form.add(new JLabel("Username:"));
        form.add(txtUser);
        form.add(new JLabel("Password:"));
        form.add(txtPass);

        JLabel info = new JLabel(" ");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JButton btnLogin = styledButton("Login", new Color(46, 204, 113));
        JButton btnRegister = styledButton("Register", new Color(155, 89, 182));
        JButton btnExit = styledButton("Exit", new Color(231, 76, 60));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.setBackground(new Color(245, 245, 245));
        buttons.add(btnLogin);
        buttons.add(btnRegister);
        buttons.add(btnExit);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(new Color(245, 245, 245));
        bottom.add(buttons, BorderLayout.CENTER);
        bottom.add(info, BorderLayout.SOUTH);

        final String[] resultUser = {null};

        btnRegister.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = new String(txtPass.getPassword()).trim();

            if (u.isEmpty() || p.isEmpty()) {
                info.setForeground(new Color(231, 76, 60));
                info.setText("Username & password wajib diisi!");
                return;
            }
            if (users.containsKey(u)) {
                info.setForeground(new Color(231, 76, 60));
                info.setText("Username sudah terdaftar!");
                return;
            }

            users.put(u, p);
            info.setForeground(new Color(39, 174, 96));
            info.setText("Register sukses! Sekarang login.");
        });

        btnLogin.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = new String(txtPass.getPassword()).trim();

            if (!users.containsKey(u)) {
                info.setForeground(new Color(231, 76, 60));
                info.setText("Username belum terdaftar!");
                return;
            }
            if (!users.get(u).equals(p)) {
                info.setForeground(new Color(231, 76, 60));
                info.setText("Password salah!");
                return;
            }

            resultUser[0] = u;
            dialog.dispose();
        });

        btnExit.addActionListener(e -> {
            resultUser[0] = null;
            dialog.dispose();
        });

        dialog.add(header, BorderLayout.NORTH);
        dialog.add(form, BorderLayout.CENTER);
        dialog.add(bottom, BorderLayout.SOUTH);

        dialog.setVisible(true);
        return resultUser[0];
    }

    // ===== MAIN GUI BANK =====
    private static void openBankGUI(String username) {
        Wallet wallet = new Wallet();

        JFrame frame = new JFrame("Crypto Savings Bank - Colorful GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 420);
        frame.setLocationRelativeTo(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 144, 255));
        header.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("CRYPTO SAVINGS BANK");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel subtitle = new JLabel("Login sebagai: " + username);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel headerText = new JPanel(new GridLayout(2, 1));
        headerText.setOpaque(false);
        headerText.add(title);
        headerText.add(subtitle);

        header.add(headerText, BorderLayout.WEST);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Transaksi"));
        inputPanel.setBackground(new Color(245, 245, 245));

        JComboBox<String> cbCoin = new JComboBox<>(new String[]{"BTC", "ETH", "USDT"});
        JTextField txtAmount = new JTextField();

        inputPanel.add(new JLabel("Pilih Coin:"));
        inputPanel.add(cbCoin);
        inputPanel.add(new JLabel("Jumlah:"));
        inputPanel.add(txtAmount);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Coin", "Saldo"}, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Saldo Wallet"));

        JTextArea history = new JTextArea();
        history.setEditable(false);
        history.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane historyScroll = new JScrollPane(history);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Riwayat Transaksi"));

        JPanel center = new JPanel(new GridLayout(1, 2, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        center.add(tableScroll);
        center.add(historyScroll);

        JButton btnDeposit = styledButton("Deposit", new Color(46, 204, 113));
        JButton btnWithdraw = styledButton("Withdraw", new Color(231, 76, 60));
        JButton btnRefresh = styledButton("Refresh", new Color(155, 89, 182));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.setBackground(new Color(245, 245, 245));
        buttons.add(btnDeposit);
        buttons.add(btnWithdraw);
        buttons.add(btnRefresh);

        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(new Color(245, 245, 245));
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        left.add(inputPanel, BorderLayout.NORTH);
        left.add(buttons, BorderLayout.SOUTH);

        btnDeposit.addActionListener(e -> {
            try {
                String coin = (String) cbCoin.getSelectedItem();
                double amount = Double.parseDouble(txtAmount.getText().trim());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Jumlah harus > 0");
                    return;
                }

                wallet.deposit(coin, amount);
                history.append("DEPOSIT  " + coin + " : " + amount + "\n");
                txtAmount.setText("");
                refreshTable(tableModel, wallet);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Jumlah harus angka!");
            }
        });

        btnWithdraw.addActionListener(e -> {
            try {
                String coin = (String) cbCoin.getSelectedItem();
                double amount = Double.parseDouble(txtAmount.getText().trim());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Jumlah harus > 0");
                    return;
                }

                boolean ok = wallet.withdraw(coin, amount);
                if (!ok) {
                    JOptionPane.showMessageDialog(frame, "Saldo tidak cukup!");
                    return;
                }

                history.append("WITHDRAW " + coin + " : " + amount + "\n");
                txtAmount.setText("");
                refreshTable(tableModel, wallet);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Jumlah harus angka!");
            }
        });

        btnRefresh.addActionListener(e -> refreshTable(tableModel, wallet));

        refreshTable(tableModel, wallet);

        frame.setLayout(new BorderLayout());
        frame.add(header, BorderLayout.NORTH);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            users.put("admin", "123"); // akun default buat test

            String username = showLoginDialog();
            if (username != null) {
                openBankGUI(username);
            }
        });
    }
}