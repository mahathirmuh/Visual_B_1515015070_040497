/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posttest6;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Maulia
 */
public class FormDataBuku extends javax.swing.JFrame
{
    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();//untuk menghubungkan aplikasi dengan class koneksi 
    private Statement stt;
    private ResultSet rss;  //variabel rss berfungsi untuk memecah 1 baris record data menjadi beberapa data
    public int id=0,count=0;    //menambah variabel baru yang akan digunakan untuk pencarian dan penghapusan
    /**
     * Creates new form FormDataBuku
     */
    public FormDataBuku()
    {
        initComponents();
    }
    
    private void InitTable()
    {
        model = new DefaultTableModel();
        model.addColumn("JUDUL");
        model.addColumn("PENULIS");
        model.addColumn("HARGA");    
        jTable1.setModel(model);
    }
    
    private void TampilData()//method untuk menampilkan data
    {
        try
        {    
            String sql = "SELECT * FROM buku"; //query untuk menampilkan seluruh data buku
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){                  //perulangan menggunakan while untuk menampilkan data pada tabel
                Object[] o = new Object[4];
                o[0] = rss.getString("judul");      //mendefinisikan objek o[0] dengan isi dari kolom judul
                o[1] = rss.getString("penulis");    //mendefinisikan objek o[1] dengan isi dari kolom penulis
                o[2] = rss.getInt("harga");         //mendefinisikan objek o[2] dengan isi dari kolom harga
                o[3] = rss.getInt("id");            //mendefinisikan objek o[3] dengan isi dari kolom id
                model.addRow(o);                    //mencetak pada tabel bagian baris
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private void clear()
    {       //method ini berfungsi untuk menghapus table seluruh data pada method TampilData
        int rowCount = model.getRowCount();//mendeklarasikan variabel baru dan menentukan nilainya berdasarkan jumlah baris
        for (int i = rowCount - 1; i >= 0; i--)
        {
            model.removeRow(i);//menghapus baris menggunakan perulangan for
        }
    }
    private void clears(int coun)
    {      //method ini berfungsi untuk menghapus tabel hasil pencarian 
            for (int i = 0; i < coun; i++)
            {   //fungsi perulangan yaitu menggunakan variabel count untuk mencari jumlah baris dari hasil pencarian
                model.removeRow(i);//menghapus baris berdasarkan dari jumlah baris
            }
    }
    private void TampilCari(String cari, String objek)
    {
        String jud,pen,har;
        int har1;
        try
        {
            String sql = "SELECT * FROM buku WHERE " + objek + " LIKE '%" + cari +"%'"; //query pencarian berdasarkan objek yang dicari dan menggunakan like untuk mencari kemungkinan kemiripan antar objek pencarian
            stt = con.createStatement();
            rss = stt.executeQuery(sql);    //berfungsi mengeksekusi query      
            while(rss.next())
            {
                Object[] o = new Object[4];
                o[0] = rss.getString("judul");      //mendefinisikan objek o[0] dengan isi dari kolom judul
                o[1] = rss.getString("penulis");    //mendefinisikan objek o[1] dengan isi dari kolom penulis
                o[2] = rss.getInt("harga");         //mendefinisikan objek o[2] dengan isi dari kolom harga
                o[3] = rss.getInt("id");            //mendefinisikan objek o[3] dengan isi dari kolom id
                model.addRow(o);                    //mencetak hasil pencarian pada tabel
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void TambahData(String judul, String penulis, String harga)
    {
        try
        {
            String sql = "INSERT INTO buku VALUES (null,'"+judul+"','"+penulis+"',"+harga+")";  //query untuk menambahkan data pada tabel
            stt = con.createStatement();
            stt.executeUpdate(sql);                 //berfungsi mengeksekusi proses update pada query diatas
            model.addRow(new Object[]{judul,penulis,harga});        //menampilkan data baru pada tabel
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void UbahData(String judul, String penulis, String harga,int id)// fungsi parameter untuk mengambil nilai fungsi inputan dari judul dll.
    {
        try
        {
            String sql = "UPDATE buku SET judul='"+judul+"', penulis='"+penulis+"', harga ="+harga+" where id ="+id+"";
            //query diatas berfungsi untuk mengupdate data dari baris data yang dipilih pada tabel
            stt = con.createStatement();
            stt.executeUpdate(sql);             //berfungsi mengeksekusi proses update pada query diatas
            model.addRow(new Object[]{judul,penulis,harga}); //menampilkan data yang baru diupdate pada tabel
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void HapusData(int id,int baris)
    {
        try
        {
            String sql = "DELETE FROM buku where id="+id+""; //query untuk menghapus 1 record data
            stt = con.createStatement();    
            stt.executeUpdate(sql);         //berfungsi mengeksekusi proses update pada query diatas
            model.removeRow(baris);         //menghapus baris data yang dipilih
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private int getid(String judul, String penulis, String harga)
    {
        String jud,pen,har;                 //mendeklarasikan variabel baru untuk
        int har1=0;
        try
        {
            String sql = "SELECT * FROM buku";
            stt = con.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next())
            {
                Object[] o = new Object[4];
                o[0] = rss.getString("judul");      //mendefinisikan objek o[0] dengan isi dari kolom judul
                o[1] = rss.getString("penulis");    //mendefinisikan objek o[1] dengan isi dari kolom penulis
                o[2] = rss.getInt("harga");         //mendefinisikan objek o[2] dengan isi dari kolom harga
                o[3] = rss.getInt("id");            //mendefinisikan objek o[3] dengan isi dari kolom id
                jud=(String) o[0];                  //mengubah objek menjadi nilai dari variabel jud
                pen=(String) o[1];                  //mengubah objek menjadi nilai dari variabel pen
                har1=(int) o[2];                    //mengubah objek menjadi nilai dari variabel har1
                har= Integer.toString(har1);        //mengubah tipe data har1 dari integer menjadi string
                if(jud.equals(judul) && pen.equals(penulis) && har.equals(harga)){  //pengkondisian untuk mencari id
                    id = (int) o[3];                                                //jika kondisi terpenuhi  maka nilai id akan mengambil dari objek o[3]
                } 
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return id;
    }
    private void setId(int getid)       //method untuk mendefinisikan variabel id
    {
        id = getid;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        comboPenulis = new javax.swing.JComboBox<>();
        txtHarga = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboBy = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(121, 163, 217));

        jPanel2.setBackground(new java.awt.Color(155, 202, 242));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("FORM DATA BUKU");

        jPanel6.setBackground(new java.awt.Color(191, 57, 82));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(191, 57, 82));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel3.setBackground(new java.awt.Color(155, 202, 242));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("JUDUL");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("PENULIS");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("HARGA");

        txtJudul.setBackground(new java.awt.Color(217, 166, 121));

        comboPenulis.setBackground(new java.awt.Color(155, 202, 242));
        comboPenulis.setForeground(new java.awt.Color(191, 57, 82));
        comboPenulis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Siapa ?", "Yang", "Tanya", "Masa", "Bodo" }));
        comboPenulis.setSelectedIndex(2);

        txtHarga.setBackground(new java.awt.Color(217, 166, 121));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtJudul)
                    .addComponent(txtHarga)
                    .addComponent(comboPenulis, 0, 254, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(comboPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(155, 202, 242));
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setBackground(new java.awt.Color(155, 202, 242));
        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton3.setBackground(new java.awt.Color(155, 202, 242));
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton4.setBackground(new java.awt.Color(155, 202, 242));
        jButton4.setText("Keluar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Judul", "Penulis", "Harga"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(155, 202, 242));

        jLabel5.setText("Search:");

        jLabel6.setText("Search By :");

        comboBy.setBackground(new java.awt.Color(155, 202, 242));
        comboBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Judul", "Penulis", "Harga" }));
        comboBy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboByMouseClicked(evt);
            }
        });
        comboBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboByActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(155, 202, 242));
        jButton5.setText("Hapus Pencarian");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();    //memanggil method/fungsi InitTable
        TampilData();   //memanggil method/fungsi TampilData
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String judul = txtJudul.getText();//mendefinisikan variabel judul dari dari text field
        String penulis = comboPenulis.getSelectedItem().toString();//mendefinisika variabel penulis dari item yang dipilih
        String harga = txtHarga.getText();//mendefinisikan variabel harga dari dari text field
        TambahData(judul,penulis,harga);//memanggil method TambahData dengan paramater dari 3 variabel diaats
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);//untuk menghentikan aplikasi
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();//mendeklarasikan variabel baris dan mendefinisikannya dengan baris dari tabel yang dipilih
        //int id=0;
        String judul_edit = jTable1.getValueAt(baris, 0).toString();//mendefinisikan variabel judul_edit dari baris pada kolom judul yang dipilih
        String penulis_edit = jTable1.getValueAt(baris, 1).toString();//mendefinisikan variabel penulis_edit dari baris pada kolom penulis yang dipilih
        String harga_edit = jTable1.getValueAt(baris, 2).toString();//mendefinisikan variabel harga_edit dari baris pada kolom harga yang dipilih
        
        id=getid(judul_edit,penulis_edit,harga_edit);//mendefinisikan variabel id dengan menggunakan method getid
        setId(id);//memanggil method setId untuk menentukan id
        txtJudul.setText(judul_edit);                   //menyeting isi dari txtJudul dengan variabel judul_edit
        comboPenulis.setSelectedItem(penulis_edit);     //menyeting pilihan dari comboPenulis dengan variabel penulis_edit
        txtHarga.setText(harga_edit);                   //menyeting isi dari txtHarga dengan variabel harga_edit
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();//mendeklarasikan variabel baris dan mendefinisikannya dengan baris dari tabel yang dipilih           
        jTable1.setValueAt(txtJudul.getText(), baris, 0);       //mengupdate isi judul dari tabel yang diubah
        jTable1.setValueAt(comboPenulis.getSelectedItem(), baris, 1); //mengupdate isi penulis dari tabel yang diubah
        jTable1.setValueAt(txtHarga.getText(), baris, 2);       //mengupdate isi harga dari tabel yang diubah
        
        
        String judul = txtJudul.getText();      //mendefinisikan variabel judul dari isi textfield
        String penulis = comboPenulis.getSelectedItem().toString(); //mendefinisikan variabel penulis dari pilihan yang dipilih pada comboPenulis
        String harga = txtHarga.getText();      //mendefinisikan variabel harga dari isi textfield
        
        setId(id);          //memanggil nilai dari id
        UbahData(judul,penulis,harga,id);//memanggil method untuk mengubah data
        id=0;               //mengembalikan nilai id menjadi 0
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();//mendeklarasikan variabel baris dan mendefinisikannya dengan baris dari tabel yang dipilih 
        setId(id);//menggunakan method setId untuk mencari id
        HapusData(id,baris);    //menggunsksn method HapusData untuk menghapus data pada database dan table
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboByMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboByMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboByMouseClicked

    private void comboByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboByActionPerformed
        // TODO add your handling code here:
        String objek,cari; //mendeklarasikan variabel baru 
        cari = txtcari.getText();//mendefinisikan variabel cari dari text field pencarian
        objek = comboBy.getSelectedItem().toString();//mendefinisikan variabel objek dengan pilihan yang dipilih pada combobox objek
        if("Judul".equals(objek))   //pengkondisian jika objek pencarian berupa judul
        {
            clear();        //membersihkan tabel
            TampilCari(cari,objek); //menampilkan hasil pencarian
        }
        else if("Penulis".equals(objek))//pengkondisian jika objek pencarian berupa penulis
        {
            clear();        //membersihkan tabel
            TampilCari(cari,objek); //menampilkan hasil pencarian
        }
        else if("Harga".equals(objek))//pengkondisian jika objek pencarian berupa harga
        {
            clear();        //membersihkan tabel
            TampilCari(cari,objek); //menampilkan hasil pencarian
        }
    }//GEN-LAST:event_comboByActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        clear();        //memanggil method clear untuk membersihkan tabel
        comboBy.setSelectedIndex(0);//menyeting pilihan pada combobox menjadi default
        TampilData();   //memanggil method tampildata untuk mencetak seluruh isi ddatabase pada tabel
        txtcari.setText("");//menentukan textfield pencarian menjadi kosong
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable(){public void run(){new FormDataBuku().setVisible(true);}});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBy;
    private javax.swing.JComboBox<String> comboPenulis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}