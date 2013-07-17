package gerador;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class PessoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    protected Connection connection;

    public PessoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Pesso pesso) {
        PreparedStatement pstmt;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO pessoa VALUES (?,?,?,?)");
        try {
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setInt(1, pesso.getId());
            pstmt.setString(2, pesso.getNome());
            pstmt.setInt(3, pesso.getIdade());
            pstmt.setString(4, pesso.getEmail());
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pesso> find() {
        PreparedStatement pstmt;
        ResultSet rs;
        List<Pesso> listaPesso = new ArrayList<Pesso>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM pessoa");
        try {
            pstmt = connection.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Pesso pesso = new Pesso();
                pesso.setId(rs.getInt("id"));
                pesso.setNome(rs.getString("nome"));
                pesso.setIdade(rs.getInt("idade"));
                pesso.setEmail(rs.getString("email"));
                listaPesso.add(pesso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPesso;
    }
}