/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.utils;

/**
 *
 * @author GCI
 */
public class Utils {

    public static String retornaTipoPstmt(String tipo) {
        if (tipo.equals("String")) {
            return "pstmt.setString";
        } else if (tipo.equals("Integer")) {
            return "pstmt.setInt";
        } else if (tipo.equals("Date")) {
            return "pstmt.setDate";
        }
        else if (tipo.equals("Double")) {
            return "pstmt.setDouble";
        }
        return "";
    }

    public static String retornaTipoRs(String tipo) {
        if (tipo.equals("String")) {
            return "rs.getString";
        } else if (tipo.equals("Integer")) {
            return "rs.getInt";
        } else if (tipo.equals("Date")) {
            return "rs.getDate";
        }else if (tipo.equals("Double")) {
            return "rs.getDouble";
        }
        return "";
    }

    public static String passaPrimeiraMaisculo(String texto) {
        return texto.substring(0, 1).toUpperCase().concat(texto.substring(1));
    }
    public static String passaPrimeiraMinusculo(String texto) {
        return texto.substring(0, 1).toLowerCase().concat(texto.substring(1));
    }

    public static String serialVersion() {
        return "private static final long serialVersionUID = 1L;";
    }
}
