package com.rohith.userservice.mapper;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.Role;
import com.rohith.userservice.entity.Status;
import com.rohith.userservice.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-01T18:43:52+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserResponseDto> listOfUserToResponseDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponseDto( user ) );
        }

        return list;
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        int id = 0;
        String name = null;
        String passwordHash = null;
        String email = null;
        Role role = null;
        Status status = null;

        if ( user.getId() != null ) {
            id = user.getId();
        }
        name = user.getName();
        passwordHash = user.getPasswordHash();
        email = user.getEmail();
        role = user.getRole();
        status = user.getStatus();

        UserResponseDto userResponseDto = new UserResponseDto( id, name, passwordHash, email, role, status );

        return userResponseDto;
    }

    @Override
    public User userRequestDToToUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userRequestDto.id() );
        user.setName( userRequestDto.name() );
        user.setEmail( userRequestDto.email() );
        user.setPasswordHash( userRequestDto.passwordHash() );
        user.setRole( userRequestDto.role() );
        user.setStatus( userRequestDto.status() );

        return user;
    }

    @Override
    public User partialUserRequestDtoToUser(UserRequestDto userRequestDto, User user) {
        if ( userRequestDto == null ) {
            return user;
        }

        if ( userRequestDto.name() != null ) {
            user.setName( userRequestDto.name() );
        }
        if ( userRequestDto.email() != null ) {
            user.setEmail( userRequestDto.email() );
        }
        if ( userRequestDto.passwordHash() != null ) {
            user.setPasswordHash( userRequestDto.passwordHash() );
        }
        if ( userRequestDto.role() != null ) {
            user.setRole( userRequestDto.role() );
        }
        if ( userRequestDto.status() != null ) {
            user.setStatus( userRequestDto.status() );
        }

        return user;
    }
}
