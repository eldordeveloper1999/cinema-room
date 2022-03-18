package uz.pdp.cinema_room.projections;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room.model.Afisha;
import uz.pdp.cinema_room.repository.HallRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Projection(types = Afisha.class)
public interface AfishaProjection {

    String getId();

    @Value("#{@movieRepository.findByAfishaId(target.id) }")
    MovieProjection getMovie();

    @Value("#{@sessionDateRepository.findByAfishaId(target.id) }")
    SessionDateProjection getDate();

    LocalTime getStart_time();

    LocalTime getEnd_time();

    @Value("#{@hallRepository.findByAfishaId(target.id)}")
    HallProjection getHall();

    @Value("#{@distributorRepository.getFindAllByAfishaId(target.id)}")
    List<DistributorProjection> getDistributors();
}
