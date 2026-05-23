package payment;
 
import java.util.Date;
 
public class CarteCredit extends ModePaiement {
    private int numeroCarte;
    private Date expiration;
    private int cvc;
 
    @Override
    public void valider() {
        // logique de validation de la carte de crédit
    }
}