package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ParticipantDto;

import java.util.List;

public interface ParticipantService {
    ParticipantDto create(ParticipantDto dto);
    ParticipantDto getById(Long id);
    List<ParticipantDto> getAll();
    ParticipantDto update(Long id, ParticipantDto dto);
    void delete(Long id);
}
