<%-- 
    Document   : index
    Created on : 09/07/2013, 09:38:25
    Author     : GCI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/jquery.ui.all.css">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <div id="botoes">
                <img src="imagens/view_refresh.png" width="25px" height="25px" style="padding-right: 10px; cursor: pointer">
                <img src="imagens/search.png" width="25px" height="25px" style="padding-right: 10px;cursor: pointer">
                <img src="imagens/document_save.png" width="25px" height="25px" style="padding-right: 10px;cursor: pointer">
            </div>
            <div>
                <fieldset>
                    <legend>Cadastro de Tela</legend>
                    <br>
                    <label for="id">Id:</label>
                    <input type="text" id="id" name="id" style="width: 30px">
                    <br><br>
                    <label for="nomeClasse">Nome da Classe:</label>
                    <input id="nomeClasse" name="nomeClasse" style="padding-right: 20px">
                    <label for="moderador">Modificador:</label>
                    <select id="moderador" name="moderador">
                        <c:forEach items="${listaModeradorClasse}" var="moderador">
                            <option value="${moderador.id}">${moderador.descricao}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <label for="tabela">Tabela:</label>
                    <input id="tabela" name="tabela">
                    <br>
                    <input type="button" id="gravarTela" name="gravarTela" value="Gravar Tela" onclick="enviaTela();">
                </fieldset>
            </div>
            <div>
                <fieldset>
                    <legend>Cadastro de Atributos</legend>
                    <label for="nomeAtributo">Nome:</label>
                    <input type="text" id="nomeAtributo" name="nomeAtributo">
                    <br><br>
                    <label for="moderadorAtributo">Moderador:</label>
                    <select id="moderadorAtributo" name="moderadorAtributo">
                        <c:forEach items="${criteriaModeradorAtributos}" var="moderador">
                            <option value="${moderador.id}">${moderador.descricao}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <label for="tipoAtributo">Tipo:</label>
                    <select id="tipoAtributo" name="tipoAtributo">
                        <c:forEach items="${listaTipo}" var="tipo">
                            <option value="${tipo.id}">${tipo.descricao}</option>
                        </c:forEach>
                    </select>
                    <label for="caracteristica">Caracteristica:</label>
                    <select id="caracteristica" name="caracteristica">
                        <c:forEach items="${listaCaracteristica}" var="caracteristica">
                            <option value="${caracteristica.id}">${caracteristica.descricao}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <input type="button" name="adcionar" id="adcionar" value="Adcionar" onclick="adcionarAtributos();">
                </fieldset>
            </div>
            <div>
                <fieldset>
                    <legend>Gerar</legend>
                    <label for="bean">Bean</label>
                    <input type="checkbox" id="bean" name="bean">
                    <br>
                    <label for="servelt">Servlet</label>
                    <input type="checkbox" id="servlet" name="servlet">
                    <br>
                    <label for="dao">DAO</label>
                    <input type="checkbox" id="dao" name="dao">
                    <br>
                    <label for="jsp">JSP</label>
                    <input type="checkbox" id="jsp" name="jsp">
                    <br>
                    <label for="mapeamento">Map XML</label>
                    <input type="checkbox" id="mapeamento" name="mapeamento">
                    <br>
                    <label for="scriptTabela">Script Tabela</label>
                    <input type="checkbox" id="scriptTabela" name="scriptTabela">
                </fieldset>
            </div>
        </form>
    </body>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.maskMoney.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script src="js/culture/jquery.ui.datepicker-pt-BR.js"></script>
    <script src="js/InputMask.js"></script>
    <script src="js/JavaScriptUtil.js"></script>
    <script src="js/Parsers.js"></script>

    <script type="text/javascript">
                        function enviaTela() {
                            var nomeClasse, moderador, tabela;
                            nomeClasse = $("#nomeClasse").val();
                            moderador = $("#moderadorClase").val();
                            tabela = $("#tabela").val();

                            if (nomeClasse == "") {
                                alert("Preencha o nome da classe.");
                                return;
                            } else if (tabela == "") {
                                alert("Preencha o campo tabela.");
                                return;
                            }
                            $.ajax({
                                type: "POST",
                                url: "gravarClasse",
                                data: {classe: geraJsonClasse()},
                                dataType: "json",
                                success: function(retorno) {
                                    $("#id").val(retorno.id);
                                }
                            });
                        }
                        function adcionarAtributos() {
                            var nome, moderador, tipo, caracteristica, id;
                            nome = $("#nomeAtributo").val();
                            moderador = $("#moderadorAtributo").val();
                            tipo = $("#tipoAtributo").val();
                            caracteristica = $("#caracteristica").val();
                            id = $("#id").val();
                            if (nome == "") {
                                alert("Preencha o nome do atributo.");
                                return;
                            }
                            $.ajax({
                                type: "POST",
                                url: "gravarAtributos",
                                data: {atributos: geraJsonAtributos()},
                                dataType: "json",
                                success: function() {
                                    $("#nomeAtributo").val("");
                                }
                            });
                        }


                        function geraJsonClasse() {
                            var json = "[";
                            json += '{"nomeClasse":"' + $("#nomeClasse").val() + '",';
                            json += '"moderador":"' + $("#moderador").val() + '",';
                            json += '"tabela":"' + $("#tabela").val() + '"}';
                            json += "]";
                            return json;
                        }

                        function geraJsonAtributos() {
                            var nome, moderador, tipo, caracteristica, id;
                            nome = $("#nomeAtributo").val();
                            moderador = $("#moderadorAtributo").val();
                            tipo = $("#tipoAtributo").val();
                            caracteristica = $("#caracteristica").val();
                            id = $("#id").val();
                            var json = "[";
                            json += '{"idClasse":"' + id + '",';
                            json += '"nomeAtributo":"' + nome + '",';
                            json += '"moderador":"' + moderador + '",';
                            json += '"tipo":"' + tipo + '",';
                            json += '"caracteristica":"' + caracteristica + '"}';
                            json += "]";
                            return json;
                        }
    </script>
</html>
