package payment;

public class ReductionPromotionnelle extends VariationPrix {
    private float taux;

    public ReductionPromotionnelle(float taux) {
        this.taux = taux;
    }

    @Override
    public float appliquer(float montant) {
        return montant - (montant * this.taux);
    }

    public float getTaux() { return taux; }
}
