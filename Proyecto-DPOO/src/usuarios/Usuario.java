package usuarios;

public abstract class Usuario {
    private String login;
    private String password;

    // Constructor
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean verificarCredenciales(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public void cambiarPassword(String nuevoPassword) {
        if (nuevoPassword != null && !nuevoPassword.isEmpty()) {
            this.password = nuevoPassword;
            System.out.println("Contraseña actualizada exitosamente.");
        } else {
            System.out.println("La nueva contraseña no puede ser vacía.");
        }
    }

    // Getters y setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
