<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="name" required="true" %>
<%@attribute name="label" required="true" %>
<%@attribute name="mostrarSimbolo" %>
<%@attribute name="simbolo" %>
<%@attribute name="decimal" %>
<%@attribute name="milhar" %>
<scirpt src="/Atum/js/jquery.js"></scirpt>
<script src="/Atum/js/jquery.maskMoney.js"></script>
<label for="${name}">${label}:</label>
<input type="text" id="${name}" name="${name}">

<script>
    $(document).ready(function() {
        $("#${name}").maskMoney({symbol:"${simbolo} ", decimal:"${decimal}", thousands:"${milhar}"});
    });
</script>