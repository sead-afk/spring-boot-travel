package user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw  new IllegalStateException(
                    "user with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String username, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        if(username != null &&
                username.length() > 0 &&
                !Objects.equals(user.getUsername(), username)) {
            user.setUsername(username);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }
}
