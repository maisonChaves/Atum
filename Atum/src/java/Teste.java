
import gerador.Pessoa;
import gerador.bean.Atributos;
import gerador.bean.Classe;
import gerador.bean.Moderador;
import gerador.bean.Tipo;
import gerador.dao.DAO;
import gerador.utils.Conexao;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GCI
 */
public class Teste {

    public static void main(String[] args) {
        Session session = Conexao.getConexao();
        DAO dao = new DAO(session);
        Moderador publico = new Moderador();
        Moderador privado = new Moderador();
        publico.setDescricao("public");
        privado.setDescricao("private");
        publico = (Moderador) dao.inserir(publico);
        privado = (Moderador) dao.inserir(privado);
        Tipo string = new Tipo();
        Tipo integer = new Tipo();
        string.setDescricao("String");
        integer.setDescricao("Integer");
        string = (Tipo) dao.inserir(string);
        integer = (Tipo) dao.inserir(integer);

    }
}
