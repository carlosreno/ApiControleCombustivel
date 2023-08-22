package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.UserDto;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.UserMapper;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Sectors;
import com.example.apicontrolecombustivel.model.jpa.UserType;
import com.example.apicontrolecombustivel.model.jpa.Users;
import com.example.apicontrolecombustivel.repositories.UserRepository;
import com.example.apicontrolecombustivel.service.CompanyService;
import com.example.apicontrolecombustivel.service.SectorService;
import com.example.apicontrolecombustivel.service.UserService;
import com.example.apicontrolecombustivel.service.UserTypeService;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SectorService sectorService;
    private final CompanyService companyService;
    private final UserTypeService userTypeService;

    @Override
    public Users create(UserDto dto) {
        Company company = getCompanyById(dto);
        UserType userType = getTypeById(dto);
        List<Sectors> sectors = getAllSectorsById(dto.sectorIds());
        Users users = UserMapper.fromDtoToEntity(null,dto,sectors,userType,company);
        return userRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public MessageDto delete(Long id) {
        returnIfExist(id);
        userRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public Users findById(Long id) {
        return returnIfExist(id);
    }

    private Users returnIfExist(Long id) {
       return userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("user with this id not exist"));
    }

    @Override
    public Users put(Long id, UserDto dto) {
        returnIfExist(id);
        Company company = getCompanyById(dto);
        UserType userType = getTypeById(dto);
        List<Sectors> sectors = getAllSectorsById(dto.sectorIds());
        Users users = UserMapper.fromDtoToEntity(id,dto,sectors,userType,company);
        return userRepository.save(users);
    }

    @Override
    public Users patch(Long id, UserDto dto) {
        Users usersDb = returnIfExist(id);
        Users newDataUser = Users.builder()
                .id(id)
                .name(dto.name() != null ? dto.name() : usersDb.getName())
                .userType(dto.userTypeId() != null ? getTypeById(dto) : usersDb.getUserType())
                .sectors(dto.sectorIds() != null ? getAllSectorsById(dto.sectorIds()) : usersDb.getSectors())
                .company(dto.companyId() != null ? getCompanyById(dto) : usersDb.getCompany())
                .build();
        return userRepository.save(newDataUser);
    }

    private Company getCompanyById(UserDto dto) {
        return companyService.findById(dto.companyId());
    }

    private List<Sectors> getAllSectorsById(Set<Long> sectorIds) {
        return sectorService.findAllById(sectorIds);
    }

    private UserType getTypeById(UserDto dto) {
        return userTypeService.findById(dto.userTypeId());
    }
}
