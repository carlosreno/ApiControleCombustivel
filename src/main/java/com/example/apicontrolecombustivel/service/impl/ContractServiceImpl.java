package com.example.apicontrolecombustivel.service.impl;

import com.example.apicontrolecombustivel.dto.MessageDto;
import com.example.apicontrolecombustivel.dto.model.ContractDetailsDto;
import com.example.apicontrolecombustivel.dto.projectionsDto.ContractResponse;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import com.example.apicontrolecombustivel.mapper.ContractMapper;
import com.example.apicontrolecombustivel.model.jpa.Company;
import com.example.apicontrolecombustivel.model.jpa.Contract;
import com.example.apicontrolecombustivel.projections.contracts.ContractProjection;
import com.example.apicontrolecombustivel.repositories.ContractRepository;
import com.example.apicontrolecombustivel.service.CompanyService;
import com.example.apicontrolecombustivel.service.ContractService;
import com.example.apicontrolecombustivel.utils.ContractResponseMapper;
import com.example.apicontrolecombustivel.utils.MsgStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final CompanyService companyService;

    @Override
    public Contract create(ContractDetailsDto dto) {
        Company customer = getCustomerById(dto);
        Company supplier = getSupplierById(dto);
        Contract contract = ContractMapper.fromDtoToEntity(null,dto,customer,supplier);
        return contractRepository.save(contract);

    }

    @Override
    public List<ContractProjection> findAllDetails() {
        return contractRepository.findAllProjectedBy();
    }

    @Override
    public ContractProjection findDetailsContractById(Long id) {
        return contractRepository.findAllProjectedById(id);
    }

    @Override
    public List<ContractResponse> findAll() {
        return contractRepository.findAllContractInfo().stream()
                .map(ContractResponseMapper::mapToContractResponse).toList();
    }

    @Override
    public MessageDto delete(Long id) {
        returnIfExist(id);
        contractRepository.deleteById(id);
        return MsgStandard.msgStandardOk("deleted");
    }

    @Override
    public Contract findById(Long id) {
        return returnIfExist(id);
    }

    private Contract returnIfExist(Long id) {
       return contractRepository.findById(id)
                .orElseThrow(()->new NotFoundException("contract with this id not exist"));
    }

    @Override
    public Contract put(Long id, ContractDetailsDto dto) {
        returnIfExist(id);
        Company customer = getCustomerById(dto);
        Company supplier = getSupplierById(dto);
        Contract contract = ContractMapper.fromDtoToEntity(null,dto,customer,supplier);
        return contractRepository.save(contract);
    }

    @Override
    public Contract patch(Long id, ContractDetailsDto dto) {
        Contract contractDb = returnIfExist(id);
        Company customer = getCustomerById(dto);
        Company supplier = getSupplierById(dto);
        Contract contract = ContractMapper.fromDtoToEntity(null,dto,customer,supplier);
        Contract newContract = Contract.builder()
                .id(id)
                .numberContract(dto.numberContract() !=null ? dto.numberContract() : contractDb.getNumberContract())
                .object(dto.object() !=null ? dto.object() : contractDb.getObject())
                .status(dto.status() !=null ? dto.status() : contractDb.getStatus())
                .customer(dto.customerId() !=null ? customer : contractDb.getCustomer())
                .supplier(dto.supplierId() !=null ? supplier : contractDb.getCustomer())
                .comments(dto.comments() !=null ? dto.comments() : contractDb.getComments())
                .dateInitial(dto.dateInitial() !=null ? dto.dateInitial() : contractDb.getDateInitial())
                .dateFinal(dto.dateFinal() !=null ? dto.dateFinal() : contractDb.getDateFinal())
                .build();
        return contractRepository.save(newContract);
    }

    private Company getCustomerById(ContractDetailsDto dto) {
        return companyService.findById(dto.customerId());
    }
    private Company getSupplierById(ContractDetailsDto dto) {
        return companyService.findById(dto.supplierId());
    }

}
