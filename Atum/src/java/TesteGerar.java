
import gerador.bean.Atributos;
import gerador.bean.Classe;
import gerador.dao.DAO;
import gerador.utils.Conexao;
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
public class TesteGerar {

    public static void main(String[] args) {
        Session session = Conexao.getConexao();
        DAO dao = new DAO(session);
        Classe classe = (Classe) dao.busca(Classe.class, 1);
        File diretorio = new File("C:\\Users\\GCI\\Documents\\NetBeansProjects\\rborges~subversion\\rborges~subversion\\TesteRodrigo&Ian\\src\\java\\gerador");
        boolean statusDiretorio = diretorio.isDirectory();
        System.out.println(statusDiretorio);
        File arquivo = new File(diretorio, classe.getNomeClasse() + ".java");
        try {
            boolean statusArq = arquivo.createNewFile();
            System.out.println(statusArq);
            System.out.println("Comecando a escrever no arquivo");
            FileReader fileR = new FileReader(arquivo);
            BufferedReader buffR = new BufferedReader(fileR);
            FileWriter fileW = new FileWriter(arquivo);
            BufferedWriter buffW = new BufferedWriter(fileW);
            buffW.write("package gerador;");
            buffW.newLine();
            buffW.write("import java.io.Serializable;");
            buffW.newLine();
            buffW.write(classe.getModerador().getDescricao() + " class " + classe.getNomeClasse() + " implements Serializable{");
            buffW.newLine();
            buffW.write("private static final long serialVersionUID = 1L;");
            buffW.newLine();
            for (Atributos item : classe.getAtributos()) {
                buffW.write(item.getModerador().getDescricao() + " " + item.getTipo().getDescricao() + " " + item.getNome() + ";");
                buffW.newLine();
            }
            buffW.write("}");
            buffR.close();
            buffW.close();
            System.out.println("Classe Gerada");
        } catch (Exception e) {
        }
    }
}
