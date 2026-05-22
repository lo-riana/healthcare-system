package catalog;

import java.util.List;

// ISP : interface dédiée uniquement au filtrage par critères
public interface IFilterable {
    List<MedicalService> filter(FilterCriteria criteria);
}
