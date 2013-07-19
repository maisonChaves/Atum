<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="name" required="true" %>
<%@attribute name="label" required="true" %>
<scirpt src="/Atum/js/jquery.js"></scirpt>
<script src="/Atum/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="/Atum/js/culture/jquery.ui.datepicker-pt-BR.js"></script>
<script src="/Atum/js/InputMask.js"></script>
<script src="/Atum/js/JavaScriptUtil.js"></script>
<script src="/Atum/js/Parsers.js"></script>
<link rel="stylesheet" href="css/jquery.ui.all.css">
<label for="${name}">${label}:</label>
<input type="text" id="${name}" name="${name}">

<script>
    $(document).ready(function() {
        new DateMask("dd/MM/yyyy", "${name}");
        $("#${name}").datepicker();
    });
</script>