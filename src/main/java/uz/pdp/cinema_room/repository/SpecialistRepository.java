package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Specialist;
import uz.pdp.cinema_room.projections.SpecialistProjection;

import java.util.List;
import java.util.UUID;

public interface SpecialistRepository extends JpaRepository<Specialist, UUID> {


    @Query(nativeQuery = true, value = "select cast(s.id as varchar) as specialistId, s.full_name as fullName, s.cast_type as specialistType from specialists s\n" +
            "join movies_specialist_list msl on s.id = msl.specialist_list_id\n" +
            "join movies m on m.id = msl.movies_id" +
            "\n where m.id = :movieId")
    List<SpecialistProjection> findAllByMovieId(UUID movieId);
}
