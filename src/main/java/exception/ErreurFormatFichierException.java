package exception;

/**
 * Class ErreurFormatFichierException
 * Created by Alexis on 10/03/2018
 */
public class ErreurFormatFichierException extends Exception {

    int numeroDeLigne;

    public ErreurFormatFichierException(int numeroDeLigne) {
        this.numeroDeLigne = numeroDeLigne;
    }

    @Override
    public String getMessage() {
        String message = "Erreur de format de fichier ligne " + this.numeroDeLigne + ".";
        message += "\nFormat de ligne : {artiste : String};{album : String };{année : int };{piste : String };{durée en seconde : int};{lien du fichier audio : String}";
        return message;
    }
}
