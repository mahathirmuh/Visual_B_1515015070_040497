/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posttest6;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Maulia
 */
public class koneksi
{
    private static Connection con;
    public static Connection getConnection()
    {
        // fungsi try & catch sebagai exception yaitu mengecek benar atau salahnya pada program
        // dari source code tersebut
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/modul7visual","root","");
            JOptionPane.showMessageDialog(null,"Koneksi Berhasil");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal: "+e.getMessage());
        }
        return con;
    }
}
