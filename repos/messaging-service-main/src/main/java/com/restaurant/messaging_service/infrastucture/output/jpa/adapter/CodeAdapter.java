package com.restaurant.messaging_service.infrastucture.output.jpa.adapter;

import com.restaurant.messaging_service.domain.spi.ICodePersistencePort;
import com.restaurant.messaging_service.infrastucture.output.jpa.entity.CodeEntity;
import com.restaurant.messaging_service.infrastucture.output.jpa.repository.ICodeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodeAdapter implements ICodePersistencePort {

    private final ICodeRepository codeRepository;

    @Override
    public void saveCode(Long orderId, String code) {
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode(code);
        codeEntity.setOrderId(orderId);
        codeRepository.save(codeEntity);
    }

    @Override
    public boolean orderIsReady(Long orderId) {
        return codeRepository.existsByOrderId(orderId);
    }

    @Override
    public boolean isCodeValid(Long orderId, String code) {

        return codeRepository.existsByOrderIdAndCode(orderId,code);
    }
}
