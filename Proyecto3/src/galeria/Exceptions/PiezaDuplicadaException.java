package galeria.Exceptions;

@SuppressWarnings("serial")
public class PiezaDuplicadaException extends Exception{

    public PiezaDuplicadaException( String mensaje )
    {
        super( mensaje );
    }
}
