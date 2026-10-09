import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeopleMenu extends JFrame{

    // Komponen ini sudah dibuat oleh GUI Designer.
    // Tambahkan hanya jika belum ada di class kamu:
    // private JRadioButton aktifRadioButton;
    // private JRadioButton nonaktifRadioButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JTextField textField3;
    private JButton deleteButton;
    private JTable personTable;
    private JComboBox comboBox1;
    private JLabel title;
    private JLabel atributID;
    private JLabel atributNama;
    private JLabel atributTahunLahir;
    private JLabel atributKategori;
    private JLabel atributBebas;
    private JPanel mainPanel;
    private JRadioButton aktifRadioButton;
    private JRadioButton nonaktifRadioButton;

    private final List<Person> peopleList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private int selectedIndex = -1;

    public PeopleMenu() {
        // Mengelompokkan radio button agar hanya satu yang dipilih
        ButtonGroup statusButtonGroup = new ButtonGroup();
        statusButtonGroup.add(aktifRadioButton);
        statusButtonGroup.add(nonaktifRadioButton);

        // Mengisi pilihan kategori
        comboBox1.removeAllItems();
        comboBox1.addItem("Mahasiswa");
        comboBox1.addItem("Dosen");
        comboBox1.addItem("Umum");

        // Menyiapkan tabel
        tableModel = new DefaultTableModel(
                new Object[]{
                        "ID", "Nama", "Tahun Lahir",
                        "Kategori", "Status Keanggotaan"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        personTable.setModel(tableModel);

        // Mengisi data awal
        populateList();
        refreshTable();

        // Event tombol
        addUpdateButton.setText("Tambah");
        cancelButton.setText("Batal");
        deleteButton.setText("Hapus");

        addUpdateButton.addActionListener(e -> addOrUpdatePerson());
        cancelButton.addActionListener(e -> clearForm());
        deleteButton.addActionListener(e -> deletePerson());

        // Memilih data pada tabel untuk diedit
        personTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = personTable.getSelectedRow();

                if (row >= 0 && row < peopleList.size()) {
                    selectedIndex = row;
                    showSelectedPerson(row);
                    addUpdateButton.setText("Update");
                }
            }
        });

        // Pilihan awal
        if (peopleList.size() > 0) {
            aktifRadioButton.setSelected(true);
        }
    }

    // Data awal
    private void populateList() {
        peopleList.clear();

        peopleList.add(new Person(
                "P001", "Andi", 2004, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P002", "Budi", 2002, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P003", "Citra", 1985, "Dosen", "Aktif"
        ));

        peopleList.add(new Person(
                "P004", "Dewi", 1990, "Dosen", "Tidak Aktif"
        ));

        peopleList.add(new Person(
                "P005", "Eko", 1998, "Umum", "Aktif"
        ));

        peopleList.add(new Person(
                "P006", "Fajar", 2003, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P007", "Gita", 2001, "Mahasiswa", "Tidak Aktif"
        ));

        peopleList.add(new Person(
                "P008", "Hendra", 1982, "Dosen", "Aktif"
        ));

        peopleList.add(new Person(
                "P009", "Indah", 1995, "Umum", "Aktif"
        ));

        peopleList.add(new Person(
                "P010", "Joko", 2000, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P011", "Kartika", 1988, "Dosen", "Tidak Aktif"
        ));

        peopleList.add(new Person(
                "P012", "Lukman", 1997, "Umum", "Aktif"
        ));

        peopleList.add(new Person(
                "P013", "Maya", 2004, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P014", "Nanda", 2003, "Mahasiswa", "Tidak Aktif"
        ));

        peopleList.add(new Person(
                "P015", "Oscar", 1979, "Dosen", "Aktif"
        ));

        peopleList.add(new Person(
                "P016", "Putri", 1999, "Umum", "Aktif"
        ));

        peopleList.add(new Person(
                "P017", "Rizky", 2002, "Mahasiswa", "Aktif"
        ));

        peopleList.add(new Person(
                "P018", "Sinta", 1993, "Dosen", "Aktif"
        ));

        peopleList.add(new Person(
                "P019", "Tono", 1987, "Umum", "Tidak Aktif"
        ));

        peopleList.add(new Person(
                "P020", "Vina", 2001, "Mahasiswa", "Aktif"
        ));
    }

    // Menampilkan seluruh data ke tabel
    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Person person : peopleList) {
            tableModel.addRow(new Object[]{
                    person.getId(),
                    person.getNama(),
                    person.getTahunLahir(),
                    person.getKategori(),
                    person.getStatusKeanggotaan()
            });
        }
    }

    // Menambahkan data atau memperbarui data terpilih
    private void addOrUpdatePerson() {
        String id = textField1.getText().trim();
        String nama = textField2.getText().trim();
        String tahunText = textField3.getText().trim();
        String kategori = (String) comboBox1.getSelectedItem();

        String status = "";

        if (aktifRadioButton.isSelected()) {
            status = "Aktif";
        } else if (nonaktifRadioButton.isSelected()) {
            status = "Tidak Aktif";
        }

        // Validasi input kosong
        if (id.isEmpty() || nama.isEmpty() || tahunText.isEmpty()
                || kategori == null || status.isEmpty()) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Semua data harus diisi dan status harus dipilih!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validasi tahun lahir
        int tahunLahir;

        try {
            tahunLahir = Integer.parseInt(tahunText);

            int tahunSekarang = Calendar.getInstance()
                    .get(Calendar.YEAR);

            if (tahunLahir < 1 || tahunLahir > tahunSekarang) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Tahun lahir tidak valid!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Tahun lahir harus berupa angka!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Mencegah ID duplikat
        for (int i = 0; i < peopleList.size(); i++) {
            if (peopleList.get(i).getId().equalsIgnoreCase(id)
                    && i != selectedIndex) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "ID sudah digunakan!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        if (selectedIndex == -1) {
            // Tambah data baru
            Person person = new Person(
                    id, nama, tahunLahir, kategori, status
            );

            peopleList.add(person);

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data berhasil ditambahkan!"
            );
        } else {
            // Update data terpilih
            Person person = peopleList.get(selectedIndex);

            // ID dipertahankan agar tetap konsisten
            if (!person.getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "ID data yang dipilih tidak boleh diubah!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            person.setNama(nama);
            person.setTahunLahir(tahunLahir);
            person.setKategori(kategori);
            person.setStatusKeanggotaan(status);

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data berhasil diperbarui!"
            );
        }

        refreshTable();
        clearForm();
    }

    // Memasukkan data tabel ke dalam form
    private void showSelectedPerson(int row) {
        Person person = peopleList.get(row);

        textField1.setText(person.getId());
        textField2.setText(person.getNama());
        textField3.setText(String.valueOf(person.getTahunLahir()));
        comboBox1.setSelectedItem(person.getKategori());

        if (person.getStatusKeanggotaan().equals("Aktif")) {
            aktifRadioButton.setSelected(true);
        } else {
            nonaktifRadioButton.setSelected(true);
        }
    }

    // Menghapus data dengan confirmation prompt
    private void deletePerson() {
        int row = personTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Pilih data yang ingin dihapus terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Person person = peopleList.get(row);

        int confirmation = JOptionPane.showConfirmDialog(
                mainPanel,
                "Apakah kamu yakin ingin menghapus data "
                        + person.getNama() + " ("
                        + person.getId() + ")?",
                "Konfirmasi Penghapusan",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            peopleList.remove(row);
            refreshTable();
            clearForm();

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data berhasil dihapus!"
            );
        }
    }

    // Mengosongkan form
    private void clearForm() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        comboBox1.setSelectedIndex(0);

        aktifRadioButton.setSelected(false);
        nonaktifRadioButton.setSelected(false);

        personTable.clearSelection();
        selectedIndex = -1;

        addUpdateButton.setText("Tambah");
        textField1.setEditable(true);
    }

    // Menjalankan aplikasi
    public static void main(String[] args) {
        // buat object window
        PeopleMenu menu = new PeopleMenu();

        // atur ukuran window
        menu.setSize( 600, 500);

        // Letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
