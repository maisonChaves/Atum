

import gerador.bean.Atributos;
import gerador.bean.Classe;
import gerador.dao.DAO;
import gerador.utils.Conexao;
import gerador.utils.Parametros;
import gerador.utils.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GCI
 */
public class TesteGerarJsp {

    public static void main(String[] args) {
        Session session = Conexao.getConexao();
        DAO dao = new DAO(session);
        Classe classe = (Classe) dao.busca(Classe.class, 10);
        File diretorio = new File("E:\\Atum\\Atum\\web");
        boolean statusDiretorio = diretorio.isDirectory();
        System.out.println(statusDiretorio);
        File arquivo = new File(diretorio, Utils.passaPrimeiraMinusculo(classe.getNomeClasse()) + ".jsp");
        try {
            boolean statusArq = arquivo.createNewFile();
            System.out.println(statusArq);
            System.out.println("Comecando a escrever no arquivo");
            FileReader fileR = new FileReader(arquivo);
            BufferedReader buffR = new BufferedReader(fileR);
            FileWriter fileW = new FileWriter(arquivo);
            BufferedWriter buffW = new BufferedWriter(fileW);
            buffW.write("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>");
            buffW.newLine();
            buffW.write("<%@taglib prefix=\"tag\" tagdir=\"/WEB-INF/tags/\" %>");
            buffW.newLine();
            buffW.write("<html>");
            buffW.newLine();
            buffW.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            buffW.newLine();
            buffW.write("<title> ");
            buffW.write(classe.getNomeClasse());
            buffW.write(" </title>");
            buffW.newLine();
            buffW.write("<body>");
            buffW.newLine();
            buffW.write("<form>");
            buffW.newLine();
            for (Atributos item : classe.getAtributos()) {
                if (!item.getModerador().getId().equals(Parametros.PROTECTED)) {
                    buffW.write(retornaTipoInput(item.getCaracteristica().getId(), item.getNome().toLowerCase(), item.getNome(),",",".","R$"));
                    buffW.newLine();
                }
            }
            buffW.write("</form>");
            buffW.newLine();
            buffW.write("</body>");
            buffW.newLine();
            buffW.write("</html>");
            buffW.newLine();
            buffR.close();
            buffW.close();
            System.out.println("Classe Gerada");
        } catch (Exception e) {
        }
    }

    public static String retornaTipoInput(Integer tipo, String name, String label, String milhar, String decimal, String simbolo) {
        if (tipo.equals(Parametros.INPUT_NUMERICO)) {
            return "<tag:inputNum name='" + name + "' label='" + label + "'/>";
        }
        if (tipo.equals(Parametros.INPUT_DATA)) {
            return "<tag:inputDate name='" + name + "' label='" + label + "'/>";
        }
        if (tipo.equals(Parametros.INPUT_MONETARIO)) {
            
            return "<tag:inputMoney name='" + name + "' label='" + label + "' decimal='"+decimal+"' milhar='"+milhar+"' simbolo='"+simbolo+"'/>";
        }
        return "";
    }
}
