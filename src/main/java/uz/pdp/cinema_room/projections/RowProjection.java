package uz.pdp.cinema_room.projections;

import javax.persistence.criteria.CriteriaBuilder;

public interface RowProjection {

    String getId();

    Integer getNumber();
}
