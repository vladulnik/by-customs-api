package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ParticipantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipantService {

    ParticipantDto create(ParticipantDto dto);

    ParticipantDto getById(Long id);

    Page<ParticipantDto> getAll(Pageable pageable);

    ParticipantDto update(Long id, ParticipantDto dto);

    void delete(Long id);
}
