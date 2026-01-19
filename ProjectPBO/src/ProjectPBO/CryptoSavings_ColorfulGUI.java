package ProjectPBO;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CryptoSavings_ColorfulGUI {

    static final Map<String, String> users = new HashMap<>();
    static final Map<String, Double> saldoCoin = new LinkedHashMap<>();

    static DefaultTableModel tableModel;
    static JTextArea areaHistory;
    static JComboBox<String> comboCoin;
    static JTextField txtJumlah;
    static JLabel lblLogin;

    static String currentUser = "";

    public static void main(String[] args) {
        if (users.isEmpty()) {
            users.put("Dava", "123");
        }

        if (saldoCoin.isEmpty()) {
            saldoCoin.put("BTC", 0.0);
            saldoCoin.put("ETH", 0.0);
            saldoCoin.put("USDT", 0.0);
        }

        SwingUtilities.invokeLater(CryptoSavings_ColorfulGUI::showLoginGUI);
    }

    static void showLoginGUI() {
        JFrame frame = new JFrame("Login - Crypto Savings");
        frame.setSize(720, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(720, 80));
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("LOGIN CRYPTO SAVINGS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(new EmptyBorder(10, 20, 10, 10));
        header.add(title, BorderLayout.WEST);

        JPanel body = new JPanel();
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(20, 40, 20, 40));
        body.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField txtUser = new JTextField();
        txtUser.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUser.setPreferredSize(new Dimension(300, 40));

        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(new Font("Arial", Font.PLAIN, 18));
        txtPass.setPreferredSize(new Dimension(300, 40));

        JButton btnLogin = makeButton("Login", new Color(46, 204, 113));
        JButton btnRegister = makeButton("Register", new Color(155, 89, 182));
        JButton btnExit = makeButton("Exit", new Color(231, 76, 60));

        gbc.gridx = 0;
        gbc.gridy = 0;
        body.add(lblUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        body.add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        body.add(lblPass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        body.add(txtPass, gbc);

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(btnLogin);
        panelBtn.add(btnRegister);
        panelBtn.add(btnExit);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        body.add(panelBtn, gbc);

        btnLogin.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = String.valueOf(txtPass.getPassword()).trim();

            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username & Password wajib diisi!");
                return;
            }

            if (!users.containsKey(u)) {
                JOptionPane.showMessageDialog(frame, "User belum terdaftar. Klik Register dulu.");
                return;
            }

            if (!users.get(u).equals(p)) {
                JOptionPane.showMessageDialog(frame, "Password salah!");
                return;
            }

            currentUser = u;
            frame.dispose();
            showDashboardGUI();
        });

        btnRegister.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = String.valueOf(txtPass.getPassword()).trim();

            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Isi Username & Password untuk Register!");
                return;
            }

            if (users.containsKey(u)) {
                JOptionPane.showMessageDialog(frame, "Username sudah dipakai!");
                return;
            }

            users.put(u, p);
            JOptionPane.showMessageDialog(frame, "Register berhasil! Silakan Login.");
        });

        btnExit.addActionListener(e -> frame.dispose());

        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static void showDashboardGUI() {
        JFrame frame = new JFrame("Crypto Savings Bank - Colorful GUI");
        frame.setSize(1050, 620);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(33, 150, 243));
        header.setPreferredSize(new Dimension(1050, 90));
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("CRYPTO SAVINGS BANK");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(10, 20, 0, 10));

        lblLogin = new JLabel("Login sebagai: " + currentUser);
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLogin.setBorder(new EmptyBorder(0, 22, 10, 10));

        JPanel leftHeader = new JPanel(new GridLayout(2, 1));
        leftHeader.setOpaque(false);
        leftHeader.add(title);
        leftHeader.add(lblLogin);

        header.add(leftHeader, BorderLayout.WEST);

        JButton btnLogout = makeButton("Logout", new Color(244, 67, 54));
        btnLogout.addActionListener(e -> {
            frame.dispose();
            showLoginGUI();
        });

        JPanel rightHeader = new JPanel();
        rightHeader.setOpaque(false);
        rightHeader.setBorder(new EmptyBorder(20, 10, 20, 20));
        rightHeader.add(btnLogout);

        header.add(rightHeader, BorderLayout.EAST);

        JPanel main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(15, 15, 15, 15));
        main.setLayout(new GridLayout(1, 3, 15, 15));

        JPanel panelInput = new JPanel();
        panelInput.setBackground(Color.WHITE);
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Transaksi"));
        panelInput.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCoin = new JLabel("Pilih Coin:");
        lblCoin.setFont(new Font("Arial", Font.BOLD, 16));

        comboCoin = new JComboBox<>(new String[]{"BTC", "ETH", "USDT"});
        comboCoin.setFont(new Font("Arial", Font.BOLD, 16));
        comboCoin.setPreferredSize(new Dimension(220, 35));

        JLabel lblJumlah = new JLabel("Jumlah:");
        lblJumlah.setFont(new Font("Arial", Font.BOLD, 16));

        txtJumlah = new JTextField();
        txtJumlah.setFont(new Font("Arial", Font.PLAIN, 16));
        txtJumlah.setPreferredSize(new Dimension(220, 35));

        JButton btnDeposit = makeButton("Deposit", new Color(46, 204, 113));
        JButton btnWithdraw = makeButton("Withdraw", new Color(231, 76, 60));
        JButton btnRefresh = makeButton("Refresh", new Color(155, 89, 182));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(lblCoin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelInput.add(comboCoin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(lblJumlah, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelInput.add(txtJumlah, gbc);

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(btnDeposit);
        panelBtn.add(btnWithdraw);
        panelBtn.add(btnRefresh);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelInput.add(panelBtn, gbc);

        JPanel panelSaldo = new JPanel(new BorderLayout());
        panelSaldo.setBackground(Color.WHITE);
        panelSaldo.setBorder(BorderFactory.createTitledBorder("Saldo Wallet"));

        tableModel = new DefaultTableModel(new Object[]{"Coin", "Saldo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollTable = new JScrollPane(table);
        panelSaldo.add(scrollTable, BorderLayout.CENTER);

        JPanel panelHistory = new JPanel(new BorderLayout());
        panelHistory.setBackground(Color.WHITE);
        panelHistory.setBorder(BorderFactory.createTitledBorder("Riwayat Transaksi"));

        areaHistory = new JTextArea();
        areaHistory.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaHistory.setEditable(false);

        JScrollPane scrollHistory = new JScrollPane(areaHistory);
        panelHistory.add(scrollHistory, BorderLayout.CENTER);

        main.add(panelInput);
        main.add(panelSaldo);
        main.add(panelHistory);

        btnDeposit.addActionListener(e -> {
            String coin = comboCoin.getSelectedItem().toString();
            Double jumlah = parseAmount(frame);
            if (jumlah == null) return;

            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(frame, "Jumlah deposit harus > 0");
                return;
            }

            saldoCoin.put(coin, saldoCoin.get(coin) + jumlah);
            addHistory("DEPOSIT", coin, jumlah);
            refreshTable();
            txtJumlah.setText("");
        });

        btnWithdraw.addActionListener(e -> {
            String coin = comboCoin.getSelectedItem().toString();
            Double jumlah = parseAmount(frame);
            if (jumlah == null) return;

            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(frame, "Jumlah withdraw harus > 0");
                return;
            }

            double saldo = saldoCoin.get(coin);
            if (jumlah > saldo) {
                JOptionPane.showMessageDialog(frame, "Saldo " + coin + " tidak cukup!");
                addHistory("GAGAL WITHDRAW", coin, jumlah);
                return;
            }

            saldoCoin.put(coin, saldo - jumlah);
            addHistory("WITHDRAW", coin, jumlah);
            refreshTable();
            txtJumlah.setText("");
        });

        btnRefresh.addActionListener(e -> refreshTable());

        refreshTable();

        frame.add(header, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static JButton makeButton(String text, Color color) {
        JButton b = new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(120, 40));
        return b;
    }

    static Double parseAmount(JFrame frame) {
        try {
            String s = txtJumlah.getText().trim();
            if (s.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Jumlah tidak boleh kosong!");
                return null;
            }
            return Double.valueOf(s); 
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Jumlah harus angka!");
            return null;
        }
    }

    static void refreshTable() {
        tableModel.setRowCount(0);
        for (String coin : saldoCoin.keySet()) {
            tableModel.addRow(new Object[]{coin, saldoCoin.get(coin)});
        }
    }

    static void addHistory(String type, String coin, double amount) {
        areaHistory.append(type + " " + coin + " : " + amount + "\n");
    }
}
