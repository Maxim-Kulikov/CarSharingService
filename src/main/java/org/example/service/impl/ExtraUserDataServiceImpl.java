package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.comparator.ExtraUserDataComparator;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.dto.userDTO.ExtraUserDataFilterReq;
import org.example.repository.user.ExtraUserDao;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.example.mapper.user.ExtraUserDataMapper;
import org.example.model.ExtraUserData;
import org.example.service.ExtraUserDataService;
import org.example.util.SortParamsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ExtraUserDataServiceImpl implements ExtraUserDataService {
    @Autowired
    private final ExtraUserDao extraUserDao;
    @Autowired
    private final ExtraUserDataMapper extraUserDataMapper;

    @Transactional
    @Override
    public List<ExtraUserDataResp> getAll(ExtraUserDataFilterReq filter) {
        return extraUserDataMapper.toExtraUserDataResponseList(
                filterExtraUserData((List<ExtraUserData>) extraUserDao.findAll(), filter)
        );
    }

    @Transactional
    @Override
    public ExtraUserDataResp update(ExtraUserDataUpdateReq dto, Long id) throws UserNotFoundException {
        ExtraUserData extraUserData = extraUserDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        extraUserData = update(extraUserData, dto);
        return extraUserDataMapper.toExtraUserDataResponse(extraUserData);
    }

    @Transactional
    @Override
    public ExtraUserDataResp get(Long id) throws UserNotFoundException {
        return extraUserDataMapper.toExtraUserDataResponse(
                extraUserDao.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id))
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
                    || !names.contains(data.getName()))) {
                return false;
            }

            if (!(lastnames == null
                    || lastnames.isEmpty()
                    || !lastnames.contains(data.getLastname()))
            ) {
                return false;
            }
            return true;
        });

        return sortField != null && sortOrder != null ?
                stream.sorted(new ExtraUserDataComparator(sortField, sortOrder)).collect(Collectors.toList())
                : stream.collect(Collectors.toList());
    }


    private ExtraUserData update(ExtraUserData model, ExtraUserDataUpdateReq dto) {
        return model.changer()
                .phone(dto.getPhone())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .birthdate(dto.getBirthdate())
                .passportNumber(dto.getPassportNumber())
                .drivingLicense(dto.getDrivingLicense())
                .registerDate(dto.getRegisterDate())
                .change();
    }
}
