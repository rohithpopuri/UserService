package com.rohith.userservice.mapper;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserResponseDto>  listOfUserToResponseDto (List<User> users);

    UserResponseDto userToUserResponseDto(User user);

    User userRequestDToToUser(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    User partialUserRequestDtoToUser(UserRequestDto userRequestDto,@MappingTarget User user);
}
