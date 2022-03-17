package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.projections.DistributorProjection;

import java.util.List;
import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {

    @Query(nativeQuery = true, value = "select cast(d.id as varchar ) as id, d.name as name, " +
            "cast(d.attachment_id as varchar) as photoId from distributors d " +
            "join movies m on d.id = m.distributor_id\n" +
            "join afishalar a on m.id = a.movie_id\n" +
            "join reserved_halls rh on a.id = rh.afisha_id" +
            " where rh.afisha_id = :id")
    List<DistributorProjection> getFindAllByAfishaId(UUID id);
}
