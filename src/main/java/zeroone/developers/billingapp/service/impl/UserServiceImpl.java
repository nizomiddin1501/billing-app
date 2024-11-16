package zeroone.developers.billingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.User;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.exceptions.UserException;
import zeroone.developers.billingapp.payload.UserDto;
import zeroone.developers.billingapp.repository.UserRepository;
import zeroone.developers.billingapp.service.UserService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public Page<UserDto> getAllUsers(int page, int size) {
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size));
        return usersPage.map(this::userToDto);
    }

    @Override
    public Optional<UserDto> getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        return Optional.of(userToDto(user));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        if (user.getFullname() == null || user.getFullname().isEmpty()) {
            throw new UserException("User name cannot be null or empty");
        }
        boolean exists = userRepository.existsByUsername(user.getUsername());
        if (exists) {
            throw new UserException("User with this name already exists");
        }
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        existingUser.setFullname(userDto.getFullname());
        existingUser.setUsername(userDto.getFullname());
        existingUser.setBalance(userDto.getBalance());
        User updatedUser = userRepository.save(existingUser);
        return userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        userRepository.delete(user);
    }

    // DTO to Entity conversion
    public User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    // Entity to DTO conversion
    public UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
