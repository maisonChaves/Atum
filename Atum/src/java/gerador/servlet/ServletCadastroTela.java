/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.servlet;

import gerador.bean.Atributos;
import gerador.bean.Caracteristica;
import gerador.bean.Classe;
import gerador.bean.Moderador;
import gerador.bean.Tipo;
import gerador.dao.DAO;
import gerador.utils.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author GCI
 */
@WebServlet(name = "ServletCadastroTeça", urlPatterns = {"/cadastroTela", "/gravarClasse", "/gravarAtributos"})
public class ServletCadastroTela extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("cadastroTela")) {
            processaTela(request, response);
        } else if (request.getRequestURI().contains("gravarClasse")) {
            gravaClasse(request, response);
        } else if (request.getRequestURI().contains("gravarAtributos")) {
            gravaAtributo(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("cadastroTela")) {
            processaTela(request, response);
        } else if (request.getRequestURI().contains("gravarClasse")) {
            gravaClasse(request, response);
        } else if (request.getRequestURI().contains("gravarAtributos")) {
            gravaAtributo(request, response);
        }
    }

    public void processaTela(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = Conexao.getConexao();
        DAO dao = new DAO(session);
        Criteria criteriaModerador = session.createCriteria(Moderador.class);
        Criteria criteriaTipo = session.createCriteria(Tipo.class);
        Criteria criteriaCaracteristica = session.createCriteria(Caracteristica.class);
        List<Moderador> listaModerador = dao.buscaCriteria(criteriaModerador);
        List<Tipo> listaTipo = dao.buscaCriteria(criteriaTipo);
        List<Caracteristica> listaCaracteristicas = dao.buscaCriteria(criteriaCaracteristica);
        request.setAttribute("listaModerador", listaModerador);
        request.setAttribute("listaTipo", listaTipo);
        request.setAttribute("listaCaracteristica", listaCaracteristicas);
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        Conexao.fechaSessao(session);
    }

    public void gravaClasse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonValores = request.getParameter("classe") != null && !request.getParameter("classe").equals("") ? request.getParameter("classe") : "";
        Object obj = JSONValue.parse(jsonValores);
        PrintWriter out = response.getWriter();
        JSONArray array = (JSONArray) obj;
        Session session = Conexao.getConexao();
        Moderador moderador = new Moderador();
        DAO dao = new DAO(session);
        Classe classe = new Classe();
        JSONObject retorno = new JSONObject();
        for (Object item : array) {
            Map<String, String> mapaClasse = (Map) item;
            classe.setNomeClasse(mapaClasse.get("nomeClasse"));
            moderador = (Moderador) dao.busca(Moderador.class, Integer.parseInt(mapaClasse.get("moderador")));
            classe.setModerador(moderador);
            classe.setTabela(mapaClasse.get("tabela"));
            classe = (Classe) dao.inserir(classe);
            retorno.put("id", classe.getId());
        }
        out.print(retorno);
        out.close();
    }

    public void gravaAtributo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonValores = request.getParameter("atributos") != null && !request.getParameter("atributos").equals("") ? request.getParameter("atributos") : "";
        Object obj = JSONValue.parse(jsonValores);
        PrintWriter out = response.getWriter();
        JSONArray array = (JSONArray) obj;
        Session session = Conexao.getConexao();
        Moderador moderador = new Moderador();
        Tipo tipo = new Tipo();
        Caracteristica caracteristica = new Caracteristica();
        Classe classe = new Classe();
        DAO dao = new DAO(session);
        JSONObject retorno = new JSONObject();
        Atributos atributos = new Atributos();
        for (Object item : array) {
            Map<String, String> mapaClasse = (Map) item;
            classe = (Classe) dao.busca(Classe.class, Integer.parseInt(mapaClasse.get("idClasse")));
            moderador = (Moderador) dao.busca(Moderador.class, Integer.parseInt(mapaClasse.get("moderador")));
            tipo = (Tipo) dao.busca(Tipo.class, Integer.parseInt(mapaClasse.get("tipo")));
            caracteristica = (Caracteristica) dao.busca(Caracteristica.class, Integer.parseInt(mapaClasse.get("caracteristica")));
            atributos.setCaracteristica(caracteristica);
            atributos.setClasse(classe);
            atributos.setModerador(moderador);
            atributos.setTipo(tipo);
            atributos.setNome(mapaClasse.get("nomeAtributo"));
            dao.inserir(atributos);
        }
        out.print(retorno);
        out.close();
    }
}
