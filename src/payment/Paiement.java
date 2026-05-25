package payment;

import java.util.ArrayList;
import java.util.List;

public class Paiement {

    private int idPaiement;
    private float prixInitial;
    private float prixFinal;

    private ModePaiement modePaiement;
    private List<VariationPrix> variations;

    public Paiement(int idPaiement, float prixInitial, ModePaiement modePaiement) {
        this.idPaiement = idPaiement;
        this.prixInitial = prixInitial;
        this.modePaiement = modePaiement;
        this.variations = new ArrayList<>();
        this.prixFinal = prixInitial;
    }

    public void ajouterVariation(VariationPrix variation) {
        variations.add(variation);
    }

    public void enregistrerOption() {
        // logique d'enregistrement du mode de paiement
    }

    public void calculerPrixFinal() {
       // Logique de calcul du prix final en appliquant les variations de prix
      
    }

    public float simulerPrix() {
        // Logique de simulation du prix final sans appliquer les variations
       
    }
   
    public void presenterPrix() {
        // Logique de présentation du prix final à l'utilisateur
    }

    public void validerPaiement() {
        // Logique de validation du paiement
    }

    // Getters
    public float getPrixFinal() { return prixFinal; }
    public float getPrixInitial() { return prixInitial; }
    public ModePaiement getModePaiement() { return modePaiement; }
    public List<VariationPrix> getVariations() { return variations; }
}
