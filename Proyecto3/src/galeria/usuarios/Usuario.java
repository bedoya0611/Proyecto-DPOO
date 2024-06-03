package galeria.usuarios;

public abstract class Usuario {
    private String login;
    private String password;

    // Constructor
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean verificarPassword(String password) {
        return this.password.equals(password);
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

    public String getPassword() {
        return password;
    }
}
