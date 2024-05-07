package galeria.Exceptions;

@SuppressWarnings("serial")
public class ClienteNoVerificadoException extends Exception{

    public ClienteNoVerificadoException( String mensaje )
    {
        super( mensaje );
    }
}
