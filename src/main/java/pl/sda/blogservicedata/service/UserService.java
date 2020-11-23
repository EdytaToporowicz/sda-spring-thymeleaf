package pl.sda.blogservicedata.service;

import org.springframework.stereotype.Service;
import pl.sda.blogservicedata.model.User;
import pl.sda.blogservicedata.model.request.UserDto;
import pl.sda.blogservicedata.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        return userRepository.save(user);
    }
}
