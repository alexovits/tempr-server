package edu.endava.tempr.api.assembler;


import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.UserDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gergo Nagy<gergo.nagy@endava.com>
 */
@Service
public class UserAssembler implements Assembler<UserDto, User> {

    @Autowired
    private ThermostatAssembler thermostatAssembler;

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setThermostat(thermostatAssembler.toEntity(dto.getThermostatDto()));
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
        userDto.setThermostatDto(thermostatAssembler.toDto(entity.getThermostat()));
        return userDto;
    }

}
