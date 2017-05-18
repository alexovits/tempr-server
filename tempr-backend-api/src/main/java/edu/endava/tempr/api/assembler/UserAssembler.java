package edu.endava.tempr.api.assembler;


import edu.endava.tempr.common.UserDto;
import edu.endava.tempr.model.User;
import edu.endava.tempr.model.UserType;
import org.springframework.stereotype.Service;

@Service
public class UserAssembler implements Assembler<UserDto, User> {

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUserType(UserType.valueOf(dto.getUserType()));
        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setEmail(entity.getEmail());
        userDto.setUserType(entity.getUserType().getAuthority());
        return userDto;
    }

}
