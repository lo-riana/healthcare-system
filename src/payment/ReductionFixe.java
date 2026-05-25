package payment;

public class ReductionFixe extends VariationPrix {
    private float montant;

    public ReductionFixe(float montant) {
        this.montant = montant;
    }

    @Override
    public float appliquer(float montant) {
        return montant - this.montant;
    }

    public float getMontant() { 
        return montant; 
    }
}
