package galeria.Exceptions;

@SuppressWarnings("serial")
public class PiezaInexistenteException extends Exception{

    public PiezaInexistenteException( String mensaje )
    {
        super( mensaje );
    }
}
