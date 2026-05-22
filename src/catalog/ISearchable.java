package catalog;

import java.util.List;

// ISP : interface dédiée uniquement à la recherche textuelle
public interface ISearchable {
    List<MedicalService> search(String keyword);
}
