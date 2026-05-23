package payment.variation;

public class ReductionPromotionnelle extends VariationPrix {
    private float taux;

    @Override
    public void appliquer() {
        // logique d'application de la réduction promotionnelle
    }

    public float getTaux() { return taux; }
}
