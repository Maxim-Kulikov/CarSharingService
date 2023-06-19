package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.comparator.CarComparator;
import org.example.comparator.ExtraUserDataComparator;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.dto.userDTO.ExtraUserDataFilterReq;
import org.example.model.Car;
import org.example.repository.user.ExtraUserDao;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.example.mapper.user.ExtraUserDataMapper;
import org.example.model.ExtraUserData;
import org.example.service.ExtraUserDataService;
import org.example.util.SortParamsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@EqualsAndHashCode
@AllArgsConstructor
@ComponentScan("org.example")
public class ExtraUserDataServiceImpl implements ExtraUserDataService {
    @Autowired
    private final ExtraUserDao extraUserDao;
    @Autowired
    private final ExtraUserDataMapper extraUserDataMapper;

    @Override
    public List<ExtraUserDataResp> getAll(ExtraUserDataFilterReq filter) {
        return extraUserDataMapper.toExtraUserDataResponseList(
                filterExtraUserData((List<ExtraUserData>) extraUserDao.findAll(), filter)
        );
    }

    @Override
    public ExtraUserDataResp update(ExtraUserDataUpdateReq dto, Long id) {
        ExtraUserData extraUserData = extraUserDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find extra user data by this id!"));
        extraUserData = updateExtraUserDataWithChanger(extraUserData, dto);
        return extraUserDataMapper.toExtraUserDataResponse(extraUserData);
    }

    @Override
    public ExtraUserDataResp get(Long id) {
        return extraUserDataMapper.toExtraUserDataResponse(
                extraUserDao.findById(id)
                        .orElseThrow(() -> new RuntimeException("Could not find extra user data by this id!"))
        );
    }

    private List<ExtraUserData> filterExtraUserData(List<ExtraUserData> list, ExtraUserDataFilterReq filter) {
        SortParamsValidator.Data sortParams = SortParamsValidator
                .throwExceptionIfIncorrectInputOrElseGetData(filter.getSortOrder(), filter.getSortField());
        List<String> names = filter.getNames();
        List<String> lastnames = filter.getLastnames();
        SortOrder sortOrder = sortParams.getSortOrder();
        SortField sortField = sortParams.getSortField();

        Stream<ExtraUserData> stream = list.stream().filter(data -> {
            if (!(names == null
                    || names.isEmpty()
                    || names.contains(data.getName()))
            ) {
                return false;
            }

            if (!(lastnames == null
                    || lastnames.isEmpty()
                    || lastnames.contains(data.getLastname()))
            ) {
                return false;
            }
            return true;
        });

        return sortField != null && sortOrder != null ?
                stream.sorted(new ExtraUserDataComparator(sortField, sortOrder)).collect(Collectors.toList())
                : stream.collect(Collectors.toList());
    }

    private ExtraUserData updateExtraUserData(ExtraUserData model, ExtraUserDataUpdateReq dto) {
        return ExtraUserData.builder()
                .id(model.getId())
                .phone(dto.getPhone().equals(model.getPhone()) || dto.getPhone().isEmpty()
                        ? model.getPhone() : dto.getPhone())
                .name(dto.getName().equals(model.getName()) || dto.getName().isEmpty()
                        ? model.getName() : dto.getName())
                .lastname(dto.getLastname().equals(model.getLastname()) || dto.getLastname().isEmpty()
                        ? model.getLastname() : dto.getLastname())
                .birthdate(dto.getBirthdate().equals(model.getBirthdate()) || dto.getBirthdate() == null
                        ? model.getBirthdate() : dto.getBirthdate())
                .passportNumber(dto.getPassportNumber().equals(model.getPassportNumber()) || dto.getPassportNumber().isEmpty()
                        ? model.getPassportNumber() : dto.getPassportNumber())
                .drivingLicense(dto.getDrivingLicense().equals(model.getDrivingLicense()) || dto.getDrivingLicense().isEmpty()
                        ? model.getDrivingLicense() : dto.getDrivingLicense())
                .registerDate(dto.getRegisterDate().equals(model.getRegisterDate()) || dto.getRegisterDate() == null
                        ? model.getRegisterDate() : dto.getRegisterDate())
                .build();
    }

    private ExtraUserData updateExtraUserDataWithChanger(ExtraUserData model, ExtraUserDataUpdateReq dto) {
        return model.changer()
                .id(model.getId())
                .phone(dto.getPhone().equals(model.getPhone()) || dto.getPhone().isEmpty()
                        ? model.getPhone() : dto.getPhone())
                .name(dto.getName().equals(model.getName()) || dto.getName().isEmpty()
                        ? model.getName() : dto.getName())
                .lastname(dto.getLastname().equals(model.getLastname()) || dto.getLastname().isEmpty()
                        ? model.getLastname() : dto.getLastname())
                .birthdate(dto.getBirthdate().equals(model.getBirthdate()) || dto.getBirthdate() == null
                        ? model.getBirthdate() : dto.getBirthdate())
                .passportNumber(dto.getPassportNumber().equals(model.getPassportNumber()) || dto.getPassportNumber().isEmpty()
                        ? model.getPassportNumber() : dto.getPassportNumber())
                .drivingLicense(dto.getDrivingLicense().equals(model.getDrivingLicense()) || dto.getDrivingLicense().isEmpty()
                        ? model.getDrivingLicense() : dto.getDrivingLicense())
                .registerDate(dto.getRegisterDate().equals(model.getRegisterDate()) || dto.getRegisterDate() == null
                        ? model.getRegisterDate() : dto.getRegisterDate())
                .change();
    }
}
