package Exceptions;

@SuppressWarnings("serial")
public class FormatoIncorrectoException extends Exception{

    public FormatoIncorrectoException( String mensaje )
    {
        super( mensaje );
    }
}
