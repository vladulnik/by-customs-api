package by.customs.by_customs_api.service;

import by.customs.by_customs_api.dto.ParticipantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipantService {

    ParticipantDto createParticipant(ParticipantDto dto);

    ParticipantDto getParticipantById(Long id);

    Page<ParticipantDto> getAllParticipants(Pageable pageable);

    ParticipantDto updateParticipant(Long id, ParticipantDto dto);

    void deleteParticipant(Long id);
}
