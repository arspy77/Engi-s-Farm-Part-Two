package engisfarm;

/** Kelas turunan dari Exception untuk dithrow saat error */
@SuppressWarnings("serial")
public class GeneralException extends Exception {

    /** Constructor GeneralException */
    public GeneralException(String message) {
        super(message);
    }
}