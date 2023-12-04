import com.tfg.swapCatBack.data.entities.FavouritesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFavoritesDao extends JpaRepository<FavouritesModel, Long> {

    List<FavouritesModel> findAllByUser_Username(String name);

    Optional<FavouritesModel> findByUser_UsernameAndCoinName(String username, String coin);


}
