<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="name" required="true" %>
<%@attribute name="label" required="true" %>

<label for="${name}">${label}:</label>
<input type="text" id="num" name="${name}" onKeyPress="return isNumberKey(event);">

<script src="/Atum/js/jquery.js"></script>
<script>
    function isNumberKey(evt) {
        "use strict";
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
</script>