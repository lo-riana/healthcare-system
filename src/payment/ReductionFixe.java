package payment;

public class ReductionFixe extends VariationPrix {
    private float montant;

    @Override
    public void appliquer() {
        // logique d'application de la réduction fixe
    }

    public float getMontant() { 
        return montant; 
    }
}
