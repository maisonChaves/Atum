
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
import java.util.List;
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
        Classe classe = (Classe) dao.busca(Classe.class, 10);
        File diretorio = new File("E:\\Atum\\Atum\\src\\java\\gerador");
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
            buffW.write(pacote());
            buffW.newLine();
            buffW.write(imports());
            buffW.newLine();
            buffW.write(classe.getModerador().getDescricao() + " class " + classe.getNomeClasse() + " implements Serializable{");
            buffW.newLine();
            buffW.write(Utils.serialVersion());
            buffW.newLine();
            for (Atributos item : classe.getAtributos()) {
                buffW.write(item.getModerador().getDescricao() + " " + item.getTipo().getDescricao() + " " + item.getNome() + ";");
                buffW.newLine();
            }
            buffW.write(getSet(classe.getAtributos()));
            buffR.close();
            buffW.write("}");
            buffR.close();
            buffW.close();
            System.out.println("Classe Gerada");
        } catch (Exception e) {
        }
    }

    public static String imports() {
        StringBuilder imports = new StringBuilder();
        imports.append("import java.io.Serializable;");
        imports.append("import java.sql.Date;");
        return imports.toString();
    }

    public static String pacote() {
        StringBuilder pacote = new StringBuilder();
        pacote.append("package gerador;");
        return pacote.toString();
    }

    public static String getSet(List<Atributos> listaAtributos) {
        StringBuilder getSet = new StringBuilder();
        for (Atributos item : listaAtributos) {
            if (item.getModerador().getId().equals(Parametros.PRIVATE) && !item.getModerador().getId().equals(Parametros.PROTECTED)) {
                getSet.append("public ").append(item.getTipo().getDescricao()).append(" get").append(Utils.passaPrimeiraMaisculo(item.getNome())).append("(){\n");
                getSet.append("return ").append(item.getNome()).append(";\n");
                getSet.append("}\n");
                getSet.append("public void set").append(Utils.passaPrimeiraMaisculo(item.getNome())).append("(").append(item.getTipo().getDescricao()).append(" ").append(item.getNome()).append("){\n");
                getSet.append("this.").append(item.getNome()).append("=").append(item.getNome()).append(";\n");
                getSet.append("}\n");
            }
        }
        return getSet.toString();
    }
}
