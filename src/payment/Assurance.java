package payment;
 
public class Assurance extends ModePaiement {
    private int numeroAssure;

    public Assurance(int numeroAssure) {
        this.numeroAssure = numeroAssure;
    }
 
    @Override
    public void valider() {
        // logique de validation de l'assurance
    }
 
    public void tauxCouverture(float tauxCouverture) {
        // logique de fixation du taux de couverture
    }
}
 
