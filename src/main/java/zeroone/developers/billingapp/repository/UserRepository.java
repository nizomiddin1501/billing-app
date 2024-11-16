package zeroone.developers.billingapp.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zeroone.developers.billingapp.entity.User;

public interface UserRepository extends BaseRepository<User, Long> {


//    // Email exists check
//    @Query(value = "select count(*) > 0 from users u where u.user_email = :email", nativeQuery = true)
//    boolean existsByEmail(@Param("email") String email);
//
//
//    // Get user email
//    @Query(value = "select * from users u where u.user_email = :email", nativeQuery = true)
//    User findByUserEmail(@Param("email") String email);

    // User name exists check
    @Query(value = "select count(*) > 0 from users u where u.username = :username", nativeQuery = true)
    boolean existsByUsername(@Param("username") String username);

    // User ID exists check
    @Query(value = "select count(*) > 0 from users u where u.id = :id", nativeQuery = true)
    boolean existsByUserId(@Param("id") Long id);

}
