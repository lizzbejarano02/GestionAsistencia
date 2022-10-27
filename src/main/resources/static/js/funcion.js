function checkPasswordMatch(fieldConfirmPassword) {
    if (fieldConfirmPassword.value != $("#password").val()) {
        fieldConfirmPassword.setCustomValidity("¡Las contraseñas no coinciden!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}