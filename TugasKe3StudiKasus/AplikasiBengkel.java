package TugasKe3StudiKasus;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AplikasiBengkel extends JFrame {
    private JTextField namaPelangganField;
    private JTextArea catatanArea;
    private JComboBox<String> kategoriComboBox;
    private JList<String> daftarLayanan;
    private JTable tabelPesanan;
    private DefaultTableModel tabelModel;
    private JLabel totalLabel;
    private Map<String, Double> hargaMap;
    private JRadioButton paketBasic, paketStandar, paketPremium;
    private JCheckBox gantiOli, poles, waxing;
    private NumberFormat formatMataUang;

    public AplikasiBengkel() {
        setTitle("Aplikasi Layanan Bengkel Motor");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi harga layanan
        inisialisasiHarga();

        // Format Rupiah
        formatMataUang = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opsi");
        JMenuItem menuItem = new JMenuItem("Pesanan Baru");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel untuk input pesanan
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Nama Pelanggan
        formPanel.add(new JLabel("Nama Pelanggan:"));
        namaPelangganField = new JTextField();
        formPanel.add(namaPelangganField);

        // Catatan Khusus
        formPanel.add(new JLabel("Catatan Khusus:"));
        catatanArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(catatanArea));

        // Kategori Layanan
        formPanel.add(new JLabel("Kategori Layanan:"));
        kategoriComboBox = new JComboBox<>(new String[]{"Perbaikan", "Pemeliharaan", "Pembersihan"});
        kategoriComboBox.addActionListener(e -> updateDaftarLayanan());
        formPanel.add(kategoriComboBox);

        // Daftar Layanan
        formPanel.add(new JLabel("Pilihan Layanan:"));
        daftarLayanan = new JList<>();
        updateDaftarLayanan();
        formPanel.add(new JScrollPane(daftarLayanan));

        // Paket Layanan
        formPanel.add(new JLabel("Paket:"));
        paketBasic = new JRadioButton("Basic");
        paketStandar = new JRadioButton("Standar");
        paketPremium = new JRadioButton("Premium");
        ButtonGroup grupPaket = new ButtonGroup();
        grupPaket.add(paketBasic);
        grupPaket.add(paketStandar);
        grupPaket.add(paketPremium);
        JPanel paketPanel = new JPanel();
        paketPanel.add(paketBasic);
        paketPanel.add(paketStandar);
        paketPanel.add(paketPremium);
        formPanel.add(paketPanel);

        // Opsi Ekstra
        formPanel.add(new JLabel("Opsi Tambahan:"));
        gantiOli = new JCheckBox("Ganti Oli");
        poles = new JCheckBox("Poles");
        waxing = new JCheckBox("Waxing");
        JPanel ekstraPanel = new JPanel();
        ekstraPanel.add(gantiOli);
        ekstraPanel.add(poles);
        ekstraPanel.add(waxing);
        formPanel.add(ekstraPanel);

        // Tabel Pesanan
        tabelModel = new DefaultTableModel(new String[]{"Layanan", "Paket", "Ekstra", "Jumlah", "Harga", "Catatan Khusus"}, 0);
        tabelPesanan = new JTable(tabelModel);

        // Tombol tambah ke tabel pesanan
        JButton tambahButton = new JButton("Tambah ke Pesanan");
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahKeTabelPesanan();
            }
        });

        // Label Total
        totalLabel = new JLabel("Total: Rp0");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(tabelPesanan), BorderLayout.CENTER);
        getContentPane().add(tambahButton, BorderLayout.SOUTH);
        getContentPane().add(totalLabel, BorderLayout.NORTH);
    }

    private void inisialisasiHarga() {
        // Harga contoh layanan
        hargaMap = new HashMap<>();
        hargaMap.put("Perbaikan Mesin", 200000.0);
        hargaMap.put("Penggantian Rem", 150000.0);
        hargaMap.put("Penyetelan Rantai", 50000.0);
        hargaMap.put("Servis Lengkap", 250000.0);
        hargaMap.put("Penggantian Ban", 100000.0);
        hargaMap.put("Cuci Basic", 30000.0);
        hargaMap.put("Detailing", 80000.0);
        hargaMap.put("Poles", 70000.0);
    }

    private void updateDaftarLayanan() {
        // Layanan per kategori
        String[] perbaikan = {"Perbaikan Mesin", "Penggantian Rem", "Penyetelan Rantai"};
        String[] pemeliharaan = {"Servis Lengkap", "Penggantian Ban"};
        String[] pembersihan = {"Cuci Basic", "Detailing", "Poles"};

        String kategoriTerpilih = (String) kategoriComboBox.getSelectedItem();
        if (kategoriTerpilih == null) return;

        switch (kategoriTerpilih) {
            case "Perbaikan":
                daftarLayanan.setListData(perbaikan);
                break;
            case "Pemeliharaan":
                daftarLayanan.setListData(pemeliharaan);
                break;
            case "Pembersihan":
                daftarLayanan.setListData(pembersihan);
                break;
            default:
                daftarLayanan.setListData(new String[]{}); // Kosongkan jika tidak ada kategori yang cocok
                break;
        }
    }

    private void tambahKeTabelPesanan() {
        String layananTerpilih = daftarLayanan.getSelectedValue();
        if (layananTerpilih != null) {
            // Tentukan paket yang dipilih
            String paketLayanan = paketBasic.isSelected() ? "Basic" :
                    paketStandar.isSelected() ? "Standar" : "Premium";

            // Opsi tambahan
            StringBuilder opsiEkstra = new StringBuilder();
            if (gantiOli.isSelected()) opsiEkstra.append("Ganti Oli ");
            if (poles.isSelected()) opsiEkstra.append("Poles ");
            if (waxing.isSelected()) opsiEkstra.append("Waxing ");

            // Harga dasar dengan multiplier berdasarkan paket
            double hargaDasar = hargaMap.getOrDefault(layananTerpilih, 0.0);
            double multiplierPaket = paketLayanan.equals("Basic") ? 1 : paketLayanan.equals("Standar") ? 1.5 : 2;
            double hargaAkhir = hargaDasar * multiplierPaket;

            // Catatan khusus
            String catatanKhusus = catatanArea.getText();

            // Tambahkan baris dengan harga dalam format Rupiah
            tabelModel.addRow(new Object[]{
                    layananTerpilih, 
                    paketLayanan, 
                    opsiEkstra.toString(), 
                    1, 
                    formatMataUang.format(hargaAkhir), 
                    catatanKhusus
            });
            updateTotal();
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < tabelModel.getRowCount(); i++) {
            int jumlah = (int) tabelModel.getValueAt(i, 3);
            String hargaTeks = (String) tabelModel.getValueAt(i, 4);
            double harga = Double.parseDouble(hargaTeks.replaceAll("[^0-9]", ""));
            total += jumlah * harga;
        }
        totalLabel.setText("Total: " + formatMataUang.format(total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiBengkel app = new AplikasiBengkel();
            app.setVisible(true);
        });
    }
}
