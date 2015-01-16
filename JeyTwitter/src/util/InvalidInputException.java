package util;

@SuppressWarnings("serial")
/**
 * Excepcion que controla que el tamaño del contenido que se visualiza en pantalla, se visualize sin problemas ni cortes
 * @author Sergio Anguita
 *
 */
public class InvalidInputException extends Exception {
	
	public InvalidInputException(String msg){
		 super(msg);
	}
}
